package pe.edu.upc.SpringStartupInvest.init;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.edu.upc.SpringStartupInvest.model.entity.Investor;
import pe.edu.upc.SpringStartupInvest.model.entity.Startup;
import pe.edu.upc.SpringStartupInvest.model.entity.User;
import pe.edu.upc.SpringStartupInvest.model.repository.InvestorRepository;
import pe.edu.upc.SpringStartupInvest.model.repository.StartupRepository;
import pe.edu.upc.SpringStartupInvest.model.repository.UserRepository;
import pe.edu.upc.SpringStartupInvest.model.types.UserAuthorities;

@Service
public class InitUser implements CommandLineRunner{

	@Autowired
	private StartupRepository startupRepository;
	
	@Autowired
	private InvestorRepository investorRepository;
	
	@Autowired	
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
	
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		// DESBLOQUEAR PARA CREAR USUARIOS
		/*
		Optional<Startup> optional= startupRepository.findById(2001);
		if (optional.isPresent()) {
			Startup startup = optional.get();
			User user =new User("startup", bcpe.encode("contra"), startup);
		 user.addAuthority(UserAuthorities.ROLE_STARTUP);
			userRepository.save(user);
		 }
		
		
			Optional<Investor> optional= investorRepository.findById(1001);
			if (optional.isPresent()) {
				Investor investor = optional.get();
				User user =new User("investor", bcpe.encode("contra"), investor);
			 user.addAuthority(UserAuthorities.ROLE_INVESTOR);
				userRepository.save(user);
			
		}
		
		
	
		User user =new User("admin", bcpe.encode("contra"));
	 user.addAuthority(UserAuthorities.ROLE_ADMIN);
		userRepository.save(user);
	*/
}
}