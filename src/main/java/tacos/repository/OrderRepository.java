package tacos.repository;

import tacos.domain.TacoOrder;

public interface OrderRepository {
    
    TacoOrder save(TacoOrder order);

}
