package pe.edu.upc.SpringStartupInvest.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TypeCardHistories")
@SequenceGenerator(name = "TypeCardHistories_type_card_history_id_seq", initialValue = 1, allocationSize = 1)
public class TypeCardHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TypeCardHistories_type_card_history_id_seq")
	@Column(name = "type_card_history_id", length = 5, nullable = false)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "inversionista_id", nullable = false)
	private Investor investor;

	@ManyToOne()
	@JoinColumn(name = "type_card_id", nullable = false)
	private TypeCard typeCard;

	@NotBlank(message = "El número no debe estar en blanco")
	@NotNull(message="El número debe contener valor")
	@Size(max=17,message="El tamaño no debe ser mayor a 17")
	@Column(name = "type_card_detail_number", length = 17, nullable = false)
	private String detailNumber;

	public TypeCardHistory(Integer id, Investor investor, TypeCard typeCard, String detailNumber) {
		super();
		this.id = id;
		this.investor = investor;
		this.typeCard = typeCard;
		this.detailNumber = detailNumber;
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

	public TypeCard getTypeCard() {
		return typeCard;
	}

	public void setTypeCard(TypeCard typeCard) {
		this.typeCard = typeCard;
	}

	public String getDetailNumber() {
		return detailNumber;
	}

	public void setDetailNumber(String detailNumber) {
		this.detailNumber = detailNumber;
	}

}
