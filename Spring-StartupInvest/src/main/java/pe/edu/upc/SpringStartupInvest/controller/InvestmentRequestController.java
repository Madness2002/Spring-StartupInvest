package pe.edu.upc.SpringStartupInvest.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import pe.edu.upc.SpringStartupInvest.model.entity.InvestmentRequest;
import pe.edu.upc.SpringStartupInvest.model.entity.TypeInvestment;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestmentRequestService;
import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeInvestmentService;

@Controller
@RequestMapping("/startupinvest/investmentRequests")
public class InvestmentRequestController {

	@Autowired
	private InvestmentRequestService investmentRequestService;

	
	@Autowired
	private TypeInvestmentService typeInvestmentService;

	@Autowired
	private StartupService startupService;
	
	@GetMapping
	public String list(Model model) {

		try {
			List<InvestmentRequest> lista = new ArrayList<>();
			lista = investmentRequestService.getAll();
			model.addAttribute("investmentRequests", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	

	@PostMapping("startups/view/profile/newInvestmentRequest")
	public String insertar(Model model, @ModelAttribute("investmentRequest") InvestmentRequest investmentRequest) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			Date expirationDate = formato.parse(investmentRequest.getExpirationDateText());
			Date returnDate = formato.parse(investmentRequest.getReturnDateText());
			Date todayDate= new Date();
			
			investmentRequest.setDescription("a");
			investmentRequest.setCreationDate(todayDate);
			investmentRequest.setExpirationDate(expirationDate);
			investmentRequest.setReturnDate(returnDate);
			investmentRequest.setState(true);
			
			
		investmentRequestService.create(investmentRequest);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "redirect:/startupinvest/startups/"+investmentRequest.getStartup().getId().toString()+"/view/profile";
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (investmentRequestService.existsById(id)) {
				Optional<InvestmentRequest> optional = investmentRequestService.findById(id);
				model.addAttribute("editInvestmentRequest", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	
	
	
	
	
	
	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("investmentRequest") InvestmentRequest investmentRequest) {
		try {
			investmentRequestService.update(investmentRequest);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/investmentRequests";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (investmentRequestService.existsById(id)) {
				investmentRequestService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/investmentRequests";
	}

}
