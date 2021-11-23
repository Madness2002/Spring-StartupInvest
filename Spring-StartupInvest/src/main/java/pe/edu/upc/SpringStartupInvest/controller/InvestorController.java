package pe.edu.upc.SpringStartupInvest.controller;

import java.util.ArrayList;
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

import pe.edu.upc.SpringStartupInvest.model.entity.Authority;
import pe.edu.upc.SpringStartupInvest.model.entity.Investor;
import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;
import pe.edu.upc.SpringStartupInvest.model.entity.TypeCard;
import pe.edu.upc.SpringStartupInvest.model.entity.User;
import pe.edu.upc.SpringStartupInvest.model.repository.InvestorRepository;
import pe.edu.upc.SpringStartupInvest.model.repository.UserRepository;
import pe.edu.upc.SpringStartupInvest.security.MyUserDetails;
import pe.edu.upc.SpringStartupInvest.service.crud.AuthorityService;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorHistoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorService;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeCardService;
import pe.edu.upc.SpringStartupInvest.service.crud.UserService;
import pe.edu.upc.SpringStartupInvest.model.types.UserAuthorities;

@Controller
@RequestMapping("/startupinvest/investors")
public class InvestorController {
	@Autowired
	private InvestorService investorService;
	@Autowired
	private InvestorHistoryService investorHistoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InvestorRepository investorRepository;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private TypeCardService typeCardService;
	
	@GetMapping
	public String list(Model model) {

		try {
			List<Investor> lista = new ArrayList<>();
			lista = investorService.getAll();
			model.addAttribute("investors", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@GetMapping("perfil")
	public String perfil(Model model, Authentication authentication) {
		MyUserDetails userSession = (MyUserDetails) authentication.getPrincipal();
		try {

			List<InvestorHistory> investments = new ArrayList<>();
			// LA LINEA 68 ME DA LOS DATOS DEL USUARIO AUTENTICADO
			List<TypeCard> listTypeCard = typeCardService.getAll();
			
			investments = investorHistoryService.listPortafolioByInvestor(userSession.getUser().getInvestor().getId());
			Integer totalTimesInvested = investorService
					.getTotalTimesInvestedByInvestorId(userSession.getUser().getInvestor().getId());
			double totalAmountInvested = investorService
					.getAmountTotalInvestedByInvestorId(userSession.getUser().getInvestor().getId());
			
//TOTAL DE DINERO POR RECLAMAR
			double totalAmountToReclaim=investorService.getAmountTotalToReclaimByInvestorId(userSession.getUser().getInvestor().getId());
			
			//TOTAL DE DINERO RETIRADO
			double totalAmountReclaimed =investorService.getAmountTotalToReclaimedByInvestorId(userSession.getUser().getInvestor().getId());
			
			model.addAttribute("totalAmountInvested", totalAmountInvested);
			model.addAttribute("totalTimesInvested", totalTimesInvested);
			model.addAttribute("investments", investments);
			model.addAttribute("nombres", userSession.getUser().getInvestor().getName());
			model.addAttribute("apellidos", userSession.getUser().getInvestor().getLastName());
			model.addAttribute("email", userSession.getUser().getInvestor().getEmail());

			model.addAttribute("totalAmountToReclaim", totalAmountToReclaim);
			
			model.addAttribute("totalAmountReclaimed", totalAmountReclaimed);
			
			model.addAttribute("typesCard", listTypeCard);
			
			
			return "investor/profile";

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/startupinvest/investors/perfil";

	}

	@GetMapping("perfil/reclaim")
	public String reclaim(Model model, Authentication authentication) {
		MyUserDetails userSession = (MyUserDetails) authentication.getPrincipal();
		try {

			List<InvestorHistory> investmentsToReclaim = investorHistoryService.listInvestmentsToReclaimByInvestorId(userSession.getUser().getInvestor().getId());
			// LA LINEA 69 ME DA LOS DATOS DEL USUARIO AUTENTICADO

			if (investmentsToReclaim != null) {
				

				for (InvestorHistory investorHistory : investmentsToReclaim) {
					investorHistory.setState(false);
					investorHistoryService.update(investorHistory);
				}

				return "redirect:/startupinvest/investors/perfil";
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/startupinvest/investors/perfil";

	}

	@GetMapping("perfil/edit")
	public String editPerfil(Model model, Authentication authentication) {

		MyUserDetails userSession = (MyUserDetails) authentication.getPrincipal();
		List<InvestorHistory> investments = new ArrayList<>();
		// LA LINEA 64 ME DA LOS DATOS DEL USUARIO AUTENTICADO
		
		
		
		investments = investorHistoryService.listPortafolioByInvestor(userSession.getUser().getInvestor().getId());
		Integer totalTimesInvested = investorService
				.getTotalTimesInvestedByInvestorId(userSession.getUser().getInvestor().getId());
		double totalAmountInvested = investorService
				.getAmountTotalInvestedByInvestorId(userSession.getUser().getInvestor().getId());
		//TOTAL DE DINERO POR RECLAMAR
		double totalAmountToReclaim=investorService.getAmountTotalToReclaimByInvestorId(userSession.getUser().getInvestor().getId());
		
		model.addAttribute("totalAmountInvested", totalAmountInvested);
		model.addAttribute("totalTimesInvested", totalTimesInvested);
		model.addAttribute("investments", investments);
		model.addAttribute("investor", userSession.getUser().getInvestor());
		model.addAttribute("user", userSession.getUser());
		model.addAttribute("saveEditProfile", new Investor());
		model.addAttribute("totalAmountToReclaim", totalAmountToReclaim);
		model.addAttribute("nombres", userSession.getUser().getInvestor().getName());
		model.addAttribute("apellidos", userSession.getUser().getInvestor().getLastName());
		
		return "users/edit-profile";
	}

	@GetMapping("perfil/saveEdit")
	public String saveEdit(Model model, Authentication authentication,
			@ModelAttribute("saveEditProfile") Investor saveEditProfile) {

		try {
			MyUserDetails userSession = (MyUserDetails) authentication.getPrincipal();
			BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

			// INVESTOR
			Integer id = userSession.getUser().getInvestor().getId();
			String name = saveEditProfile.getName();
			String lastName = saveEditProfile.getLastName();
			String email = saveEditProfile.getEmail();

			Optional<Investor> investor = investorService.findById(id);
			investor.get().setName(name);
			investor.get().setLastName(lastName);
			investor.get().setEmail(email);
			// USER
			User user = userSession.getUser();
			String username = saveEditProfile.getUsername();
			String password = saveEditProfile.getPassword();

			user.setUsername(username);
			user.setPassword(bcpe.encode(password));
			// GUARDAR CAMBIOS
			investorService.update(investor.get());
			userService.update(user);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "redirect:/startupinvest/investors/perfil";
	}

	@PostMapping("newInvestor")
	public String insert(Model model, @Valid @ModelAttribute("investor") Investor investor) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		try {

			// USER
			String username = investor.getUsername();
			String password = investor.getPassword();
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			userService.create(user);

			Optional<User> userCreated = userService.findByUsername(username);
			// Authority
			Authority authority = new Authority();
			authority.setAuthority("ROLE_INVESTOR");
			authority.setUser(userCreated.get());
			authorityService.create(authority);

			List<Authority> authorityCreated = authorityService.findByUser(userCreated.get());

//INVESTOR

			investor.setState(true);
			investor.setUser(userCreated.get());

			investorService.create(investor);

			Optional<Investor> investorCreated = investorService.findByName(investor.getName());

			// UPDATE
			userCreated.get().setInvestor(investorCreated.get());
			investorCreated.get().setUser(userCreated.get());
			investorService.update(investorCreated.get());
			userService.update(userCreated.get());

			System.out.println(investor.getDni());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "redirect:/startupinvest/login";
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (investorService.existsById(id)) {
				Optional<Investor> optional = investorService.findById(id);
				model.addAttribute("editinvestor", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("investor") Investor investor) {
		try {
			investorService.update(investor);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/investors";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (investorService.existsById(id)) {
				investorService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/investors";
	}

}
