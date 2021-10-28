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

import pe.edu.upc.SpringStartupInvest.model.entity.TypeCardHistory;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeCardHistoryService;

@Controller
@RequestMapping("/startupinvest/TypeCardHistorysHistory")
@SessionAttributes("TypeCardHistoryHistory")
public class TypeCardHistoryController {
	@Autowired
	private TypeCardHistoryService typeCardHistoryService;

	@GetMapping
	public String list(Model model) {

		try {
			List<TypeCardHistory> lista = new ArrayList<>();
			lista = typeCardHistoryService.getAll();
			model.addAttribute("typeCardHistory", lista);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@PostMapping("newTypeCardHistory")
	public String insertar(Model model, @Valid @ModelAttribute("typeCardHistory") TypeCardHistory typeCardHistory) {
		try {

			TypeCardHistory typeCardHistorySaved = typeCardHistoryService.create(typeCardHistory);
			model.addAttribute("typeCardHistory", typeCardHistorySaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (typeCardHistoryService.existsById(id)) {
				Optional<TypeCardHistory> optional = typeCardHistoryService.findById(id);
				model.addAttribute("editTypeCardHistory", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("typeCardHistory") TypeCardHistory typeCardHistory) {
		try {
			typeCardHistoryService.update(typeCardHistory);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/TypeCardHistorysHistory";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (typeCardHistoryService.existsById(id)) {
				typeCardHistoryService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/TypeCardHistorysHistory";
	}

}
