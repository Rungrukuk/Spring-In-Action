package tacos.domain;

// import org.springframework.data.annotation.Id; // * Both for JDBC and Mongo
// import org.springframework.data.mongodb.core.mapping.Document; // * Mongo

import jakarta.persistence.Entity; // --- JPA
import jakarta.persistence.Id; // --- JPA 

//  * import org.springframework.data.cassandra.core.mapping.PrimaryKey;   --- Cassandra
//  * import org.springframework.data.cassandra.core.mapping.Table;   --- Cassandra

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity // --- JPA
// * @Table("ingredients") --- JDBC and Cassandra
// @Document(collection = "ingredients")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {
    // * @Id --- JPA
    // * @PrimaryKey --- Cassandra
    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
