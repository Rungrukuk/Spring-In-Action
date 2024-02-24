package tacos.domain;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.hibernate.validator.constraints.CreditCardNumber;
// import org.springframework.data.annotation.Id;
// import org.springframework.data.relational.core.mapping.Column;
// import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

import java.util.ArrayList;
import lombok.Data;

@Data
// @Table //* @Table("Taco_Cloud_Order") to imply that this class will associate
// with Taco_Cloud_Order tabel in the database
@Entity
public class TacoOrder {

    // * private static final long serialVersionUID = 1L; I do not exactly know what
    // the hell this

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;
    // @Column("delivery_name") to explicitly map this property to delivery_name
    // column but in our case Spring boot will automatically map them becuase of the
    // name of property
    @NotBlank(message = "Delivery  name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip  code is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid card number")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])", message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
