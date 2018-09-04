package com.syntaxterror.bestseller.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OsaAlue {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long osaAlueId;
	private String nimi;
	private Long painoarvo;
	private Long kilpailuId;
	
	public OsaAlue() {
		super();
		this.nimi=null;
		this.painoarvo=null;
		this.kilpailuId=null;
	}

	public OsaAlue(String nimi, Long painoarvo, Long kilpailuId) {
		super();
		this.nimi = nimi;
		this.painoarvo = painoarvo;
		this.kilpailuId = kilpailuId;
	}

	public Long getOsaAlueId() {
		return osaAlueId;
	}

	public void setOsaAlueId(Long osaAlueId) {
		this.osaAlueId = osaAlueId;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public Long getPainoarvo() {
		return painoarvo;
	}

	public void setPainoarvo(Long painoarvo) {
		this.painoarvo = painoarvo;
	}

	public Long getKilpailuId() {
		return kilpailuId;
	}

	public void setKilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
	}

	@Override
	public String toString() {
		return "OsaAlue [osaAlueId=" + osaAlueId + ", nimi=" + nimi + ", painoarvo=" + painoarvo + ", kilpailuId="
				+ kilpailuId + "]";
	}
	
	
	
	
	
	
}