package tacos.reactor.repository;

import java.util.ArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import tacos.reactor.domain.ReactiveIngredient;
import tacos.reactor.domain.ReactiveIngredient.Type;
// import tacos.reactor.repository.RectiveIngredientRepository;

@DataR2dbcTest
public class IngredientRepositoryTest {
    @Autowired
    RectiveIngredientRepository ingredientRepo;

    @BeforeEach
    public void setup() {
        Flux<ReactiveIngredient> deleteAndInsert = ingredientRepo.deleteAll()
                .thenMany(ingredientRepo.saveAll(
                        Flux.just(
                                new ReactiveIngredient("FLTO", "Flour Tortilla", Type.WRAP),
                                new ReactiveIngredient("GRBF", "Ground Beef", Type.PROTEIN),
                                new ReactiveIngredient("CHED", "Cheddar Cheese", Type.CHEESE))));

        StepVerifier.create(deleteAndInsert)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void shouldSaveAndFetchIngredients() {

        StepVerifier.create(ingredientRepo.findAll())
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(ingredients -> {
                    assertThat(ingredients).hasSize(3);
                    assertThat(ingredients).contains(
                            new ReactiveIngredient("FLTO", "Flour Tortilla", Type.WRAP));
                    assertThat(ingredients).contains(
                            new ReactiveIngredient("GRBF", "Ground Beef", Type.PROTEIN));
                    assertThat(ingredients).contains(
                            new ReactiveIngredient("CHED", "Cheddar Cheese", Type.CHEESE));
                })
                .verifyComplete();

        StepVerifier.create(ingredientRepo.findBySlug("FLTO"))
                .assertNext(ingredient -> {
                    ingredient.equals(new ReactiveIngredient("FLTO", "Flour Tortilla",
                            Type.WRAP));
                });
    }

}
