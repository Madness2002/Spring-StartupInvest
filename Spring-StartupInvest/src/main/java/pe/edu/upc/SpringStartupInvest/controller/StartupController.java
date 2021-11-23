package pe.edu.upc.SpringStartupInvest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestmentRequest;
import pe.edu.upc.SpringStartupInvest.model.entity.Investor;
import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;
import pe.edu.upc.SpringStartupInvest.model.entity.Plan;
import pe.edu.upc.SpringStartupInvest.model.entity.PlanHistory;
import pe.edu.upc.SpringStartupInvest.model.entity.Startup;
import pe.edu.upc.SpringStartupInvest.model.entity.TypeCard;
import pe.edu.upc.SpringStartupInvest.model.entity.TypeInvestment;
import pe.edu.upc.SpringStartupInvest.model.entity.User;
import pe.edu.upc.SpringStartupInvest.security.MyUserDetails;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestmentRequestService;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorService;
import pe.edu.upc.SpringStartupInvest.service.crud.PlanHistoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.PlanService;
import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeCardService;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeInvestmentService;
import pe.edu.upc.SpringStartupInvest.service.crud.UserService;

@Controller
@RequestMapping("/startupinvest/startups") // DOMINIO BASE
public class StartupController {

	@Autowired
	private StartupService startupService;

	@Autowired
	private InvestorService investorService;

	@Autowired
	private InvestmentRequestService investmentRequestService;

	@Autowired
	private TypeCardService typeCardService;

	@Autowired
	private PlanService planService;

	@Autowired
	private PlanHistoryService planHistoryService;

	@Autowired
	private TypeInvestmentService typeInvestmentService;

	@Autowired
	private UserService userService;

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
				int idStartup = optional.get().getId();
				List<InvestmentRequest> listInvestmentRequests = new ArrayList<>();
				listInvestmentRequests = investmentRequestService.findInvestmentRequestByStartupId(idStartup);
				for (InvestmentRequest investmentRequest : listInvestmentRequests) {

					int quantityInvestors = investmentRequestService
							.getInvestorQuantityByInvestmentRequestId(investmentRequest.getId());
					double amounthColected = investmentRequestService
							.getAmountColectedByInvestmentRequestId(investmentRequest.getId());
					investmentRequest.setQuantityInvestors(quantityInvestors);
					investmentRequest.setAmountColected(amounthColected);
				}
				// USAMOS EL ID 1001 PORQUE AUN NO ENTRAMOS A SECURITY
				Optional<Investor> investor = investorService.findById(1001);
				List<TypeCard> cards = typeCardService.getAll();
				// Solicitudes de inversion
				model.addAttribute("investmentRequests", listInvestmentRequests);
				model.addAttribute("imgname", optional.get().getImage());
				model.addAttribute("description", optional.get().getDescription());
				// INVESTOR
				model.addAttribute("name", investor.get().getName());
				model.addAttribute("lastName", investor.get().getLastName());
				model.addAttribute("email", investor.get().getEmail());
				model.addAttribute("dni", investor.get().getDni());
				model.addAttribute("id", investor.get().getId());

				model.addAttribute("cards", cards);
				model.addAttribute("startup", optional.get());
				model.addAttribute("investorHistory", new InvestorHistory());
				// PUBLICACIONES
				model.addAttribute("publications", optional.get().getPublications());

				return "startup/startup-investor-view";
			}
		} catch (Exception e) {

			e.getMessage();
			return "redirect:/startupinvest/home";
		}

		return "startup/startup-investor-view";
	}

	//
	@GetMapping("{id}/view/profile")
	public String viewStartupProfile(Model model, @PathVariable("id") Integer id) {
		try {
			if (startupService.existsById(id)) {

				Optional<Startup> optional = startupService.findById(id);
				int idStartup = optional.get().getId();
				List<InvestmentRequest> listInvestmentRequests = new ArrayList<>();
				listInvestmentRequests = investmentRequestService.findInvestmentRequestByStartupId(idStartup);
				for (InvestmentRequest investmentRequest : listInvestmentRequests) {

					int quantityInvestors = investmentRequestService
							.getInvestorQuantityByInvestmentRequestId(investmentRequest.getId());
					// TE DEVUELVE EL MONTO RECOLECTADO POR LA SOLICITUD DE INVERSION
					double amounthColected = investmentRequestService
							.getAmountColectedByInvestmentRequestId(investmentRequest.getId());

					investmentRequest.setQuantityInvestors(quantityInvestors);
					investmentRequest.setAmountColected(amounthColected);
				}
				// USAMOS EL ID 1001 PORQUE AUN NO ENTRAMOS A SECURITY

				List<Plan> plans = planService.findByState(true);
				model.addAttribute("investmentRequests", listInvestmentRequests);
				model.addAttribute("imgname", optional.get().getImage());
				model.addAttribute("description", optional.get().getDescription());
				model.addAttribute("plans", plans);
				model.addAttribute("planHistory", new PlanHistory());
				model.addAttribute("name", optional.get().getName());
				model.addAttribute("idStartup", id);

				// SOLICITUD DE INVERSION
				List<TypeInvestment> typesInvestment = typeInvestmentService.getAll();
				model.addAttribute("investmentRequest", new InvestmentRequest());
				model.addAttribute("typesInvestment", typesInvestment);

				// PLANS
				List<TypeCard> typesCard = typeCardService.getAll();
				model.addAttribute("typesCard", typesCard);
				Date dateExpirationOfTheLastPlanActive = planHistoryService.findLastDateOfPlanValidByStartupId(id);
				Date dateExpirationPlanActive = planHistoryService.findActivePlanValidByStartupId(id);
				if (dateExpirationPlanActive != null)
					model.addAttribute("dateExpirationOfTheLastPlanActive", dateExpirationOfTheLastPlanActive);
				// PUBLICACIONES
				model.addAttribute("publications", optional.get().getPublications());

				return "startup/startup-startup-view";
			}
		} catch (Exception e) {

			e.getMessage();
			return "redirect:/startupinvest/home";
		}
		return "startup/startup-startup-view";
	}

	@GetMapping("view/profile/edit")
	public String viewStartupProfileEdit(Model model, Authentication authentication) {
		MyUserDetails userSession = (MyUserDetails) authentication.getPrincipal();
		Integer id = userSession.getUser().getStartup().getId();

		Optional<Startup> startup = startupService.findById(id);

		model.addAttribute("user", userSession.getUser());
		model.addAttribute("startup", startup.get());
		return "startup/editprofile";
	}

	@PostMapping("view/profile/saveEdit")
	public String viewStartupProfileSaveEdit(Model model, Authentication authentication,
			@ModelAttribute("saveEditProfile") Startup saveEditProfile) {
		MyUserDetails userSession = (MyUserDetails) authentication.getPrincipal();
		Integer id = userSession.getUser().getStartup().getId();
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		Optional<Startup> startup = startupService.findById(id);
		try {

			// STARTUP
			String name = saveEditProfile.getName();
			String email = saveEditProfile.getEmail();
			String description = saveEditProfile.getDescription();
			startup.get().setName(name);
			startup.get().setEmail(email);
			startup.get().setDescription(description);

			// USER
			User user = userSession.getUser();
			String username = saveEditProfile.getUsername();
			String password = saveEditProfile.getPassword();
			user.setUsername(username);
			user.setPassword(bcpe.encode(password));

//GUARDAR CAMBIOS
			userService.update(user);
			startupService.update(startup.get());
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "redirect:/startupinvest/startups/" + startup.get().getId() + "/view/profile";
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
