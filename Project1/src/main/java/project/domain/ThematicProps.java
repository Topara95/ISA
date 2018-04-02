package project.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ThematicProps implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String cratedBy;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private Date date;
	
	@Column(nullable = true)
	private String picture;
	
	@Column(nullable = false)
	private ThematicPropsType tptype;
	
	@OneToMany
	private List<Offer> offers;

	public ThematicProps() {}
	
	public ThematicProps(ThematicPropsType tptype,String createdBy, String name,String description,Date date,String picture) {
		this.tptype = tptype;
		this.name = name;
		this.description = description;
		this.date = date;
		this.picture = picture;	
		this.cratedBy = createdBy;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public String getCratedBy() {
		return cratedBy;
	}

	public void setCratedBy(String cratedBy) {
		this.cratedBy = cratedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public ThematicPropsType getTptype() {
		return tptype;
	}

	public void setTptype(ThematicPropsType tptype) {
		this.tptype = tptype;
	}
	
	
}
