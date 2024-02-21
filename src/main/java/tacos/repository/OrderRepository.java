package tacos.repository;

import tacos.domain.TacoOrder;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {
    // ? CRUD Repository already have this methods by default - This Interface can
    // work with both JPA and JDCB-Data
}
