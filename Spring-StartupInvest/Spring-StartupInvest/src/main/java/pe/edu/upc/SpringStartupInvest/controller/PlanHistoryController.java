package pe.edu.upc.SpringStartupInvest.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.SpringStartupInvest.model.entity.PlanHistory;
import pe.edu.upc.SpringStartupInvest.service.crud.PlanHistoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeCardService;

@Controller
@RequestMapping("/startupinvest/plansHistory")
public class PlanHistoryController {

	@Autowired
	private PlanHistoryService planHistoryService;

	@Autowired
	private TypeCardService typeCardService;

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

	@PostMapping("startups/view/profile/newPlanHistory")
	public String insertar(Model model, @ModelAttribute("planHistory") PlanHistory planHistory) {
		try {
			int planDays = planHistory.getPlan().getDuration();
			Date todayDate = new Date();
			Date expirationDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(expirationDate);
			c.add(Calendar.DATE, planDays);
			expirationDate = c.getTime();

			planHistory.setAcquistionDate(todayDate);
			planHistory.setExpirationDate(expirationDate);

			planHistory.setTypeCard(typeCardService.findById(40).get());

			planHistoryService.create(planHistory);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/startups/"+planHistory.getStartup().getId().toString()+"/view/profile";
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
