package pe.edu.upc.SpringStartupInvest.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pe.edu.upc.SpringStartupInvest.model.types.UserAuthorities;

@Entity
@Table(name = "users", indexes = { @Index(columnList = "username", name = "user_index_username") })
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer id;

	@NotNull
	@NotBlank
	@Size(max = 40)
	@Column(name = "username", length = 40, nullable = false)
	private String username;
	@NotNull
	@NotBlank
	@Size(max = 60)
	@Column(name = "password", length = 60, nullable = false)
	private String password;

	@NotNull
	@Column(name = "enable")
	private boolean enable;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Authority> authorities;

	@OneToOne(mappedBy="user",orphanRemoval = true)
	private Startup startup;
	
	@OneToOne(mappedBy="user")
	private Investor investor;

	

	public User() {
		this.enable=true;
		this.authorities=new ArrayList<Authority>();
	}
	
	
	public User(String username, String password) {
		this.username=username;
		this.password=password;
		this.enable=true;
		this.authorities=new ArrayList<Authority>();
	}
	
	public User(String username, String password, Startup startup) {
		this.id=startup.getId();
		this.username=username;
		this.password=password;
		this.enable=true;
		this.startup=startup;
		this.authorities=new ArrayList<Authority>();
		startup.setUser(this);
	}
	
	public User(String username, String password, Investor investor) {
		this.id=investor.getId();
		this.username=username;
		this.password=password;
		this.enable=true;
		this.investor=investor;
		this.authorities=new ArrayList<Authority>();
		investor.setUser(this);
	}
	
	
	//PARA AGREGAR ROLES 
	public void addAuthority(UserAuthorities auth) {
		Authority authority = new Authority();
		authority.setAuthority(auth.name());
		authority.setUser(this);
		this.authorities.add(authority);
		
	}
	
	public User(Integer id, @NotNull @NotBlank @Size(max = 40) String username,
			@NotNull @NotBlank @Size(max = 50) String password, @NotNull boolean enable, List<Authority> authorities,
			Investor investor, Startup startup) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.authorities = authorities;
		this.investor = investor;
		this.startup = startup;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public String getAuthorityRoleInvestor() {
		
		String roleInvestor = new String();
		for (Authority authority : this.authorities) {
			if(authority.getAuthority().equals("ROLE_INVESTOR"))
				roleInvestor=authority.getAuthority();	
		}
		
		return roleInvestor;
	}
	
public String getAuthorityRoleAdmin() {
		
		String roleAdmin= new String();
		for (Authority authority : this.authorities) {
			if(authority.getAuthority().equals("ROLE_ADMIN"))
				roleAdmin=authority.getAuthority();	
		}
		
		return roleAdmin;
	}
	


public String getAuthorityRoleStartup() {
	
	String roleStartup= new String();
	for (Authority authority : this.authorities) {
		if(authority.getAuthority().equals("ROLE_STARTUP"))
			roleStartup=authority.getAuthority();	
	}
	
	return roleStartup;
}
	
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public Startup getStartup() {
		return startup;
	}

	public void setStartup(Startup startup) {
		this.startup = startup;
	}

}
