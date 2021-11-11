package pe.edu.upc.SpringStartupInvest.controller;

import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.SpringStartupInvest.model.entity.Publication;
import pe.edu.upc.SpringStartupInvest.model.entity.Startup;
import pe.edu.upc.SpringStartupInvest.service.crud.PublicationService;
import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;

@Controller
@RequestMapping("/startupinvest/publications")
public class PublicationController {
	@Autowired
	private PublicationService publicationService;

	@Autowired
	private StartupService startupService;
	
	
	@GetMapping("{id}/view")
	public String list(Model model,@ModelAttribute("id")Integer id) {

		try {
			Optional<Startup> startup = startupService.findById(id);
			Date todayDate= new Date();
		
			model.addAttribute("publications", startup.get().getPublications());
			model.addAttribute("imgname", startup.get().getImage());
			model.addAttribute("idStartup", startup.get().getId());
			model.addAttribute("name", startup.get().getName());
			model.addAttribute("newPublication",new Publication());
			model.addAttribute("editPublication",new Publication());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "publication/publication";
	}

	@PostMapping("newPublication")
	public String insertar(Model model, @ModelAttribute("publication") Publication publication) {
		try {
			publication.setDate(new Date());
		publicationService.create(publication);		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "redirect:/startupinvest/publications/"+publication.getStartup().getId()+"/view";
	}

	@PostMapping("edit")
	public String actualizar(Model model, @ModelAttribute("editPublication") Publication publication) {
		try {
			if (publicationService.existsById(publication.getId())) {
				Optional<Publication> optional = publicationService.findById(publication.getId());
				String title=publication.getName();
				String description=publication.getDescription();
				String url=publication.getUrl();
			optional.get().setName(title);
			optional.get().setDescription(description);
			optional.get().setUrl(url);
publicationService.update(optional.get());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
return "redirect:/startupinvest/publications/"+publication.getStartup().getId()+"/view";
	}

	@PostMapping("saveEdit")
	public String saveEdit(Model model, @ModelAttribute("publication") Publication publication) {
		try {
			publicationService.update(publication);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "redirect:/startupinvest/publications";
	}

	@GetMapping("{idStartup}/{idPublication}/del")
	public String delete(@PathVariable("idStartup") Integer idStartup,@PathVariable("idPublication") Integer idPublication) {

		try {
			if (publicationService.existsById(idPublication)) {
				publicationService.deleteById(idPublication);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/startupinvest/publications/"+idStartup+"/view";
	}

}
