package tacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.repository.IngredientRepository;
import tacos.repository.TacoRepository;
import tacos.repository.UserRepository;

import java.util.Arrays;

// import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;

import tacos.domain.Ingredient;
import tacos.domain.Ingredient.Type;
import tacos.domain.Taco;
import tacos.domain.User;

@Configuration
public class DataLoaderConfig {
        // @Bean
        //
        // +++++++++++++++++++++++++++++++++Profiles+++++++++++++++++++++++++++++++++++
        // @Profile("dev") this means that This code will work only in the
        // development
        // @Profile({"dev", "qa"}) This is for dev and qa profiles
        // @Profile("!prod") if profile is not prod
        // * Also profile annotations can be used on classes too

        // ---------------------------------Profiles-----------------------------------
        // public ApplicationRunner dataLoader(IngredientRepository repo) {
        // return args -> {
        // repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        // repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        // repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        // repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        // repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        // repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
        // repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
        // repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        // repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
        // repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
        // };
        // }

        @Bean
        public CommandLineRunner dataLoader(
                        IngredientRepository repo,
                        UserRepository userRepo,
                        PasswordEncoder encoder,
                        TacoRepository tacoRepo) {
                return args -> {
                        Ingredient flourTortilla = new Ingredient(
                                        "FLTO", "Flour Tortilla", Type.WRAP);
                        Ingredient cornTortilla = new Ingredient(
                                        "COTO", "Corn Tortilla", Type.WRAP);
                        Ingredient groundBeef = new Ingredient(
                                        "GRBF", "Ground Beef", Type.PROTEIN);
                        Ingredient carnitas = new Ingredient(
                                        "CARN", "Carnitas", Type.PROTEIN);
                        Ingredient tomatoes = new Ingredient(
                                        "TMTO", "Diced Tomatoes", Type.VEGGIES);
                        Ingredient lettuce = new Ingredient(
                                        "LETC", "Lettuce", Type.VEGGIES);
                        Ingredient cheddar = new Ingredient(
                                        "CHED", "Cheddar", Type.CHEESE);
                        Ingredient jack = new Ingredient(
                                        "JACK", "Monterrey Jack", Type.CHEESE);
                        Ingredient salsa = new Ingredient(
                                        "SLSA", "Salsa", Type.SAUCE);
                        Ingredient sourCream = new Ingredient(
                                        "SRCR", "Sour Cream", Type.SAUCE);
                        repo.save(flourTortilla);
                        repo.save(cornTortilla);
                        repo.save(groundBeef);
                        repo.save(carnitas);
                        repo.save(tomatoes);
                        repo.save(lettuce);
                        repo.save(cheddar);
                        repo.save(jack);
                        repo.save(salsa);
                        repo.save(sourCream);

                        Taco taco1 = new Taco();
                        taco1.setId((long) 1);
                        taco1.setName("Carnivore");
                        taco1.setIngredients(Arrays.asList(
                                        flourTortilla, groundBeef, carnitas,
                                        sourCream, salsa, cheddar));
                        tacoRepo.save(taco1);
                        Taco taco2 = new Taco();
                        taco2.setId((long) 2);
                        taco2.setName("Bovine Bounty");
                        taco2.setIngredients(Arrays.asList(
                                        cornTortilla, groundBeef, cheddar,
                                        jack, sourCream));
                        tacoRepo.save(taco2);
                        Taco taco3 = new Taco();
                        taco3.setId((long) 3);
                        taco3.setName("Veg-Out");
                        taco3.setIngredients(Arrays.asList(
                                        flourTortilla, cornTortilla, tomatoes,
                                        lettuce, salsa));
                        tacoRepo.save(taco3);
                        User user = new User(
                                        "Rungrukuk",
                                        "$2a$12$hCuqZAThwyEqkwWt9kpN9uSgeDEybz1ucNoOkif2CZSxXMW6.PPQ2",
                                        "Kamal Azizov",
                                        "street",
                                        "city",
                                        "state",
                                        "zip",
                                        "phoneNumber");
                        user.setId((long) 1);
                        userRepo.save(user);
                };
        }

}
