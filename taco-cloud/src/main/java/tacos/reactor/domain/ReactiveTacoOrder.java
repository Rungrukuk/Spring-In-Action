package tacos.reactor.domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class ReactiveTacoOrder {
    @Id
    private Long id;

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private Set<Long> tacoIds = new LinkedHashSet<>();
    private List<ReactiveTaco> tacos = new ArrayList<>();

    public void addTaco(ReactiveTaco taco) {
        this.tacos.add(taco);
    }
}
