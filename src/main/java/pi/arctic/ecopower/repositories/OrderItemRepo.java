package pi.arctic.ecopower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.arctic.ecopower.entities.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}
