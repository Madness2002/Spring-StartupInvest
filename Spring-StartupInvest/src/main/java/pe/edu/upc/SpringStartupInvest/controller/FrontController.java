package pe.edu.upc.SpringStartupInvest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.SpringStartupInvest.model.entity.Category;
import pe.edu.upc.SpringStartupInvest.service.crud.CategoryService;

@Controller
@RequestMapping("/")
public class FrontController {
	@Autowired
	private CategoryService	categoryService;	
	@GetMapping("startupinvest")
	public String index() {
		return "index/index";
	}

	
	@GetMapping("startupinvest/home")
	public String home(Model model) {
		
		try {
			List<Category> categories = new ArrayList<>();
			categories = categoryService.findByState(true);
			model.addAttribute("categories", categories);
		} catch (Exception e) {
			
		}
		
		
		return "dashboard/dashboard-investor";
	}
	


	
}
