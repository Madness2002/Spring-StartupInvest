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

import pe.edu.upc.SpringStartupInvest.model.entity.Startup;
import pe.edu.upc.SpringStartupInvest.service.crud.CategoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;

@Controller
@RequestMapping("/startupinvest/startups")
public class StartupController {

	@Autowired
	private StartupService startupService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String list(Model model) {

		try {
			List<Startup> lista = new ArrayList<>();
			lista = startupService.getAll();
			
			model.addAttribute("Startup", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	
	
	@GetMapping("{id}/view")
	public String viewStartup(Model model, @PathVariable("id") Integer id) {
		try {
			if (startupService.existsById(id)) {
				Optional<Startup> optional = startupService.findById(id);
			//	model.addAttribute("", optional);
				
				return "startup/startup-investor-view";
			}

		} catch (Exception e) {
			
			
			e.getMessage();
			return "redirect:/startupinvest/home";
		}

		return "startup/startup-investor-view";
	}
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("newStartup")
	public String insertar(Model model, @Valid @ModelAttribute("startup") Startup startup) {
		try {

			Startup startupSaved = startupService.create(startup);
			model.addAttribute("Startup", startupSaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (startupService.existsById(id)) {
				Optional<Startup> optional = startupService.findById(id);
				model.addAttribute("editStartup", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("startup") Startup startup) {
		try {
			startupService.update(startup);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/startups";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (startupService.existsById(id)) {
				startupService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/startups";
	}

}
