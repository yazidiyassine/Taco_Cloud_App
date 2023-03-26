package tacos;


import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

@Data
@Table("Taco_Ingredients")
public class IngredientRef {
	private final String ingredient;
}
