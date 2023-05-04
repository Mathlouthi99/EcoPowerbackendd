package pi.arctic.ecopower.services;

import pi.arctic.ecopower.entities.OrderItem;

import java.util.List;

public interface IOrderItemService {
    void add(OrderItem item);
    OrderItem update(OrderItem item);
    List<OrderItem> getAll();
    OrderItem getById(long id);
    void remove(long id);
}
