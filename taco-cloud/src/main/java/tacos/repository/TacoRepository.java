package tacos.repository;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.lang.NonNull;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.domain.Taco;
import tacos.repository.custom.CustomTacoRepository;

@Repository
public interface TacoRepository extends JpaRepository<Taco, Long>, CustomTacoRepository {
}
