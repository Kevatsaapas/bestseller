package com.syntaxterror.bestseller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Koulu {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "koulu_id")
	private Long kouluId;

	@Column(name = "koulu_nimi")
	@NotNull
	private String kouluNimi;

	@Column(name = "koulu_kaupunki")
	private String kouluKaupunki;

	@Column(name = "kilpailu_id")
	private Long kilpailuId;

	public Koulu(String kouluNimi, String kouluKaupunki, Long kilpailuId) {
		super();
		this.kouluNimi = kouluNimi;
		this.kouluKaupunki = kouluKaupunki;
		this.kilpailuId = kilpailuId;
	}

	public Koulu() {
		super();
		this.kouluNimi = null;
		this.kouluKaupunki = null;
		this.kilpailuId = null;
	}

	public Long getKilpailuId() {
		return kilpailuId;
	}

	public void setKilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
	}

	public Long getKouluId() {
		return kouluId;
	}

	public void setKouluId(Long kouluId) {
		this.kouluId = kouluId;
	}

	public String getKouluNimi() {
		return kouluNimi;
	}

	public void setKouluNimi(String kouluNimi) {
		this.kouluNimi = kouluNimi;
	}

	public String getKouluKaupunki() {
		return kouluKaupunki;
	}

	public void setKouluKaupunki(String kouluKaupunki) {
		this.kouluKaupunki = kouluKaupunki;
	}

	@Override
	public String toString() {
		return "Koulu [kouluId=" + kouluId + ", kouluNimi=" + kouluNimi + ", kouluKaupunki=" + kouluKaupunki
				+ ", kilpailuId=" + kilpailuId + "]";
	}

}
