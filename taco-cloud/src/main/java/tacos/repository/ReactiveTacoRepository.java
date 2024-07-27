package tacos.repository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository;
// import org.springframework.lang.NonNull;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import tacos.domain.Taco;

@Repository
public interface ReactiveTacoRepository extends ReactiveCrudRepository<Taco, Long> {
}
