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

import pe.edu.upc.SpringStartupInvest.model.entity.Publication;
import pe.edu.upc.SpringStartupInvest.service.crud.PublicationService;

@Controller
@RequestMapping("/startupinvest/publications")
@SessionAttributes("publication")
public class PublicationController {
	@Autowired
	private PublicationService publicationService;

	@GetMapping
	public String list(Model model) {

		try {
			List<Publication> lista = new ArrayList<>();
			lista = publicationService.getAll();
			model.addAttribute("publication", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@PostMapping("newPublication")
	public String insertar(Model model, @Valid @ModelAttribute("publication") Publication publication) {
		try {

			Publication publicationSaved = publicationService.create(publication);
			model.addAttribute("publication", publicationSaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (publicationService.existsById(id)) {
				Optional<Publication> optional = publicationService.findById(id);
				model.addAttribute("editPublication", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("publication") Publication publication) {
		try {
			publicationService.update(publication);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/publications";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (publicationService.existsById(id)) {
				publicationService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/publications";
	}

}
