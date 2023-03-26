/*package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {
	private JdbcTemplate jdbcTemplate;

	public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
	}

	@Override
	public Optional<Ingredient> findById(String id) {
		List<Ingredient> results = jdbcTemplate.query("select id, name, type from Ingredient where id=?",
				this::mapRowToIngredient, id);
		return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
	}

	public Ingredient mapRowToIngredient(ResultSet row, int rownum) throws SQLException {
		return new Ingredient(row.getString("id"), row.getString("name"),
				Ingredient.Type.valueOf(row.getString("type")));
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbcTemplate.update("insert into Ingredient (id, name, type) values(?, ?, ?);", ingredient.getId(),
				ingredient.getName(), ingredient.getType());
		return ingredient;
	}

	@Override
	public <S extends Ingredient> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Ingredient> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Ingredient entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Ingredient> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
}*/
