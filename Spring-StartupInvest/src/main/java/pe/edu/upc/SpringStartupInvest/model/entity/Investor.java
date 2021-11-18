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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Investors", indexes = { @Index(columnList = "investor_id", name = "investors_index_investor_id") })
@SequenceGenerator(name = "Investors_investor_id_seq", initialValue = 1, allocationSize = 1)
public class Investor {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Investors_investor_id_seq")
	@Column(name = "investor_id", length = 5, nullable = false)
	private Integer id;

	@NotBlank(message = "La descripcion no debe estar en blanco")
	@NotNull(message="La descripcion debe contener valor")
	@Size(max=50,message="El tamaño no debe ser mayor a 50")
	@Column(name = "investor_name", length = 50, nullable = false)
	private String name;

	@NotBlank(message = "El DNI no debe estar en blanco")
	@NotNull(message="El DNI debe contener valor")
	@Size(max=8,message="El tamaño no debe ser mayor a 8")
	@Column(name = "investor_dni", length = 8, nullable = false)
	private String dni;

	@NotBlank(message = "El lastname no debe estar en blanco")
	@NotNull(message="El lastname debe contener valor")
	@Size(max=50,message="El tamaño no debe ser mayor a 50")
	@Column(name = "investor_lastname", length = 50, nullable = false)
	private String lastname;

	@NotBlank(message = "El email no debe estar en blanco")
	@NotNull(message="El email debe contener valor")
	@Size(max=50,message="El tamaño no debe ser mayor a 50")
	@Column(name = "investor_email", length = 50, nullable = false)
	private String email;


	@Size(max=500,message="El tamaño no debe ser mayor a 500")
	@Column(name = "investor_image", length = 500, nullable = true)
	private String image;

	@Column(name = "investor_state", nullable = false)
	private Boolean state;

	@OneToMany(mappedBy = "investor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<InvestorHistory> investorHistories;

	@OneToMany(mappedBy = "investor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TypeCardHistory> typeCardHistories;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId("id")
	@JoinColumn(name="user_id",nullable = true)
	private User user;
	
	
	public Investor() {
		investorHistories = new ArrayList<InvestorHistory>();
		typeCardHistories = new ArrayList<TypeCardHistory>();
		
	}

	

	public Investor(Integer id,
			@NotBlank(message = "La descripcion no debe estar en blanco") @NotNull(message = "La descripcion debe contener valor") @Size(max = 50, message = "El tamaño no debe ser mayor a 50") String name,
			@NotBlank(message = "El DNI no debe estar en blanco") @NotNull(message = "El DNI debe contener valor") @Size(max = 8, message = "El tamaño no debe ser mayor a 8") String dni,
			@NotBlank(message = "El lastname no debe estar en blanco") @NotNull(message = "El lastname debe contener valor") @Size(max = 50, message = "El tamaño no debe ser mayor a 50") String lastname,
			@NotBlank(message = "El email no debe estar en blanco") @NotNull(message = "El email debe contener valor") @Size(max = 50, message = "El tamaño no debe ser mayor a 50") String email,
			@Size(max = 500, message = "El tamaño no debe ser mayor a 500") String image, Boolean state,
			List<InvestorHistory> investorHistories, List<TypeCardHistory> typeCardHistories, User user) {
		super();
		this.id = id;
		this.name = name;
		this.dni = dni;
		this.lastname = lastname;
		this.email = email;
		this.image = image;
		this.state = state;
		this.investorHistories = investorHistories;
		this.typeCardHistories = typeCardHistories;
		this.user = user;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	
	public List<InvestorHistory> getInvestorHistories() {
		return investorHistories;
	}

	public void setInvestorHistories(List<InvestorHistory> investorHistories) {
		this.investorHistories = investorHistories;
	}

	public List<TypeCardHistory> getTypeCardHistories() {
		return typeCardHistories;
	}

	public void setTypeCardHistories(List<TypeCardHistory> typeCardHistories) {
		this.typeCardHistories = typeCardHistories;
	}

}
