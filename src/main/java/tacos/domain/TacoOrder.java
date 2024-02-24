package tacos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

// import jakarta.persistence.GeneratedValue;  --- JPA
// import jakarta.persistence.GenerationType;  --- JPA

// import org.springframework.data.annotation.Id;  --- JDBC
// import org.springframework.data.relational.core.mapping.Column;  --- JDBC
// import org.springframework.data.relational.core.mapping.Table;  --- JDBC

// import jakarta.persistence.Id;  --- JPA
// import jakarta.persistence.CascadeType;  --- JPA
// import jakarta.persistence.Entity;  --- JPA
// import jakarta.persistence.OneToMany;  --- JPA

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import lombok.Data;

@Data
// @Table //* @Table("Taco_Cloud_Order") to imply that this class will associate
// with Taco_Cloud_Order tabel in the database
// @Entity --- JPA
@Table("orders")
public class TacoOrder implements Serializable {

    // * private static final long serialVersionUID = 1L; I do not exactly know what
    // the hell this

    // @Id --- JPA
    // @GeneratedValue(strategy = GenerationType.AUTO) --- JPA
    @PrimaryKey
    private UUID id = Uuids.timeBased();

    private Date placedAt = new Date();
    // @Column("delivery_name") to explicitly map this property to delivery_name ---
    // JDBC
    // column but in our case Spring boot will automatically map them becuase of the
    // name of property

    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])", message = "Must beformatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    // @OneToMany(cascade = CascadeType.ALL) --- JPA
    @Column("tacos")
    private List<TacoUDT> tacos = new ArrayList<>();

    public void addTaco(TacoUDT taco) {
        this.tacos.add(taco);
    }
}
