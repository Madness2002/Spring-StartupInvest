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
import pe.edu.upc.SpringStartupInvest.util.startup.DateTimeUtil;

@Controller
@RequestMapping("/startupinvest")//dominio base 
public class AdministratorController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StartupService startupService;

	@GetMapping("administrator")// 
	public String index() {
		return "dashboard-administrator/dashboard-administrator";
	}
	
	@GetMapping("administrator/categories")// 
	public String categories() {
		return "dashboard-administrator/administrator-category";
	}
	
	@GetMapping("administrator/plans")// 
	public String plans() {
		return "dashboard-administrator/administrator-plan";
	}
	@GetMapping("administrator/typesCard")// 
	public String typesCard() {
		return "dashboard-administrator/administrator-type-card";
	}
	@GetMapping("administrator/typesInvestment")// 
	public String typesInvestment() {
		return "dashboard-administrator/administrator-type-investment";
	}
	
	
	
}
