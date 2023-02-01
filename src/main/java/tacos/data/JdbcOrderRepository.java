package tacos.data;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tacos.Ingredient;
import tacos.IngredientRef;
import tacos.Taco;
import tacos.TacoOrder;

@Repository
public class JdbcOrderRepository implements OrderRepository {

	private JdbcOperations jdbcOperations;

	public JdbcOrderRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Transactional
	@Override
	public TacoOrder save(TacoOrder order) {
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"insert into taco_order(deliveryName, deliveryStreet, deliveryCity,deliveryState, deliveryZip, ccNumber,ccExpiration, ccCVV, placedAt)values(?,?,?,?,?,?,?,?,?)",
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.TIMESTAMP);
		pscf.setReturnGeneratedKeys(true);
		order.setPlacedAt(new Date());
		PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(order.getDeliveryName(),
				order.getDeliveryStreet(), order.getDeliveryCity(), order.getDeliveryState(), order.getDeliveryZip(),
				order.getCcNumber(), order.getCcExpiration(), order.getCcCVV(), order.getPlacedAt()));
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long orderId = keyHolder.getKey().longValue();
		order.setId(orderId);

		List<Taco> tacos = order.getTacos();
		int i = 0;
		for (Taco taco : tacos) {
			saveTaco(orderId, i++, taco);
		}
		return order;
	}

	private long saveTaco(Long orderId, int orderKey, Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
				"insert into Taco(id, name , type)" + "value(?, ?, ?)", Types.VARCHAR,
				Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP);
		pscf.setReturnGeneratedKeys(true);

		PreparedStatementCreator psc = pscf
				.newPreparedStatementCreator(Arrays.asList(taco.getName(), taco.getCreatedAt(), orderId, orderKey));
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcOperations.update(psc, keyHolder);
		long tacoId = keyHolder.getKey().longValue();
		taco.setId(tacoId);

		//saveIngredientRefs(tacoId, taco.getIngredients());
		return tacoId;
	}
	

	private void saveIngredientRefs(Long tacoId, List<IngredientRef> ingredientRefs) {
		int key = 0;
		for (IngredientRef ingredientRef : ingredientRefs) {
			jdbcOperations.update(
					"insert into taco_ingredients(taco, ingredient)"
					+ "values(?, ?)",
						tacoId, ingredientRef.getIngredient()
					);
		}
	}
}
