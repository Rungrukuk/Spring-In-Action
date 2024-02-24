package tacos.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/*
 * import org.springframework.data.annotation.Id;  ---JDBC
 * import org.springframework.data.relational.core.mapping.Column;  ---JDBC
 * import org.springframework.data.relational.core.mapping.Table;  ---JDBC
 */

// * import jakarta.persistence.Entity;   --- JPA
// * import jakarta.persistence.GeneratedValue;   --- JPA
// * import jakarta.persistence.GenerationType;   --- JPA
// * import jakarta.persistence.Id;   --- JPA
// * import jakarta.persistence.ManyToMany;   --- JPA

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.uuid.Uuids;

// import org.springframework.data.annotation.Id; --- JDBC
// import org.springframework.data.relational.core.mapping.Table; --- JDBC

import lombok.Data;
import tacos.utils.TacoUDRUtils;

@Data
// @Entity
@Table("tacos")
public class Taco {

    // @Id --- JPA
    // @GeneratedValue(strategy = GenerationType.AUTO) --- JPA
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = Uuids.timeBased();

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    // @ManyToMany --- JPA
    @Column("ingredients")
    private List<IngredientUDT> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
    }

}
