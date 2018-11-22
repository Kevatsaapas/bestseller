package com.syntaxterror.bestseller.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Ostaja {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ostaja_id")
	private Long ostajaId;

	@Column(name = "ostaja_etunimi")
	@NotNull
	public String ostajaEtunimi;

	@Column(name = "ostaja_sukunimi")
	@NotNull
	public String ostajaSukunimi;

	@Column(name = "lohko_id")
	public String lohkoNro;

	@Column(name = "kilpailu_id")
	private Long kilpailuId;

	@Column(name = "finaaliin")
	public Long finaaliin;

	public Ostaja(@NotNull String ostajaEtunimi, @NotNull String ostajaSukunimi, String lohkoNro, Long kilpailuId,
			Long finaaliin) {
		this.ostajaEtunimi = ostajaEtunimi;
		this.ostajaSukunimi = ostajaSukunimi;
		this.lohkoNro = lohkoNro;
		this.kilpailuId = kilpailuId;
		this.finaaliin = finaaliin;
	}

	public Ostaja() {

	}

	public Long getOstajaId() {
		return ostajaId;
	}

	public void setOstajaId(Long ostajaId) {
		this.ostajaId = ostajaId;
	}

	public String getOstajaNimi() {
		return ostajaEtunimi;
	}

	public void setOstajaNimi(String ostajaNimi) {
		this.ostajaEtunimi = ostajaNimi;
	}

	public String getLohkoNro() {
		return lohkoNro;
	}

	public void setLohkoNro(String lohkoNro) {
		this.lohkoNro = lohkoNro;
	}

	public Long getFinaaliin() {
		return finaaliin;
	}

	public void setFinaaliin(Long finaaliin) {
		this.finaaliin = finaaliin;
	}

	public String getOstajaEtunimi() {
		return ostajaEtunimi;
	}

	public void setOstajaEtunimi(String ostajaEtunimi) {
		this.ostajaEtunimi = ostajaEtunimi;
	}

	public String getOstajaSukunimi() {
		return ostajaSukunimi;
	}

	public void setOstajaSukunimi(String ostajaSukunimi) {
		this.ostajaSukunimi = ostajaSukunimi;
	}

	public Long getKilpailuId() {
		return kilpailuId;
	}

	public void setKilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
	}

	@Override
	public String toString() {
		return "Ostaja [ostajaId=" + ostajaId + ", ostajaNimi=" + ostajaEtunimi + "]";
	}

}
