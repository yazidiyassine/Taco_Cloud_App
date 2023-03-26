package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import lombok.Data;

@Data
//@Table("Taco_Cloud_Order")
//@Table
@Table("tacoorder")
public class TacoOrder implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	private UUID id = Uuids.timeBased();
	
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

	@Column("tacos")
	private List<TacoUDT> tacos = new ArrayList<>();

	public void addTaco(TacoUDT taco) {
		this.tacos.add(taco);
	}
}