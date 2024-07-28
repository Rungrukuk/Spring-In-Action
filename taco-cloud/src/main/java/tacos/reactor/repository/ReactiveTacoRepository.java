package tacos.reactor.repository;

import org.springframework.stereotype.Repository;

import tacos.reactor.domain.ReactiveTaco;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

@Repository
public interface ReactiveTacoRepository extends ReactiveCrudRepository<ReactiveTaco, Long> {
}
