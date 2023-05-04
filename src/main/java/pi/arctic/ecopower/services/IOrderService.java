package pi.arctic.ecopower.services;

import pi.arctic.ecopower.entities.Order;
import pi.arctic.ecopower.entities.Product;
import pi.arctic.ecopower.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IOrderService {
    Order createOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    List<Order> getOrdersByUser(User user);

    Order findOrderById(Long orderId);
    void updateOrder(Order order);

    void save(Order order);

    Order updateeOrder(Order order);



}


