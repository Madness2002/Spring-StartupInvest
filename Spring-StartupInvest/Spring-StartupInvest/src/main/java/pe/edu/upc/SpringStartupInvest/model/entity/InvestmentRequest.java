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
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "InvestmentRequests", indexes = {
		@Index(columnList = "investment_request_id", name = "investmentRequests_index_investment_request_id") })
@SequenceGenerator(name = "InvestmentRequest_investment_request_id_seq", initialValue = 1, allocationSize = 1)
public class InvestmentRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "InvestmentRequest_investment_request_id_seq")
	@Column(name = "investment_request_id", length = 5, nullable = false)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "startup_id")
	private Startup startup;

	@ManyToOne()
	@JoinColumn(name = "type_investment_id")
	private TypeInvestment typeInvestment;

	@DecimalMax(value = "9999999999.99")
	@DecimalMin(value = "0.99")
	@Column(name = "investment_request_amount", columnDefinition = "DECIMAL(12,2)", nullable = false)
	private Double amount;

	@Column(name = "investment_request_state")
	private Boolean state;

	@NotBlank()
	@NotNull(message = "La descripcion debe contener valor")
	@Size(max = 200, message = "El tamaño máximo del nombre de la descripcion es 200")
	@Column(name = "investment_request_description", length = 200, nullable = false)
	private String description;

	@NotNull(message = "La fecha de creación debe contener valor")
	@NotBlank(message = "La fecha de creación no debe estar en blanco")
	@Column(name = "investment_request_creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@NotNull(message = "La fecha de expiracion debe contener valor")
	@NotBlank(message = "La fecha de expiracion no debe estar en blanco")
	@Column(name = "investment_request_expiration_date")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	@NotNull(message = "La fecha de retorno debe contener valor")
	@NotBlank(message = "La fecha de retorno no debe estar en blanco")
	@Column(name = "investment_request_return_date")
	@Temporal(TemporalType.DATE)
	private Date returnDate;

	@OneToMany(mappedBy = "investmentRequest", fetch = FetchType.LAZY)
	private List<InvestorHistory> investorHistories;

	@Transient
	private double amountColected;
	
	@Transient
	private int quantityInvestors;

	public InvestmentRequest() {
		investorHistories = new ArrayList<InvestorHistory>();
	}

	

	public InvestmentRequest(Integer id, Startup startup, TypeInvestment typeInvestment,
			@DecimalMax("9999999999.99") @DecimalMin("0.99") Double amount, Boolean state,
			@NotBlank @NotNull(message = "La descripcion debe contener valor") @Size(max = 200, message = "El tamaño máximo del nombre de la descripcion es 200") String description,
			@NotNull(message = "La fecha de creación debe contener valor") @NotBlank(message = "La fecha de creación no debe estar en blanco") Date creationDate,
			@NotNull(message = "La fecha de expiracion debe contener valor") @NotBlank(message = "La fecha de expiracion no debe estar en blanco") Date expirationDate,
			@NotNull(message = "La fecha de retorno debe contener valor") @NotBlank(message = "La fecha de retorno no debe estar en blanco") Date returnDate,
			List<InvestorHistory> investorHistories, double amountColected, int quantityInvestors) {
		super();
		this.id = id;
		this.startup = startup;
		this.typeInvestment = typeInvestment;
		this.amount = amount;
		this.state = state;
		this.description = description;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
		this.returnDate = returnDate;
		this.investorHistories = investorHistories;
		this.amountColected = amountColected;
		this.quantityInvestors = quantityInvestors;
	}



	public int getQuantityInvestors() {
		return quantityInvestors;
	}



	public void setQuantityInvestors(int quantityInvestors) {
		this.quantityInvestors = quantityInvestors;
	}



	public List<InvestorHistory> getInvestorHistories() {
		return investorHistories;
	}

	public void setInvestorHistories(List<InvestorHistory> investorHistories) {
		this.investorHistories = investorHistories;
	}

	public double getAmountColected() {
		return amountColected;
	}

	public void setAmountColected(double amounthColected) {
		this.amountColected = amounthColected;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Startup getStartup() {
		return startup;
	}

	public void setStartup(Startup startup) {
		this.startup = startup;
	}

	public TypeInvestment getTypeInvestment() {
		return typeInvestment;
	}

	public void setTypeInvestment(TypeInvestment typeInvestment) {
		this.typeInvestment = typeInvestment;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
