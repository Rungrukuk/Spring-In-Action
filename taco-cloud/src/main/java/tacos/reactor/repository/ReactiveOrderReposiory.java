package tacos.reactor.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import tacos.reactor.domain.ReactiveTacoOrder;

public interface ReactiveOrderReposiory extends ReactiveCrudRepository<ReactiveTacoOrder, Long> {

}
