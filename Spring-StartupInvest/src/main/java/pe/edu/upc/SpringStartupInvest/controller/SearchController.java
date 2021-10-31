package pe.edu.upc.SpringStartupInvest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.SpringStartupInvest.model.entity.Startup;
import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;

@Controller
@RequestMapping("/search") // Dominio base
public class SearchController {

	@Autowired
	private StartupService startupService;

	@PostMapping("startup")
	public String searchStartup(Model model, @ModelAttribute("startupSearch") Startup startupSearch) {
		List<Startup> startups = new ArrayList<Startup>();

		startupSearch.setName(startupSearch.getName().trim());

		startups = startupService.findByNameStartup(startupSearch.getName());
		if (startups.size() > 0) {

			model.addAttribute("startups", startups);
			model.addAttribute("size",startups.size());
			model.addAttribute("word", startupSearch.getName());
			return "dashboard/dashboard-investor-search";

		} else {
			model.addAttribute("word", startupSearch.getName());
			return "dashboard/dashboard-investor-search-error";
		}
	}

}
