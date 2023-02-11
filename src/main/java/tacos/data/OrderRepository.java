package tacos.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
	@Query("Order o where o.deliveryCity='Seattle'")
	List<TacoOrder> readOrdersDeliveredInSeattle();
}
