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
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Publications", indexes = {
		@Index(columnList = "publication_id", name = "publications_index_publication_id"),
		@Index(columnList = "publication_name", name = "publications_index_publication_name") })
@SequenceGenerator(name = "Publications_publication_id_seq", initialValue = 1, allocationSize = 1)
public class Publication {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Publications_publication_id_seq")
	@Column(name = "publication_id", length = 5, nullable = false)
	private Integer id;

	@NotBlank(message = "El nombre no debe estar en blanco")
	@NotNull(message = "El nombre debe contener valor")
	@Size(max = 100, message = "El tamaño no debe ser mayor a 100")
	@Column(name = "publication_name", length = 100, nullable = false)
	private String name;

	@Column(name = "publication_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;

	@NotBlank(message = "La descripcion no debe estar en blanco")
	@NotNull(message = "La descripcion debe contener valor")
	@Size(max = 500, message = "El tamaño no debe ser mayor a 500")
	@Column(name = "publication_description", length = 500, nullable = false)
	private String description;

	@ManyToOne()
	@JoinColumn(name = "startup_id", nullable = false)
	private Startup startup;

	@Size(max = 500, message = "El tamaño no debe ser mayor a 500")
	@Column(name = "publication_image", length = 500, nullable = true)
	private String image;

	@Size(max = 500, message = "El tamaño no debe ser mayor a 500")
	@Column(name = "publication_url", length = 500, nullable = true)
	private String url;

	
	@Transient
	private String dateText;
	
	
	public Publication() {

	}

	
	
	public Publication(Integer id,
			@NotBlank(message = "El nombre no debe estar en blanco") @NotNull(message = "El nombre debe contener valor") @Size(max = 100, message = "El tamaño no debe ser mayor a 100") String name,
			Date date,
			@NotBlank(message = "La descripcion no debe estar en blanco") @NotNull(message = "La descripcion debe contener valor") @Size(max = 500, message = "El tamaño no debe ser mayor a 500") String description,
			Startup startup, @Size(max = 500, message = "El tamaño no debe ser mayor a 500") String image,
			@Size(max = 500, message = "El tamaño no debe ser mayor a 500") String url, String dateText) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.description = description;
		this.startup = startup;
		this.image = image;
		this.url = url;
		this.dateText = dateText;
	}



	
	public String getDateText() {
		return dateText;
	}



	public void setDateText(String dateText) {
		this.dateText = dateText;
	}



	public Publication(Date date, Startup startup) {
		super();

		this.date = date;

		this.startup = startup;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Startup getStartup() {
		return startup;
	}

	public void setStartup(Startup startup) {
		this.startup = startup;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
