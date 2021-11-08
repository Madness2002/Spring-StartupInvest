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

import pe.edu.upc.SpringStartupInvest.model.entity.TypeInvestment;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeInvestmentService;

@Controller
@RequestMapping("/startupinvest/typesInvestment")
@SessionAttributes("typeInvestment")
public class TypeInvestmentController {
	@Autowired
	private TypeInvestmentService typeInvestmentService;

	@GetMapping
	public String list(Model model) {

		try {
			List<TypeInvestment> lista = new ArrayList<>();
			lista = typeInvestmentService.getAll();
			model.addAttribute("typeInvestments", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@PostMapping("newTypeInvestment")
	public String insertar(Model model, @Valid @ModelAttribute("typeInvestment") TypeInvestment typeInvestment) {
		try {

			TypeInvestment typeInvestmentSaved = typeInvestmentService.create(typeInvestment);
			model.addAttribute("typeInvestment", typeInvestmentSaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (typeInvestmentService.existsById(id)) {
				Optional<TypeInvestment> optional = typeInvestmentService.findById(id);
				model.addAttribute("editTypeInvestment", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("typeInvestment") TypeInvestment typeInvestment) {
		try {
			typeInvestmentService.update(typeInvestment);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/typesInvestment";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (typeInvestmentService.existsById(id)) {
				typeInvestmentService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/typesInvestment";
	}
}
