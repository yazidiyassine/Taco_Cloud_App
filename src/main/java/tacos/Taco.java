package tacos;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
//@Table
@Entity

public class Taco {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 5, message = "Name of the taco must be at least 5 characters long!")
	private String name;

	private Date createdAt = new Date();

	@NotNull
	@Size(min = 1, message = "There should be at least 1 ingredient !")
	@ManyToMany()
	private List<Ingredient> ingredients;

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
}
