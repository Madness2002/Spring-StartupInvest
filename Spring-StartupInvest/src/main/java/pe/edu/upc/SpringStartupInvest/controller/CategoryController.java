package pe.edu.upc.SpringStartupInvest.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import pe.edu.upc.SpringStartupInvest.model.entity.Category;
import pe.edu.upc.SpringStartupInvest.service.crud.CategoryService;

@Controller
@RequestMapping("/startupinvest/categories")
@SessionAttributes("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ModelAttribute("category")
	public Category populateForm() {
		return new Category();
	}

	@GetMapping
	public String list(Model model) {

		try {
			List<Category> categories = new ArrayList<>();
			categories = categoryService.findByState(true);
			model.addAttribute("categories", categories);
			model.addAttribute("category", new Category());
		} catch (Exception e) {
			e.getMessage();
		}

		return "dashboard/dashboard-investor";
	}

	@PostMapping("newCategory")
	public String insertar(Model model, @Valid @ModelAttribute("category") Category category,
			@RequestParam("file") MultipartFile imagen) throws Exception {
		try {
			//Path directorioImagenes = Paths.get("src//main//resources//static/images/categories");
			String rutaAbsoluta ="C://Users//Usuario//Desktop//Programacion_en_web//Sstartup-invest_test//images//categories";
			byte[] bytesImg = imagen.getBytes();
			Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
			Files.write(rutaCompleta, bytesImg);

			category.setImage(imagen.getOriginalFilename());

			category.setState(true);
			category.setImage(imagen.getOriginalFilename());
			Category categorySaved = categoryService.create(category);
			model.addAttribute("category", categorySaved);
		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/ga";
	}

	@PostMapping("{id}/edit")
	public void actualizar(Model model, @PathVariable("id") Integer id) {
		try {
			if (categoryService.existsById(id)) {
				Optional<Category> optional = categoryService.findById(id);
				model.addAttribute("editCountry", optional);
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("category") Category category) {
		try {
			categoryService.update(category);

		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/categories";
	}

	@GetMapping("{id}/del")
	public String delete(@PathVariable("id") Integer id) {

		try {
			if (categoryService.existsById(id)) {
				categoryService.deleteById(id);

			}
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/ga";
	}

}
