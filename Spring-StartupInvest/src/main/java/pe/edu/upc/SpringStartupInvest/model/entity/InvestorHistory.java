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


@Entity
@Table(name = "InvestorHistories",indexes = {@Index(columnList = "investor_history_id", name = "investorHistories_index_investor_history_id")})
@SequenceGenerator(name = "Investor_histories_investor_history_id_seq", initialValue = 1, allocationSize = 1)
public class InvestorHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Investor_histories_investor_history_id_seq")
	@Column(name = "investor_history_id",length=5,nullable = false)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "investor_id", nullable = false)
	private Investor investor;

	@ManyToOne()
	@JoinColumn(name = "investment_request_id", nullable = false)
	private InvestmentRequest investmentRequest;

	@Column(name = "investor_history_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;

	
	@Column(name = "investor_history_amount",nullable =false)
	private Double amount;
	
	@Column(name = "investor_history_state",nullable = false)
	private Boolean state;
	
	@ManyToOne()
	@JoinColumn(name = "type_card_id", nullable = false)
	private TypeCard typeCard;

	public InvestorHistory() {
		super();
	}
	public InvestorHistory(Integer id, Investor investor, InvestmentRequest investmentRequest, Date date, Double amount,
			Boolean state, TypeCard typeCard) {
		super();
		this.id = id;
		this.investor = investor;
		this.investmentRequest = investmentRequest;
		this.date = date;
		this.amount = amount;
		this.state = state;
		this.typeCard = typeCard;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public InvestmentRequest getInvestmentRequest() {
		return investmentRequest;
	}

	public void setInvestmentRequest(InvestmentRequest investmentRequest) {
		this.investmentRequest = investmentRequest;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public TypeCard getTypeCard() {
		return typeCard;
	}

	public void setTypeCard(TypeCard typeCard) {
		this.typeCard = typeCard;
	}


	
	
	
}
