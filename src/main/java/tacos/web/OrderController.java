package tacos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import lombok.extern.slf4j.Slf4j;
import tacos.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
	
	 @GetMapping("/current")
	  public String orderForm(Model model) {
	    model.addAttribute("tacoOrder", new TacoOrder());
	    return "orderForm";
	  }
	 @PostMapping
	  public String processOrder(@Valid TacoOrder tacoOrder, Errors errors) {
	    if (errors.hasErrors()) {
	      return "orderForm";
	    }
	    
	    log.info("Order submitted: " + tacoOrder);
	    return "redirect:/";
	  }
}