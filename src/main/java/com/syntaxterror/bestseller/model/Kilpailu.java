package com.syntaxterror.bestseller.model;


import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@EnableAutoConfiguration
@Entity
public class Kilpailu {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kilpailu_id")
	private Long kilpailuId;

	@Column(name = "kilpailu_nimi")
	@NotNull
	private String nimi;

	@Column(name = "kilpailu_pvm")
	private String pvm;

	@Column(name = "kilpailu_paikka")
	@NotNull
	private String paikka;

	@Column(name = "testi")
	@NotNull
	private Long testi;
	
	@Column(name = "finaali")
	private Long finaali;
	
	@Column(name = "auki")
	private Long auki;
	
	@Column(name = "valmis")
	private Long valmis;

	@OneToMany
	private List<Lohko> lohkot;

	@OneToMany
	private List<Arviointi> arvioinnit;



	public Kilpailu() {
		super();
		this.nimi = null;
		this.pvm = null;
		this.paikka = null;
		this.testi=null;
		this.finaali=null;
		this.auki=null;
		this.valmis=null;
	}

	public Kilpailu(String nimi, String pvm, String paikka, Long testi, Long finaali, Long auki, Long valmis) {
		super();
		this.nimi = nimi;
		this.pvm = pvm;
		this.paikka = paikka;
		this.testi = testi;
		this.finaali = finaali;
		this.auki = auki;
		this.valmis = valmis;
	}




	public Long getkilpailuId() {
		return kilpailuId;
	}

	public void setkilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getPvm() {
		return pvm;
	}

	public void setPvm(String pvm) {
		this.pvm = pvm;
	}

	public String getPaikka() {
		return paikka;
	}

	public void setPaikka(String paikka) {
		this.paikka = paikka;
	}


	public Long getTesti() {
		return testi;
	}

	public void setTesti(Long testi) {
		this.testi = testi;
	}
	
	

	public Long getFinaali() {
		return finaali;
	}

	public void setFinaali(Long finaali) {
		this.finaali = finaali;
	}

	public Long getAuki() {
		return auki;
	}

	public void setAuki(Long auki) {
		this.auki = auki;
	}

	
	
	public Long getValmis() {
		return valmis;
	}

	public void setValmis(Long valmis) {
		this.valmis = valmis;
	}

	public Long getKilpailuId() { return kilpailuId; }

	public void setKilpailuId(Long kilpailuId) { this.kilpailuId = kilpailuId; }

    public List<Lohko> getLohkot() {
        return lohkot;
    }
    

    public void setLohkot(List<Lohko> lohkot) {
        this.lohkot = lohkot;
    }

	@Override
	public String toString() {
		return "Kilpailu [kilpailuId=" + kilpailuId + ", nimi=" + nimi + ", pvm=" + pvm + ", paikka=" + paikka
				+ ", testi=" + testi + ", finaali=" + finaali + ", auki=" + auki + ", valmis=" + valmis + ", lohkot="
				+ lohkot + ", arvioinnit=" + arvioinnit + "]";
	}

	

	

	
	
	
}