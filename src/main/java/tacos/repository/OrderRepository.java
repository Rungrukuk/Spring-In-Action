package tacos.repository;

import tacos.domain.TacoOrder;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, String> {
    // ? CRUD Repository already have this methods by default - This Interface can
    // work with both JPA and JDCB-Data

    // * Spring is clever enough to create this method by parsing it's name
    List<TacoOrder> findByDeliveryZip(String Zip);

    /*
     * Some other Interesting examples that Spring can craete methods by parsing the
     * Suppose that you need to query
     * for all orders delivered to a given ZIP code within a given date range. In
     * that case, Here how you should name the method
     */

    // ? Instead of read get or find are also available
    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    /*
     * As you can see, the verb in readOrdersByDeliveryZipAndPlacedAtBetween()
     * is read. Spring Data also understands find, read, and get as synonymous for
     * fetching
     * one or more entities. Alternatively, you can also use count as the verb if
     * you want the
     * method to return only an int with the count of matching entities.
     */

    /*
     * Although the subject of the method is optional, here it says Orders. Spring
     * Data
     * ignores most words in a subject, so you could name the method readPuppiesBy…
     * and
     * it would still find TacoOrder entities, because that is the type that
     * CrudRepository is
     * parameterized with.
     */

    // As alternatives for IgnoringCase and IgnoresCase, you can place either
    // AllIgnoringCase or AllIgnoresCase on the method to ignore case for all String
    // comparisons.
    // For example, consider the following method:
    List<TacoOrder> findByDeliveryNameAndDeliveryCityAllIgnoreCase(
            String deliveryName, String deliveryCity);

    // Finally, you can also place OrderBy at the end of the method name to sort the
    // results
    // by a specified column. For example, to order by the deliveryName property,
    // use
    // the following code:
    List<TacoOrder> findByDeliveryCityOrderByDeliveryName(String city);

    // Although the naming convention can be useful for relatively simple queries,
    // it doesn’t
    // take much imagination to see that method names could get out of hand for more
    // complex queries. In that case, feel free to name the method anything you want
    // and
    // annotate it with @Query to explicitly specify the query to be performed when
    // the
    // method is called, as this example shows:
    @Query("SELECT o FROM TacoOrder o WHERE o.deliveryCity='Seattle'")
    List<TacoOrder> readOrdersDeliveredInSeattle();
}
