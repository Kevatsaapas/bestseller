package com.syntaxterror.bestseller.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;


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

    @Temporal(TemporalType.DATE)
	@Column(name = "kilpailu_pvm")
	private Date pvm;

	@Column(name = "kilpailu_paikka")
	@NotNull
	private String paikka;
	
	@Column(name = "testi")
	@NotNull
	private Long testi;

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
	}

	public Kilpailu(String nimi, Date pvm, String paikka, Long testi) {
		super();
		this.nimi = nimi;
		this.pvm = pvm;
		this.paikka = paikka;
		this.testi = testi;
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

	public Date getPvm() {
		return pvm;
	}

	public void setPvm(Date pvm) {
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
				+ ", testi=" + testi + ", lohkot=" + lohkot + ", arvioinnit=" + arvioinnit + "]";
	}

    
	
	
}