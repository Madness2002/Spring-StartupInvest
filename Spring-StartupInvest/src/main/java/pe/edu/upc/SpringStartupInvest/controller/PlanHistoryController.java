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

import pe.edu.upc.SpringStartupInvest.model.entity.Plan;
import pe.edu.upc.SpringStartupInvest.model.entity.PlanHistory;
import pe.edu.upc.SpringStartupInvest.service.crud.PlanHistoryService;

@Controller
@RequestMapping("/startupinvest/plansHistory")
@SessionAttributes("planHistory")
public class PlanHistoryController {

	@Autowired
	private PlanHistoryService planHistoryService;
	
	
	
	@GetMapping
	public String list(Model model) {

		try {
			List<PlanHistory> lista = new ArrayList<>();
			lista = planHistoryService.getAll();
			model.addAttribute("planHistory", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@PostMapping("newPlanHistory")
	public String insertar(Model model, @Valid @ModelAttribute("planHistory") PlanHistory planHistory) {
		try {

			PlanHistory planHistorySaved = planHistoryService.create(planHistory);
			model.addAttribute("planHistory", planHistorySaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (planHistoryService.existsById(id)) {
				Optional<PlanHistory> optional = planHistoryService.findById(id);
				model.addAttribute("editPlanHistory", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("planHistory") PlanHistory planHistory) {
		try {
			planHistoryService.update(planHistory);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/plansHistory";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (planHistoryService.existsById(id)) {
				planHistoryService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/plansHistory";
	}
	
}
