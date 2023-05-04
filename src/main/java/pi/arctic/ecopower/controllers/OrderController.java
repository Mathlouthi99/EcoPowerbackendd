package pi.arctic.ecopower.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pi.arctic.ecopower.entities.Order;
import pi.arctic.ecopower.entities.OrderStatus;
import pi.arctic.ecopower.entities.User;
import pi.arctic.ecopower.repositories.OrderRepo;
import pi.arctic.ecopower.repositories.UserRepo;
import pi.arctic.ecopower.services.EmailService;
import pi.arctic.ecopower.services.IOrderService;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private UserRepo userRepository;


    @Autowired
    private IOrderService orderService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/{orderId}/select-installation-date")
    public ResponseEntity<Order> selectInstallationDate(
            @PathVariable Long orderId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate installationDate) {

        Order order = orderService.findOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        order.setInstallationDate(installationDate);
        order.setStatus(OrderStatus.CONFIRMED);
        orderService.updateOrder(order);


        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/{orderId}/send-installation-confirmation")
    public ResponseEntity<?> sendInstallationConfirmationEmail(@PathVariable Long orderId) {
        Order order = orderService.findOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        if (order.getInstallationDate() == null || order.getStatus() != OrderStatus.CONFIRMED) {
            return ResponseEntity.badRequest().build();
        }
        User user = order.getUser();
        if (user == null || user.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            emailService.sendEmail(user.getEmail(), "Installation Confirmation", "Your installation has been confirmed for " + order.getInstallationDate());
            System.out.println("Installation confirmation email sent successfully to " + user.getEmail());
        } catch (MessagingException e) {
            System.out.println("Failed to send installation confirmation email to " + user.getEmail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }

        return ResponseEntity.ok().build();
    }






    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody Order order, @RequestParam("user_id") int userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        order.setUser(user);
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order existingOrder = orderService.getOrderById(id);
        if (existingOrder == null) {
            return ResponseEntity.notFound().build();
        }
        order.setId(id);
        orderService.updateOrder(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable int userId) {
        User user = new User();
        user.setId(userId);
        List<Order> orders = orderService.getOrdersByUser(user);
        return ResponseEntity.ok(orders);
    }
}




