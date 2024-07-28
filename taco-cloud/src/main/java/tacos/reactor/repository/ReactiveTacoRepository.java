package tacos.reactor.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import tacos.domain.Taco;

@Repository
public interface ReactiveTacoRepository extends ReactiveCrudRepository<Taco, Long> {
}
