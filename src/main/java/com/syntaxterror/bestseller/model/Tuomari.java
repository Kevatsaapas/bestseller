package com.syntaxterror.bestseller.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Tuomari {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tuomari_id")
	private Long tuomariId;

	@Column(name = "tuomari_nro")
	@NotNull
	private String tuomariNro;

	@Column(name = "etunimi")
	private String etunimi;

	@Column(name = "sukunimi")
	private String sukunimi;

	// Oletuksella, että joka lohkossa eri tuomarit
	@Column(name = "lohko_id")
	private String lohkoNro;

	@Column(name = "kilpailu_id")
	private Long kilpailuId;

	@Column(name = "finaaliin")
	public Long finaaliin;

	public Tuomari() {
		this.tuomariNro = null;
		this.etunimi = null;
		this.sukunimi = null;
		this.lohkoNro = null;
		this.kilpailuId = null;
		this.finaaliin = null;
	}

	public Tuomari(String tuomariNro, String etunimi, String sukunimi, String lohkoNro, Long kilpailuId,
			Long finaaliin) {
		super();
		this.tuomariNro = tuomariNro;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.lohkoNro = lohkoNro;
		this.kilpailuId = kilpailuId;
		this.finaaliin = finaaliin;
	}

	public Long getKilpailuId() {
		return kilpailuId;
	}

	public void setKilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
	}

	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public String getSukunimi() {
		return sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public String getLohkoNro() {
		return lohkoNro;
	}

	public void setLohkoNro(String lohkoNro) {
		this.lohkoNro = lohkoNro;
	}

	public Long getTuomariId() {
		return tuomariId;
	}

	public void setTuomariId(Long tuomariId) {
		this.tuomariId = tuomariId;
	}

	public String getTuomariNro() {
		return tuomariNro;
	}

	public void setTuomariNro(String tuomariNro) {
		this.tuomariNro = tuomariNro;
	}

	public Long getfinaaliin() {
		return finaaliin;
	}

	public void setfinaaliin(Long finaaliin) {
		this.finaaliin = finaaliin;
	}

	@Override
	public String toString() {
		return "Tuomari [tuomariId=" + tuomariId + ", tuomariNro=" + tuomariNro + ", etunimi=" + etunimi + ", sukunimi="
				+ sukunimi + ", lohkoNro=" + lohkoNro + ", kilpailuId=" + kilpailuId + ", finaaliin=" + finaaliin + "]";
	}

}
