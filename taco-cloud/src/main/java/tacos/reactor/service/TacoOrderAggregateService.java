package tacos.reactor.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import tacos.reactor.domain.ReactiveTaco;
import tacos.reactor.domain.ReactiveTacoOrder;
import tacos.reactor.repository.ReactiveOrderReposiory;
import tacos.reactor.repository.ReactiveTacoRepository;

@Service
@RequiredArgsConstructor
public class TacoOrderAggregateService {
    private final ReactiveTacoRepository tacoRepo;
    private final ReactiveOrderReposiory orderRepo;

    public Mono<ReactiveTacoOrder> save(ReactiveTacoOrder tacoOrder) {
        return Mono.just(tacoOrder)
                .flatMap(order -> {
                    List<ReactiveTaco> tacos = order.getTacos();
                    order.setTacos(new ArrayList<>());
                    return tacoRepo.saveAll(tacos)
                            .map(taco -> {
                                order.addTaco(taco);
                                return order;
                            }).last();
                })
                .flatMap(orderRepo::save);
    }

    public Mono<ReactiveTacoOrder> findById(Long id) {
        return orderRepo
                .findById(id)
                .flatMap(order -> {
                    return tacoRepo.findAllById(order.getTacoIds())
                            .map(taco -> {
                                order.addTaco(taco);
                                return order;
                            }).last();
                });
    }
}
