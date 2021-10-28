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
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.SpringStartupInvest.model.entity.Investor;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorService;

@Controller
@RequestMapping("/startupinvest/investors")
@SessionAttributes("investor")
public class InvestorController {
	@Autowired
	private InvestorService investorService;

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

	@PostMapping("newInvestor")
	public String insertar(Model model, @Valid @ModelAttribute("investor") Investor investor) {
		try {
			investor.setState(true);
			Investor investorSaved = investorService.create(investor);
			model.addAttribute("investor", investorSaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
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
