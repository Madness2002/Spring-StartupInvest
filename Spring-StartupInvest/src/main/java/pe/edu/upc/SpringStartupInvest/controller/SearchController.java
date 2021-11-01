package pe.edu.upc.SpringStartupInvest.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.SpringStartupInvest.model.entity.Category;
import pe.edu.upc.SpringStartupInvest.model.entity.Startup;
import pe.edu.upc.SpringStartupInvest.service.crud.CategoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;
import pe.edu.upc.SpringStartupInvest.util.startup.DateTimeUtil;

@Controller
@RequestMapping("/search") // Dominio base
public class SearchController {

	@Autowired
	private StartupService startupService;

	@Autowired
	private CategoryService categoryService;

	@PostMapping("startup")
	public String searchStartup(Model model, @ModelAttribute("startupSearch") Startup startupSearch) throws Exception {
		List<Startup> startups = new ArrayList<Startup>();
		List<Category> categories = new ArrayList<>();
		startupSearch.setName(startupSearch.getName().trim());
		categories = categoryService.findByState(true);
		startups = startupService.findByNameStartup(startupSearch.getName());
		model.addAttribute("categories", categories);
		if (startups.size() > 0) {

			model.addAttribute("startups", startups);
			model.addAttribute("size", startups.size());
			model.addAttribute("word", "la palabra ("+startupSearch.getName()+")");
			model.addAttribute("intervalDate",new DateTimeUtil());
			return "dashboard/dashboard-investor-search";

		} else {
			model.addAttribute("word", "la palabra ("+startupSearch.getName()+")");
			model.addAttribute("intervalDate",new DateTimeUtil());
			return "dashboard/dashboard-investor-search-error";
		}

	}

	@PostMapping("startupByDateAndCategory")
	public String searchStartupByDateAndCategory(Model model, @ModelAttribute("intervalDate") DateTimeUtil intervalDate)
			throws Exception {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		List<Startup> startups = new ArrayList<Startup>();
		List<Category> categories = new ArrayList<>();
		String fechas[] = intervalDate.getIntervalDate().trim().split("-");
		Date startDate = formato.parse(fechas[0]);
		Date endDate = formato.parse(fechas[1]);
		categories = categoryService.findByState(true);
		startups = startupService.findByDateBetween(startDate, endDate);

		model.addAttribute("categories", categories);
		if (startups.size() > 0) {
			model.addAttribute("startupSearch",new Startup());
			model.addAttribute("startups", startups);
			model.addAttribute("size", startups.size());
			model.addAttribute("word", "el intervalo de tiempo ("+intervalDate.getIntervalDate()+")");
			return "dashboard/dashboard-investor-search";

		} else {
			model.addAttribute("startupSearch",new Startup());
			model.addAttribute("word", "el intervalo de tiempo ("+intervalDate.getIntervalDate()+")");
			return "dashboard/dashboard-investor-search-error";
			
			
		}

	}

}
