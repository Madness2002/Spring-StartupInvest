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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TypesInvestment", indexes = {
		@Index(columnList = "type_investment_id", name = "typesInvestment_index_type_investment_id") })
@SequenceGenerator(name = "TypesInvestment_type_investment_id_seq", initialValue = 1, allocationSize = 1)
public class TypeInvestment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TypesInvestment_type_investment_id_seq")
	@Column(name = "type_investment_id", length = 5, nullable = false)
	private Integer id;

	@NotBlank(message = "El nombre no debe estar en blanco")
	@NotNull(message="El nombre debe contener valor")
	@Size(max=50,message="El tamaño no debe ser mayor a 50")
	@Column(name = "type_investment_name", length = 50, nullable = false)
	private String name;

	@NotBlank(message = "El descripción no debe estar en blanco")
	@NotNull(message="La descripción debe contener valor")
	@Size(max=50,message="El tamaño no debe ser mayor a 50")
	@Column(name = "type_investment_description", length = 50, nullable = false)
	private String description;

	@OneToMany(mappedBy = "typeInvestment", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<InvestmentRequest> investmentRequests;

	public TypeInvestment() {

		investmentRequests = new ArrayList<InvestmentRequest>();
	}

	public TypeInvestment(Integer id, String name, String description, List<InvestmentRequest> investmentRequests) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.investmentRequests = investmentRequests;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<InvestmentRequest> getInvestmentRequests() {
		return investmentRequests;
	}

	public void setInvestmentRequests(List<InvestmentRequest> investmentRequests) {
		this.investmentRequests = investmentRequests;
	}

}
