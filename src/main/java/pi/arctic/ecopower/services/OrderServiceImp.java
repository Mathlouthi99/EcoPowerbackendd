package pi.arctic.ecopower.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.entities.Order;
import pi.arctic.ecopower.entities.OrderStatus;
import pi.arctic.ecopower.entities.Product;
import pi.arctic.ecopower.entities.User;
import pi.arctic.ecopower.repositories.OrderRepo;
import pi.arctic.ecopower.repositories.UserRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrderServiceImp implements IOrderService {

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private EmailService emailService;


    @Override
    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }
    @Override
    public void updateOrder(Order order) {
        orderRepository.save(order);
    }
    @Override
    public Order createOrder(Order order) {
        // You can perform any necessary validations here
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order updateeOrder(Order order) {
        Order updatedOrder = orderRepository.save(order);
        if (updatedOrder.getStatus() == OrderStatus.CONFIRMED && updatedOrder.getInstallationDate() != null) {
            emailService.sendInstallationConfirmation(updatedOrder.getUser().getEmail(), updatedOrder.getInstallationDate());
        }
        return updatedOrder;
    }

}