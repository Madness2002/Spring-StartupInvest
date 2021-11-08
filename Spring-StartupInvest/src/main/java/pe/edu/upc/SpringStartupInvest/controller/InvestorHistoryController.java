package pe.edu.upc.SpringStartupInvest.controller;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorHistoryService;

@Controller
@RequestMapping("/startupinvest/investorsHistory")
public class InvestorHistoryController {
	@Autowired
	private InvestorHistoryService investorHistoryService;

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

	@PostMapping("newInvestorHistory")
	public String insertar(@ModelAttribute("investorHistory") InvestorHistory investorHistory) {
		try {
			Date todayDate = new Date();

			
			investorHistory.setDate(todayDate);
			investorHistory.setState(true);
			
			System.out.println(investorHistory.getId()); 
			System.out.println(investorHistory.getDate());
			System.out.println(investorHistory.getAmount());
			System.out.println(investorHistory.getState());
			System.out.println(investorHistory.getInvestmentRequest().getId());
			System.out.println(investorHistory.getInvestor().getId());
			System.out.println(investorHistory.getTypeCard().getId());
				
			investorHistoryService.create(investorHistory);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		return "redirect:/startupinvest/home";
		
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
