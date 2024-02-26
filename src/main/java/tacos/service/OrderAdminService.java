//? THis class created for Pre and Post Authorize Annotations

package tacos.service;

// import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import tacos.repository.OrderRepository;

@Service
public class OrderAdminService {
    OrderRepository orderRepository;

    public OrderAdminService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // @PostAuthorize("hasRole('ADMIN') || " +
    // "returnObject.user.username == authentication.name") --- This is for after
    // method executes
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
