package pe.edu.upc.SpringStartupInvest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.edu.upc.SpringStartupInvest.service.crud.StartupService;

@Controller
@RequestMapping("/startupinvest/startups")
@SessionAttributes("startup")
public class StartupController {

	@Autowired
	private StartupService startupService;
}
