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

import pe.edu.upc.SpringStartupInvest.model.entity.TypeCard;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeCardService;

@Controller
@RequestMapping("/TypeCardinvest/typeCards")
@SessionAttributes("typeCard")
public class TypeCardController {

	@Autowired
	private TypeCardService typeCardService;

	@GetMapping
	public String list(Model model) {

		try {
			List<TypeCard> lista = new ArrayList<>();
			lista = typeCardService.getAll();
			model.addAttribute("typeCard", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@PostMapping("newTypeCard")
	public String insertar(Model model, @Valid @ModelAttribute("typeCard") TypeCard typeCard) {
		try {

			TypeCard typeCardSaved = typeCardService.create(typeCard);
			model.addAttribute("typeCard", typeCardSaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (typeCardService.existsById(id)) {
				Optional<TypeCard> optional = typeCardService.findById(id);
				model.addAttribute("editTypeCard", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("typeCard") TypeCard typeCard) {
		try {
			typeCardService.update(typeCard);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/TypeCardinvest/typeCards";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (typeCardService.existsById(id)) {
				typeCardService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/TypeCardinvest/typeCards";
	}
}
