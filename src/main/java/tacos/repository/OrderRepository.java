package tacos.repository;

import tacos.domain.TacoOrder;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {
    // ? CRUD Repository have already been created necessary operateions
}
