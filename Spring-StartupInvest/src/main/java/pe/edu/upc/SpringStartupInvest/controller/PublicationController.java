package pe.edu.upc.SpringStartupInvest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.SpringStartupInvest.service.crud.PublicationService;

@Controller
@RequestMapping("/startupinvest/publications")
@SessionAttributes("publication")
public class PublicationController {
@Autowired
private PublicationService publicationService;

}
