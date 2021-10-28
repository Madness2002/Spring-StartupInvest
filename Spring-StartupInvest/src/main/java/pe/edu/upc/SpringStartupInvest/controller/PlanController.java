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
import pe.edu.upc.SpringStartupInvest.service.crud.PlanService;

@Controller
@RequestMapping("/startupinvest/plans")
@SessionAttributes("plan")
public class PlanController {

	@Autowired
	private PlanService planService;

	@GetMapping
	public String list(Model model) {

		try {
			List<Plan> lista = new ArrayList<>();
			lista = planService.getAll();
			model.addAttribute("plan", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@PostMapping("newPlan")
	public String insertar(Model model, @Valid @ModelAttribute("plan") Plan plan) {
		try {

			Plan planSaved = planService.create(plan);
			model.addAttribute("plan", planSaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (planService.existsById(id)) {
				Optional<Plan> optional = planService.findById(id);
				model.addAttribute("editPlan", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("plan") Plan plan) {
		try {
			planService.update(plan);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/plans";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (planService.existsById(id)) {
				planService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/plans";
	}

}
