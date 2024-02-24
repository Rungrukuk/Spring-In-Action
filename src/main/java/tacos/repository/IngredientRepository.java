package tacos.repository;

// import java.util.Optional;

// import org.springframework.data.repository.Repository;

import org.springframework.data.repository.CrudRepository;

import tacos.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    // ? CRUD Repository already have this methods by default - This Interface can
    // work with both JPA and JDCB-Data

    // Iterable<Ingredient> findAll();

    // Optional<Ingredient> findById(String id);

    // Ingredient save(Ingredient ingredient);
}