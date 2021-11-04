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
import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;
import pe.edu.upc.SpringStartupInvest.model.entity.Startup;
import pe.edu.upc.SpringStartupInvest.service.crud.CategoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorHistoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;
import pe.edu.upc.SpringStartupInvest.util.startup.CompareAmounth;
import pe.edu.upc.SpringStartupInvest.util.startup.DateTimeUtil;

@Controller
@RequestMapping("/")//dominio base 
public class FrontController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StartupService startupService;

	
	@Autowired
	private InvestorHistoryService investorHistoryService;
	@GetMapping("startupinvest")// 
	public String index() {
		return "index/index";
	}

	@GetMapping("startupinvest/home")
	public String home(Model model) {

		try {		
			List<Category> categories = new ArrayList<>();
			List<Startup> startupsMostPopular, startupsRecently,startups = new ArrayList<>();
			List<InvestorHistory>investments=new ArrayList<>();
			
			categories = categoryService.findByState(true);
			startups=startupService.getAll();
			startupsMostPopular = startupService.listStartupsMostPopular();
			startupsRecently = startupService.findByDateRecently();
			
			//Usamos el ID=1001, ya que aun no entramos al login (security)
			investments=investorHistoryService.listPortafolioByInvestorId(1001);
			// Lista de las startups más populares (TOP 5)
			for (Startup startup : startupsMostPopular) {
				startups.remove(startup);
				int id = startup.getId();
				double amounth = startupService.getAmounthInvestedById(id);
				Integer position = startupService.getPositionStartupById(id);		
				startup.setAmounthTotalInvest(amounth);
				startup.setPosition(position);		
			}
//Lista de las startups recientes
			for (Startup startup1 : startupsRecently) {
				startups.remove(startup1);
				int id1 = startup1.getId();
				double amounth1 = startupService.getAmounthInvestedById(id1);
				Integer position1 = startupService.getPositionStartupById(id1);
				startup1.setAmounthTotalInvest(amounth1);
				startup1.setPosition(position1);
				
			
				
			}
		//	investments.get(1).getInvestmentRequest().getStartup().getImage();
		//	investment.investmentRequest.startup.image;
			
			
			//model.addAttribute("categorySelected", "");
			Collections.sort(startupsMostPopular, new CompareAmounth());
			Collections.sort(startupsRecently, new CompareAmounth());
			model.addAttribute("startupSearch",new Startup());
			model.addAttribute("intervalDate",new DateTimeUtil());
			model.addAttribute("moreStartups", startups);
			model.addAttribute("categories", categories);
			model.addAttribute("startupsRecently", startupsRecently);
			model.addAttribute("startupsMostPopular", startupsMostPopular);		
			model.addAttribute("investments", investments);
			
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
