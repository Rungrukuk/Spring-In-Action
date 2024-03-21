package tacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;

import tacos.repository.IngredientRepository;

import org.springframework.boot.ApplicationRunner;

import tacos.domain.Ingredient;
import tacos.domain.Ingredient.Type;;

@Configuration
public class DataLoaderConfig {
    @Bean
    // +++++++++++++++++++++++++++++++++Profiles+++++++++++++++++++++++++++++++++++
    // @Profile("dev") this means that This code will work only in the development
    // @Profile({"dev", "qa"}) This is for dev and qa profiles
    // @Profile("!prod") if profile is not prod
    // * Also profile annotations can be used on classes too
    // ---------------------------------Profiles-----------------------------------
    public ApplicationRunner dataLoader(IngredientRepository repo) {
        return args -> {
            repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
            repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
            repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
            repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
            repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
            repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
            repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
            repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
            repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
            repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        };
    }
}
