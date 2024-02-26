package tacos.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.validation.Errors;

import tacos.domain.TacoOrder;
import tacos.domain.User;
import tacos.repository.OrderRepository;
import tacos.repository.UserRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderRepository orderRepo;
    private UserRepository userRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping

    public String processOrder(@Valid TacoOrder order, Errors errors,
            SessionStatus sessionStatus// , Principal principal
            // , Authentication authentication
            , @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            log.info(errors.toString());
            return "orderForm";
        }
        // User user = userRepo.findByUsername(principal.getName());
        // User user = (User) authentication.getPrincipal();
        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();
        // User user = (User) authentication.getPrincipal();
        order.setUser(user);
        order.setPlacedAt(new Date());
        orderRepo.save(order);
        log.info("Processing order: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}