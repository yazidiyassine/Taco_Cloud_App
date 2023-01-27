package tacos.data;

import org.springframework.stereotype.Repository;

import tacos.TacoOrder;


public interface OrderRepository {
	TacoOrder save(TacoOrder tacoOrder);
}
