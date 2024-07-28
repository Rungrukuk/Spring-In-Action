package tacos.reactor.repository;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import tacos.domain.User;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@Repository
public interface ReactiveUserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByUsername(String username);
}
