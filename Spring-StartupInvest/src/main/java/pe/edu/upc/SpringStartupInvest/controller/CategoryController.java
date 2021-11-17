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
@RequestMapping("/startupinvest/category")
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

	@GetMapping("administrator/categories")
	public String listAdministrator(Model model) {

		try {
			List<Category> categories = new ArrayList<>();
			categories = categoryService.getAll();
			model.addAttribute("categories", categories);
			model.addAttribute("category", new Category());
			model.addAttribute("editCategory", new Category());
			model.addAttribute("newCategory", new Category());
		} catch (Exception e) {
			e.getMessage();
		}

		return "dashboard-administrator/administrator-category";
	}

	@GetMapping("administrator/categories/{id}/editState")
	public String editAdministratorCategory(Model model, @PathVariable("id") Integer id) {

		try {
			Optional<Category> category = categoryService.findById(id);
			if (category.isPresent()) {
				Boolean state = category.get().getState();

				category.get().setState(!state);
				categoryService.update(category.get());
			} else
				return "redirect:/startupinvest/category/administrator/categories";
		} catch (Exception e) {
			e.getMessage();
		}

		return "redirect:/startupinvest/category/administrator/categories";
	}

	@GetMapping("administrator/categories/edit")
	public String actualizar(Model model, @ModelAttribute("editCategory") Category editCategory) {
		try {
			Optional<Category> category = categoryService.findById(editCategory.getId());
			category.get().setName(editCategory.getName());
			categoryService.update(category.get());
			
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/category/administrator/categories";
	}
	
	
	@GetMapping("administrator/categories/newCategory")
	public String insert(Model model, @ModelAttribute("newCategory") Category editCategory) {
		try {
			editCategory.setState(false);
			
			
			categoryService.create(editCategory);
			
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/startupinvest/category/administrator/categories";
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

	
	
	
	
	
	
	
	
	
	
	// PRUEBAS
	@PostMapping("newCategory")
	public String insertar(Model model, @Valid @ModelAttribute("category") Category category,
			@RequestParam("file") MultipartFile imagen) throws Exception {
		try {
			// Path directorioImagenes =
			// Paths.get("src//main//resources//static/images/categories");
			String rutaAbsoluta = "C:\\Users\\Usuario\\eclipse-workspace\\Spring-StartupInvest\\Spring-StartupInvest\\src\\main\\resources\\static\\images\\categories";
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

}
