package com.syntaxterror.bestseller.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kilpailu {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long kilpailuId;
	private String nimi;
	private Date pvm;
	private String paikka;
	
	public Kilpailu() {
		super();
		this.nimi = null;
		this.pvm = null;
		this.pvm = null;
		this.paikka = null;
	}

	public Kilpailu(String nimi, Date pvm, String paikka) {
		super();
		this.nimi = nimi;
		this.pvm = pvm;
		this.paikka = paikka;
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

	@Override
	public String toString() {
		return "Kilpailu [kilpailuId=" + kilpailuId + ", nimi=" + nimi + ", pvm=" + pvm + ", paikka=" + paikka + "]";
	}
	
	
}