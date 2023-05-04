package pi.arctic.ecopower.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.DTO.Achat;
import pi.arctic.ecopower.entities.*;
import pi.arctic.ecopower.DTO.Payment;
import pi.arctic.ecopower.DTO.Reponseachat;
import pi.arctic.ecopower.repositories.InstallationDateRepository;
import pi.arctic.ecopower.repositories.OrderRepo;
import pi.arctic.ecopower.repositories.UserRepo;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class CheckoutserviceImp implements ICheckoutservice {
    private final UserRepo userRepo;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserservice iUserservice;

    public CheckoutserviceImp(UserRepo userRepo, @Value("${stripe.key.secret}") String secretKey) {
        this.userRepo = userRepo;
        Stripe.apiKey = secretKey;
    }

    @Override
    public PaymentIntent createPaymentIntent(Payment payment) throws StripeException {
        List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", payment.getAmount());
        params.put("currency", payment.getCurrency());
        params.put("payment_method_types", paymentMethodTypes);
        params.put("description", "achat");
        params.put("receipt_email", payment.getEmailrecu());

        return PaymentIntent.create(params);
    }

    @Override
    public Reponseachat placeOrder(@NotNull HttpServletRequest request, Achat achat) {
        // Create an instance of Order
        Order order = new Order();

        // Get the user from the token
        User user = iUserservice.getUserByToken(request);
        order.setUser(user);

        // Create an instance of Address and set the shipping address for the order
        Address address = new Address();
        address.setStreet(achat.getAddressLivraison().getStreet());
        address.setCity(achat.getAddressLivraison().getCity());
        address.setHouse(achat.getAddressLivraison().getHouse());
        address.setZipCode(achat.getAddressLivraison().getZipCode());
        order.setAddress(address);

        // Get the products ordered
        Set<OrderItem> orderItems = achat.getOrderItems();

        // Populate order with orderItems
        orderItems.forEach(item -> {
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setProduct(item.getProduct());
            newOrderItem.setQuantity(item.getQuantity());
          //  order.addOrderItem(newOrderItem);

            // Update the quantity of the ordered product in the database
            try {
                Product product = productService.findProdById(item.getProduct().getId());
                int newQuantity = product.getQuantity() - item.getQuantity();
                product.setQuantity(newQuantity);
                productService.save(product);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Add the order to the database
        orderService.save(order);

        return new Reponseachat(order.getId().toString());
    }


}

