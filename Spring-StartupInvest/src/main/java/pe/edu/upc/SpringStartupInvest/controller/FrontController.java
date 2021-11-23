package pe.edu.upc.SpringStartupInvest.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.SpringStartupInvest.model.entity.Category;
import pe.edu.upc.SpringStartupInvest.model.entity.Investor;
import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;
import pe.edu.upc.SpringStartupInvest.model.entity.Startup;
import pe.edu.upc.SpringStartupInvest.security.MyUserDetails;
import pe.edu.upc.SpringStartupInvest.service.crud.CategoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorHistoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorService;
import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;
import pe.edu.upc.SpringStartupInvest.util.startup.CompareAmounth;
import pe.edu.upc.SpringStartupInvest.util.startup.DateTimeUtil;

@Controller
@RequestMapping("/") // dominio base
public class FrontController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StartupService startupService;

	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private InvestorHistoryService investorHistoryService;

	@GetMapping("startupinvest") //
	public String index() {
		return "index/index";
	}

	@GetMapping("startupinvest/login") //
	public String login() {
		return "login";
	}
	
	@GetMapping("startupinvest/choose/user") //
	public String chooseUser() {
		return "choose-user";
	}
	
	@GetMapping("startupinvest/register/startup") //
	public String registerStartup() {
		return "register-startup";
	}
	
	@GetMapping("startupinvest/register/investor") //
	public String registerInvestor(Model model) {
		Investor investor=new Investor();
		investor.setState(true);
		model.addAttribute("investor", investor);
		
		return "register-investor";
	}
	
	@GetMapping("startupinvest/home")
	public String home(Model model, Authentication authentication) {
		MyUserDetails userSession = (MyUserDetails) authentication.getPrincipal();
if(userSession.getUser().getAuthorityRoleInvestor().equals("ROLE_INVESTOR")) {		
		try {
			
			List<Category> categories = new ArrayList<>();
			List<Startup> startupsMostPopular, startupsRecently, startups, startupWithActivePlans = new ArrayList<>();
			List<InvestorHistory> investments = new ArrayList<>();

			categories = categoryService.findByState(true);
			startups = startupService.getAll();
			startupsMostPopular = startupService.listStartupsMostPopular();
			startupsRecently = startupService.findByDateRecently();
			startupWithActivePlans = startupService.findStartupsByActivePlan();

			//LA LINEA 69 ME DA LOS DATOS DEL USUARIO AUTENTICADO 
		
			investments=investorHistoryService.listPortafolioByInvestor(userSession.getUser().getInvestor().getId());
			
			
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

			// Aqui se ordena las startups por posición
			Collections.sort(startupsMostPopular, new CompareAmounth());
			Collections.sort(startupsRecently, new CompareAmounth());

			model.addAttribute("startupSearch", new Startup());
			model.addAttribute("intervalDate", new DateTimeUtil());
			model.addAttribute("moreStartups", startups);
			model.addAttribute("categories", categories);
			model.addAttribute("startupsRecently", startupsRecently);
			model.addAttribute("startupsMostPopular", startupsMostPopular);
			// Aqui se cargan las inversiones que ha hecho el inversionista
			model.addAttribute("investments", investments);

			model.addAttribute("startupWithActivePlans", startupWithActivePlans);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "dashboard/dashboard-investor";
		
		
}//FIN DEL LA PRIMERA CONDICION

else if(userSession.getUser().getAuthorityRoleAdmin().equals("ROLE_ADMIN")) {return "redirect:/startupinvest/administrator";}

else return "redirect:/startupinvest/startups/"+userSession.getUser().getStartup().getId()+"/view/profile";


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
