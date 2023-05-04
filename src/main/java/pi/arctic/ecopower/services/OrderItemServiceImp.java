package pi.arctic.ecopower.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.entities.OrderItem;
import pi.arctic.ecopower.repositories.OrderItemRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderItemServiceImp implements IOrderItemService{
    private OrderItemRepo orderItem;
    @Override
    public void add(OrderItem item) {
        orderItem.save(item);
    }

    @Override
    public OrderItem update(OrderItem item) {

        return orderItem.save(item);
    }

    @Override
    public List<OrderItem> getAll() {
        return orderItem.findAll();
    }

    @Override
    public OrderItem getById(long id) {
        return orderItem.findById(id).orElse(null);
    }

    @Override
    public void remove(long id) {
        orderItem.deleteById(id);

    }
}
