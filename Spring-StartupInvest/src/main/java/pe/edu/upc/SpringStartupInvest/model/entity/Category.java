package pe.edu.upc.SpringStartupInvest.model.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "Categories", indexes = { @Index(columnList = "category_id", name = "categories_index_category_id"),
		@Index(columnList = "category_name", name = "categories_index_category_name"),
		@Index(columnList = "category_state", name = "categories_index_category_state") })
@SequenceGenerator(name = "Categories_category_id_seq", initialValue = 100, allocationSize = 1)
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Categories_category_id_seq")
	@Column(name = "category_id", length = 5, nullable = false)
	private Integer id;

	@NotBlank(message = "El name no debe estar en blanco")
	@NotNull(message = "El name debe contener valor")
	@Size(max=40,message = "El tamaño máximo del nombre de la categoria es 40")
	@Column(name = "category_name", length = 40, nullable = false)
	private String name;

	@Size(max=500,message = "El tamaño máximo del nombre de la categoria es 500")
	@Column(name = "category_image", length = 500, nullable = true)
	private String image;

	@Column(name = "category_state", nullable = false)
	private boolean state;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Startup> startups;

//LAZY-->ATRIBUTOS
//EAGER-->LISTA + ATRIBUTOS	
	public Category() {
		startups = new ArrayList<Startup>();
	}
//gagaga

	

	public Integer getId() {
		return id;
	}

	public Category(Integer id,
		@NotBlank(message = "El name no debe estar en blanco") @NotNull(message = "El name debe contener valor") @Size(max = 40, message = "El tamaño máximo del nombre de la categoria es 40") String name,
		@Size(max = 500, message = "El tamaño máximo del nombre de la categoria es 500") String image, boolean state,
		List<Startup> startups) {
	super();
	this.id = id;
	this.name = name;
	this.image = image;
	this.state = state;
	this.startups = startups;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public List<Startup> getStartups() {
		return startups;
	}

	public void setStartups(List<Startup> startups) {
		this.startups = startups;
	}

}
