package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
//@Table("Taco_Cloud_Order")
//@Table
@Entity
public class TacoOrder implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date placedAt = new Date();
	@NotBlank(message = "Delivery name is required!")
	//@Column("customer_name")
	private String deliveryName;

	@NotBlank(message = "Street is required!")
	private String deliveryStreet;

	@NotBlank(message = "City is required!")
	private String deliveryCity;

	@NotBlank(message = "State is required!")
	private String deliveryState;

	@NotBlank(message = "ZIP code is required!")
	private String deliveryZip;

	@CreditCardNumber(message = "Not a valid credit card number!")
	private String ccNumber;

	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccExpiration;

	@Digits(fraction = 0, integer = 3, message = "Invalid CCV")
	private String ccCVV;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Taco> tacos = new ArrayList<>();

	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
}