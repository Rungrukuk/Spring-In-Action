package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import tacos.domain.Taco;
import tacos.domain.TacoOrder;
import tacos.domain.User;
import tacos.messaging.OrderMessagingService;
import tacos.repository.OrderRepository;
import tacos.repository.TacoRepository;
import tacos.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
public class OrderApiController {

    private final OrderRepository orderRepo;
    private final TacoRepository tacoRepo;
    private final OrderMessagingService messageService;
    private final UserRepository userRepo;

    @Autowired
    public OrderApiController(OrderRepository orderRepo, TacoRepository tacoRepo, UserRepository userRepo,
            OrderMessagingService messageService) {
        this.orderRepo = orderRepo;
        this.tacoRepo = tacoRepo;
        this.messageService = messageService;
        this.userRepo = userRepo;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public TacoOrder postOrder(@RequestBody @Valid TacoOrder order) {
        // Set a unique ID for the order
        Long maxId = orderRepo.findMaxId();
        order.setId(maxId + 1);

        List<Taco> savedTacos = new ArrayList<>();
        for (Taco taco : order.getTacos()) {
            if (taco.getId() != null) {
                Optional<Taco> tacoOptional = tacoRepo.findById(taco.getId());
                if (tacoOptional.isPresent()) {
                    savedTacos.add(tacoOptional.get());
                } else {
                    throw new IllegalArgumentException("Taco not found with ID: " + taco.getId());
                }
            } else {
                savedTacos.add(tacoRepo.save(taco));
            }
        }

        Optional<User> userOptional = userRepo.findById(order.getUser().getId());
        User user = userOptional
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + order.getUser().getId()));

        order.setTacos(savedTacos);
        order.setUser(user);

        messageService.sendOrder(order);
        log.info(order.toString());
        return orderRepo.save(order);
    }
}
