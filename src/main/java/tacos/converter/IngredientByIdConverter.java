package tacos.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.domain.IngredientUDT;
import tacos.repository.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {
    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public IngredientUDT convert(String id) {
        return new IngredientUDT(ingredientRepo.findById(id).orElse(null).getName(),
                ingredientRepo.findById(id).orElse(null).getType());
    }
}
