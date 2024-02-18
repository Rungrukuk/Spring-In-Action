package tacos.repository;


import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tacos.domain.IngredientRef;
import tacos.domain.Taco;
import tacos.domain.TacoOrder;

@Repository
public class JdcbOrderRepository implements OrderRepository {

    JdbcOperations jdbcOperations;

    public JdcbOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    @Override
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf = 
        new PreparedStatementCreatorFactory(
            "insert into Taco_Order" 
            + "(delivery_name, delivery_street, delivery_city,"
            + "delivery_state, delivery_zip, cc_number, "
            + "cc_expiration, cc_cvv, placed_at) "
            + "values (?,?,?,?,?,?,?,?,?)",
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, 
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, 
            Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);
        order.setPlacedAt(new Date());
        PreparedStatementCreator psc = 
            pscf.newPreparedStatementCreator(
                Arrays.asList(
                    order.getDeliveryName(),
                    order.getDeliveryStreet(),
                    order.getDeliveryCity(),
                    order.getDeliveryState(),
                    order.getDeliveryZip(),
                    order.getCcNumber(),
                    order.getCcExpiration(),
                    order.getCcCVV(),
                    order.getPlacedAt()
                )
            );
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcOperations.update(psc, keyHolder);
            long orderId = keyHolder.getKey().longValue();
            order.setId(orderId);

            List<Taco> tacos = order.getTacos();
            for (Taco taco : tacos){
                saveTaco(orderId, taco);
            }
            return order;
    }

    private long saveTaco(Long orderId, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf =
            new PreparedStatementCreatorFactory(
                "insert into Taco "
                + "(name, created_at, taco_order) "
                + "values (?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG
            );
        pscf.setReturnGeneratedKeys(true);
            PreparedStatementCreator psc =
            pscf.newPreparedStatementCreator(
                Arrays.asList(
                    taco.getName(),
                    taco.getCreatedAt(),
                    orderId
                )
            );

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);
        
        saveIngredientRefs(tacoId, taco.getIngredients());
        
        return tacoId;
       }

    private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredientRefs) 
    {
        for (IngredientRef ingredientRef : ingredientRefs) {
            jdbcOperations.update(
                "insert into Ingredient_Ref (ingredient, taco) "
                + "values (?, ?)",
                ingredientRef.getIngredient(), tacoId
            );
        }

    }

}
