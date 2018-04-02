package project.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FanZone implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String administrator;
	
	@OneToMany
	private List<ThematicProps> thematicProps;
	
	public FanZone() {}
	
	public FanZone(String administrator) {
		this.administrator = administrator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		this.administrator = administrator;
	}

	public List<ThematicProps> getThematicProps() {
		return thematicProps;
	}

	public void setThematicProps(List<ThematicProps> thematicProps) {
		this.thematicProps = thematicProps;
	}
	
}
