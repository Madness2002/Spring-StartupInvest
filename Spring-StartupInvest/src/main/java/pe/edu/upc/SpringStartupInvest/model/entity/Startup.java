package pe.edu.upc.SpringStartupInvest.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Startups", indexes = { @Index(columnList = "startup_id", name = "startups_index_startup_id"),
		@Index(columnList = "startup_name", name = "startups_index_startup_name"),
		@Index(columnList = "startup_state", name = "startups_index_startup_state"),
        @Index(columnList = "startup_register_date", name = "startups_index_startup_register_date")}, uniqueConstraints = {
				@UniqueConstraint(columnNames = { "startup_ruc" }) })
@SequenceGenerator(name = "Startups_startup_id_seq", initialValue = 1, allocationSize = 1)
public class Startup {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Startups_startup_id_seq")
	@Column(name = "startup_id", length = 5, nullable = false)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "category_ID", nullable = false)
	private Category category;

	@NotBlank(message = "El nombre no debe estar en blanco")
	@NotNull(message="El nombre debe contener valor")
	@Size(max=50,message="El tamaño no debe ser mayor a 50")
	@Column(name = "startup_name", length = 50, nullable = false)
	private String name;

	@NotBlank(message = "El email no debe estar en blanco")
	@NotNull(message="El email debe contener valor")
	@Size(max=50,message="El tamaño no debe ser mayor a 50")
	@Column(name = "startup_email", length = 50, nullable = false)
	private String email;
	
	@Size(max=500,message="El tamaño no debe ser mayor a 500")
	@Column(name = "startup_image", length = 500, nullable = true)
	private String image;

	@Column(name = "startup_state", nullable = false)
	private Boolean state;

	@NotBlank(message = "El ruc no debe estar en blanco")
	@NotNull(message="El ruc debe contener valor")
	@Size(max=11,message="El tamaño no debe ser mayor a 11")
	@Column(name = "startup_ruc", length = 11, nullable = false)
	private String ruc;

	@NotNull(message = "La fecha de registro debe contener valor")
	@NotBlank(message = "La fecha de registro no debe estar en blanco")
	@Column(name = "startup_register_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date registerDate;

	@NotBlank(message = "El password no debe estar en blanco")
	@NotNull(message="El password debe contener valor")
	@Size(max=500,message="El tamaño no debe ser mayor a 500")
	@Column(name = "startup_password", length = 500, nullable = false)
	private String password;
	
	@NotBlank(message = "La descripción no debe estar en blanco")
	@NotNull(message="La descripción debe contener valor")
	@Size(max=400,message="El tamaño no debe ser mayor a 400")
	@Column(name = "startup_description", length = 400, nullable = false)
	private String description;
	
	@OneToMany(mappedBy = "startup", fetch = FetchType.LAZY)
	private List<Publication> publications;

	@OneToMany(mappedBy = "startup", fetch = FetchType.LAZY)
	private List<PlanHistory> plansHistory;

	@OneToMany(mappedBy = "startup", fetch = FetchType.LAZY)
	private List<InvestmentRequest> investmentRequests;

	public Startup() {
		publications = new ArrayList<Publication>();
		plansHistory = new ArrayList<PlanHistory>();
		investmentRequests = new ArrayList<InvestmentRequest>();
	}

	public Startup(Integer id, Category category, String name, String email, String image, Boolean state, String ruc,
			Date registerDate, String password, String description, List<Publication> publications,
			List<PlanHistory> plansHistory, List<InvestmentRequest> investmentRequests) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.email = email;
		this.image = image;
		this.state = state;
		this.ruc = ruc;
		this.registerDate = registerDate;
		this.password = password;
		this.description = description;
		this.publications = publications;
		this.plansHistory = plansHistory;
		this.investmentRequests = investmentRequests;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public List<PlanHistory> getPlansHistory() {
		return plansHistory;
	}

	public void setPlansHistory(List<PlanHistory> plansHistory) {
		this.plansHistory = plansHistory;
	}


	public List<InvestmentRequest> getInvestmentRequests() {
		return investmentRequests;
	}

	public void setInvestmentRequests(List<InvestmentRequest> investmentRequests) {
		this.investmentRequests = investmentRequests;
	}
	
}