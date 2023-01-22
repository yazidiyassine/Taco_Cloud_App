package tacos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
public class TacoOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date placedAt;
	@NotBlank(message = "Delivery name is required!")
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

	private List<Taco> tacos = new ArrayList<>();

	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}
}