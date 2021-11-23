package pe.edu.upc.SpringStartupInvest.model.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Startups", indexes = { @Index(columnList = "startup_id", name = "startups_index_startup_id"),
		@Index(columnList = "startup_name", name = "startups_index_startup_name"),
		@Index(columnList = "startup_state", name = "startups_index_startup_state"),
		@Index(columnList = "startup_register_date", name = "startups_index_startup_register_date") }, uniqueConstraints = {
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
	@NotNull(message = "El nombre debe contener valor")
	@Size(max = 50, message = "El tamaño no debe ser mayor a 50")
	@Column(name = "startup_name", length = 50, nullable = false)
	private String name;

	@NotBlank(message = "El email no debe estar en blanco")
	@NotNull(message = "El email debe contener valor")
	@Size(max = 50, message = "El tamaño no debe ser mayor a 50")
	@Column(name = "startup_email", length = 50, nullable = false)
	private String email;

	@Size(max = 500, message = "El tamaño no debe ser mayor a 500")
	@Column(name = "startup_image", length = 500, nullable = true)
	private String image;

	@Column(name = "startup_state", nullable = false)
	private Boolean state;

	@NotBlank(message = "El ruc no debe estar en blanco")
	@NotNull(message = "El ruc debe contener valor")
	@Size(max = 11, message = "El tamaño no debe ser mayor a 11")
	@Column(name = "startup_ruc", length = 11, nullable = false)
	private String ruc;

	
	@Column(name = "startup_register_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date registerDate;

	@NotBlank(message = "La descripción no debe estar en blanco")
	@NotNull(message = "La descripción debe contener valor")
	@Size(max = 2000, message = "El tamaño no debe ser mayor a 2000")
	@Column(name = "startup_description", length = 2000, nullable = false)
	private String description;

	@Transient
	private Integer position;

	@Transient
	private Double amounthTotalInvest;

	@OneToMany(mappedBy = "startup", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Publication> publications;

	@OneToMany(mappedBy = "startup", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<PlanHistory> plansHistory;

	@OneToMany(mappedBy = "startup", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<InvestmentRequest> investmentRequests;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId("id")
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	@Transient
	private String username;
	
	@Transient
	private String password;
	
	public Startup() {
		publications = new ArrayList<Publication>();
		plansHistory = new ArrayList<PlanHistory>();
		investmentRequests = new ArrayList<InvestmentRequest>();
	}

	

	public Startup(Integer id, Category category,
			@NotBlank(message = "El nombre no debe estar en blanco") @NotNull(message = "El nombre debe contener valor") @Size(max = 50, message = "El tamaño no debe ser mayor a 50") String name,
			@NotBlank(message = "El email no debe estar en blanco") @NotNull(message = "El email debe contener valor") @Size(max = 50, message = "El tamaño no debe ser mayor a 50") String email,
			@Size(max = 500, message = "El tamaño no debe ser mayor a 500") String image, Boolean state,
			@NotBlank(message = "El ruc no debe estar en blanco") @NotNull(message = "El ruc debe contener valor") @Size(max = 11, message = "El tamaño no debe ser mayor a 11") String ruc,
			@NotNull(message = "La fecha de registro debe contener valor") @NotBlank(message = "La fecha de registro no debe estar en blanco") Date registerDate,
			@NotBlank(message = "La descripción no debe estar en blanco") @NotNull(message = "La descripción debe contener valor") @Size(max = 2000, message = "El tamaño no debe ser mayor a 2000") String description,
			Integer position, Double amounthTotalInvest, List<Publication> publications, List<PlanHistory> plansHistory,
			List<InvestmentRequest> investmentRequests, User user, String username, String password) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.email = email;
		this.image = image;
		this.state = state;
		this.ruc = ruc;
		this.registerDate = registerDate;
		this.description = description;
		this.position = position;
		this.amounthTotalInvest = amounthTotalInvest;
		this.publications = publications;
		this.plansHistory = plansHistory;
		this.investmentRequests = investmentRequests;
		this.user = user;
		this.username = username;
		this.password = password;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Double getAmounthTotalInvest() {
		return amounthTotalInvest;
	}

	public void setAmounthTotalInvest(Double amounthTotalInvest) {
		this.amounthTotalInvest = amounthTotalInvest;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
