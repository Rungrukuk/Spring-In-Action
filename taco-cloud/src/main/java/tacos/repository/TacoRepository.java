package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.domain.Taco;
import tacos.repository.custom.CustomTacoRepository;

@Repository
public interface TacoRepository extends JpaRepository<Taco, Long>, CustomTacoRepository {
}
