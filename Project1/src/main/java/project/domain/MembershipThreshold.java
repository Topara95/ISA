package project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MembershipThreshold implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private int bronzeThreshold;
	
	@Column
	private int silverThreshold;
	
	@Column
	private int goldThreshold;
	
	@Column
	private boolean valid;
	
	public MembershipThreshold() {
		
	}
	
	public MembershipThreshold(int bronze,int silver,int gold,boolean valid) {
		this.bronzeThreshold = bronze;
		this.silverThreshold = silver;
		this.goldThreshold = gold;
		this.valid = valid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBronzeThreshold() {
		return bronzeThreshold;
	}

	public void setBronzeThreshold(int bronzeThreshold) {
		this.bronzeThreshold = bronzeThreshold;
	}

	public int getSilverThreshold() {
		return silverThreshold;
	}

	public void setSilverThreshold(int silverThreshold) {
		this.silverThreshold = silverThreshold;
	}

	public int getGoldThreshold() {
		return goldThreshold;
	}

	public void setGoldThreshold(int goldThreshold) {
		this.goldThreshold = goldThreshold;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
