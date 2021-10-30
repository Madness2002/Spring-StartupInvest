package pe.edu.upc.SpringStartupInvest.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.SpringStartupInvest.model.entity.Category;
import pe.edu.upc.SpringStartupInvest.model.entity.Startup;
import pe.edu.upc.SpringStartupInvest.service.crud.CategoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;
import pe.edu.upc.SpringStartupInvest.util.startup.CompareAmounth;

@Controller
@RequestMapping("/")
public class FrontController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StartupService startupService;

	@GetMapping("startupinvest")
	public String index() {
		return "index/index";
	}

	@GetMapping("startupinvest/home")
	public String home(Model model) {

		try {
			List<Category> categories = new ArrayList<>();
			List<Startup> startups = new ArrayList<>();
			categories = categoryService.findByState(true);
			startups = startupService.getAll();
			for (Startup startup : startups) {
				int id = startup.getId();
				double amounth = startupService.getAmounthInvestedById(id);
				Integer position = startupService.getPositionStartupById(id);
				startup.setAmounthTotalInvest(amounth);
				startup.setPosition(position);
			}

			Collections.sort(startups, new CompareAmounth());
			model.addAttribute("categories", categories);
			model.addAttribute("startups", startups);
		} catch (Exception e) {

		}

		return "dashboard/dashboard-investor";
	}

	@GetMapping("ga")
	public String prueba(Model model) {

		try {
			List<Category> categories = new ArrayList<>();
			categories = categoryService.findByState(true);
			model.addAttribute("category", new Category());
			model.addAttribute("categories", categories);
		} catch (Exception e) {

		}

		return "dashboard/prueba";
	}

}
