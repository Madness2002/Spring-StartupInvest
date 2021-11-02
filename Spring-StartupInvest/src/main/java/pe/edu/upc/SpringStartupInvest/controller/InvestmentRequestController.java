package pe.edu.upc.SpringStartupInvest.controller;

import java.util.ArrayList;
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
@SessionAttributes("investmentRequest")
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

	

	@PostMapping("newInvestmentRequest")
	public String insertar(Model model,
			@Valid @ModelAttribute("investmentRequest") InvestmentRequest investmentRequest) {
		try {
			InvestmentRequest investmentRequestSaved = investmentRequestService.create(investmentRequest);
			model.addAttribute("investmentRequest", investmentRequestSaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
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
