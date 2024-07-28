package tacos.reactor.repository;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import tacos.reactor.domain.ReactiveUser;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@Repository
public interface ReactiveUserRepository extends ReactiveCrudRepository<ReactiveUser, Long> {
    Mono<ReactiveUser> findByUsername(String username);
}
