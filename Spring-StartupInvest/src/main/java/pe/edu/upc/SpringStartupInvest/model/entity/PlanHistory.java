package pe.edu.upc.SpringStartupInvest.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PlansHistory", indexes = {
		@Index(columnList = "plan_history_id", name = "plansHistory_index_plan_history_id") })
@SequenceGenerator(name = "PlansHistory_plan_history_id_seq", initialValue = 1, allocationSize = 1)
public class PlanHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PlansHistory_plan_history_id_seq")
	@Column(name = "plan_history_id", length = 5, nullable = false)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "startup_id", nullable = false)
	private Startup startup;

	@ManyToOne()
	@JoinColumn(name = "plan_id", nullable = false)
	private Plan plan;

	@ManyToOne()
	@JoinColumn(name = "type_card_id", nullable = false)
	private TypeCard typeCard;

	
	@Column(name = "plan_history_acquisition_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date acquistionDate;

	
	@Column(name = "plan_history_expiration_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	public PlanHistory() {

	}

	public PlanHistory(Integer id, Startup startup, Plan plan, TypeCard typeCard, Date acquistionDate,
			Date expirationDate) {
		super();
		this.id = id;
		this.startup = startup;
		this.plan = plan;
		this.typeCard = typeCard;
		this.acquistionDate = acquistionDate;
		this.expirationDate = expirationDate;
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

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public TypeCard getTypeCard() {
		return typeCard;
	}

	public void setTypeCard(TypeCard typeCard) {
		this.typeCard = typeCard;
	}

	public Date getAcquistionDate() {
		return acquistionDate;
	}

	public void setAcquistionDate(Date acquistionDate) {
		this.acquistionDate = acquistionDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
