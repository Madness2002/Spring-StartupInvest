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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Plans", indexes = { @Index(columnList = "plan_id", name = "plans_index_plan_id"),
		@Index(columnList = "plan_name", name = "plans_index_plan_name") }, uniqueConstraints = {
				@UniqueConstraint(columnNames = { "plan_name" }) })
@SequenceGenerator(name = "Plans_monetary_plan_id_seq", initialValue = 100, allocationSize = 1)
public class Plan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Plans_monetary_plan_id_seq")
	@Column(name = "plan_id", length = 5, nullable = false)
	private Integer id;

	@NotBlank(message = "La nombre no debe estar en blanco")
	@NotNull(message = "La nombre debe contener valor")
	@Size(max = 40, message = "El tamaño no debe ser mayor a 40")
	@Column(name = "plan_name", length = 40, nullable = false)
	private String name;

	@DecimalMax(value = "9999999999.99")
	@DecimalMin(value = "0.99")
	@Column(name = "plan_amount", columnDefinition = "DECIMAL(12,2)", nullable = false)
	private Double amount;

	@NotBlank(message = "El descripción no debe estar en blanco")
	@NotNull(message = "La descripción debe contener valor")
	@Size(max = 500, message = "El tamaño no debe ser mayor a 500")
	@Column(name = "plan_description", length = 500, nullable = false)
	private String description;

	@Column(name = "plan_state", nullable = false)
	private Boolean state;

	@OneToMany(mappedBy = "plan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PlanHistory> plansHistory;

	public Plan() {
		plansHistory = new ArrayList<PlanHistory>();
	}

	

	public Plan(Integer id,
			@NotBlank(message = "La nombre no debe estar en blanco") @NotNull(message = "La nombre debe contener valor") @Size(max = 40, message = "El tamaño no debe ser mayor a 40") String name,
			@DecimalMax("9999999999.99") @DecimalMin("0.99") Double amount,
			@NotBlank(message = "El descripción no debe estar en blanco") @NotNull(message = "La descripción debe contener valor") @Size(max = 500, message = "El tamaño no debe ser mayor a 500") String description,
			Boolean state, List<PlanHistory> plansHistory) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.description = description;
		this.state = state;
		this.plansHistory = plansHistory;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Boolean getState() {
		return state;
	}



	public void setState(Boolean state) {
		this.state = state;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public List<PlanHistory> getPlansHistory() {
		return plansHistory;
	}

	public void setPlansHistory(List<PlanHistory> plansHistory) {
		this.plansHistory = plansHistory;
	}

}
