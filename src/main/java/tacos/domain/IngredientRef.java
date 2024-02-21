package tacos.domain;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table // * Again not necessary but it's a good practice
public class IngredientRef {

    private final String ingredient;

}
