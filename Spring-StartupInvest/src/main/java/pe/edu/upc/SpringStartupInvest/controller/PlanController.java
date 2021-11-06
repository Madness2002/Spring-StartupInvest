package pe.edu.upc.SpringStartupInvest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.upc.SpringStartupInvest.model.entity.Plan;
import pe.edu.upc.SpringStartupInvest.service.crud.PlanService;

@Controller
@RequestMapping("/startupinvest")
@SessionAttributes("plan")
public class PlanController {
//IMPORTANTE: QUITAR EL data-dismiss="modal" PARA QUE SE PUEDA EJECUTAR EL EVENTO GUARDAR, EDITAR, ETC
	@Autowired
	private PlanService planService;

	@GetMapping("plans")
	public String list(Model model) {

		try {
			List<Plan> lista = new ArrayList<>();
			lista = planService.getAll();
			model.addAttribute("plans", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@GetMapping("administrator/plans")
	public String listAdministrator(Model model) {

		try {
			List<Plan> lista = new ArrayList<>();
			lista = planService.getAll();
			model.addAttribute("plans", lista);
			model.addAttribute("editPlan", new Plan());
			model.addAttribute("plan", new Plan());
			// model.addAttribute("habilitarPlan", new Plan());
		} catch (Exception e) {
			e.getMessage();
		}

		return "dashboard-administrator/administrator-plan";
	}

	@PostMapping("newPlan")
	public String insert(Model model, @Valid @ModelAttribute("plan") Plan plan) {
		try {

			plan.setState(true);
			 planService.create(plan);
			//model.addAttribute("plan", planSaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/administrator/plans";
	}


	/// startupinvest/administrator/plans/{id}/edit/loadEdit
	@GetMapping("administrator/plans/{id}/edit/loadEdit")
	public String saveEdit(Model model, @PathVariable(name = "id") Integer id) {
		try {
			var plan = planService.findById(id);

			if (plan.get().getState().equals(true)) {
				plan.get().setState(false);
			} else
				plan.get().setState(true);

			planService.update(plan.get());

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/administrator/plans";
	}
//CARGAR DATOS
	@RequestMapping(value ="administrator/plans/{id}/edit", method = RequestMethod.PATCH)
	public void updateGo(Model model, @PathVariable("id") Integer id, ModelMap modelMap) {
		try {
			if (planService.existsById(id)) {
				Optional<Plan>plan=planService.findById(id);
				modelMap.put("editPlan", plan);
			}

		} catch (Exception e) {
			e.getMessage();
		}

		//return "administrator-plan";
	}
	
	
	@PostMapping("administrator/plans/edit")
	public String update(Model model, @ModelAttribute("editPlan") Plan plan) {
		try {
			if (planService.existsById(plan.getId())) {
				planService.update(plan);
			}

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/administrator/plans";
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
