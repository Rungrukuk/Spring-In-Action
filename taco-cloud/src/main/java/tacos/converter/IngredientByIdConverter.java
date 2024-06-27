package tacos.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
// -------------------------------- Cassandra --------------------------------
// import tacos.domain.IngredientUDT;
// import tacos.repository.IngredientRepository;

// @Component
// public class IngredientByIdConverter implements Converter<String, IngredientUDT> {
//     private IngredientRepository ingredientRepo;

//     @Autowired
//     public IngredientByIdConverter(IngredientRepository ingredientRepo) {
//         this.ingredientRepo = ingredientRepo;
//     }

//     @Override
//     public IngredientUDT convert(String id) {
//         return new IngredientUDT(ingredientRepo.findById(id).orElse(null).getName(),
//                 ingredientRepo.findById(id).orElse(null).getType());
//     }
// }
// -------------------------------- Cassandra --------------------------------
import tacos.domain.Ingredient;
import tacos.repository.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(@NonNull String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
