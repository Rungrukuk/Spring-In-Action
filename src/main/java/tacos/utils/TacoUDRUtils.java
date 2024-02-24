package tacos.utils;

import tacos.domain.Ingredient;
import tacos.domain.IngredientUDT;
import tacos.domain.Taco;
import tacos.domain.TacoUDT;

import java.util.UUID;

public class TacoUDRUtils {

    public static Ingredient toIngredient(IngredientUDT ingredientUDT) {
        String id = UUID.randomUUID().toString();
        String name = ingredientUDT.getName();
        Ingredient.Type type = ingredientUDT.getType();

        return new Ingredient(id, name, type);
    }

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

    public static TacoUDT toTacoUDT(Taco taco) {
        return new TacoUDT(taco.getName(), taco.getIngredients());
    }

    public static Taco toTaco(TacoUDT tacoUDT) {
        Taco taco = new Taco();
        taco.setName(tacoUDT.getName());
        taco.setIngredients(tacoUDT.getIngredients());
        return taco;
    }
}
