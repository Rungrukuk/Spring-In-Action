package tacos.dto;

import lombok.Data;
import tacos.domain.Ingredient.Type;

@Data
public class IngredientDTO {
    private String id;
    private String name;
    private Type type;

    public IngredientDTO(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
