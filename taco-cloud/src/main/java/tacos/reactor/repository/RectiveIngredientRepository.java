package tacos.reactor.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Mono;
import tacos.reactor.domain.ReactiveIngredient;

public interface RectiveIngredientRepository extends ReactiveCrudRepository<ReactiveIngredient, Long> {
    Mono<ReactiveIngredient> findBySlug(String slug);   
}
