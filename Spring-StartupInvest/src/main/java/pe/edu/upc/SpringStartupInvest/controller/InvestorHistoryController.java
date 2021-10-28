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

import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorHistoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeCardService;

@Controller
@RequestMapping("/startupinvest/investorsHistory")
@SessionAttributes("investorHistory")
public class InvestorHistoryController {
	@Autowired
	private InvestorHistoryService investorHistoryService;

	private TypeCardService typeCardService;

	@GetMapping
	public String list(Model model) {

		try {
			List<InvestorHistory> lista = new ArrayList<>();
			lista = investorHistoryService.getAll();
			model.addAttribute("investorsHistory", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@PostMapping("newInvestor")
	public String insertar(Model model, @Valid @ModelAttribute("investorHistory") InvestorHistory investorHistory) {
		try {
			investorHistory.setState(true);
			InvestorHistory investorHistorySaved = investorHistoryService.create(investorHistory);
			model.addAttribute("investorHistory", investorHistorySaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (investorHistoryService.existsById(id)) {
				Optional<InvestorHistory> optional = investorHistoryService.findById(id);
				model.addAttribute("editInvestorsHistory", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("investorHistory") InvestorHistory investorHistory) {
		try {
			investorHistoryService.update(investorHistory);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/investorsHistory";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (investorHistoryService.existsById(id)) {
				investorHistoryService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/investorsHistory";
	}

}
