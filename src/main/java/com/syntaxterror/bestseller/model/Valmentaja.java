package com.syntaxterror.bestseller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Valmentaja {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "valmentaja_id")
    private Long valmentajaId;

    @Column(name = "valmentaja_etunimi")
    @NotNull
    private String etunimi;
    
    @Column(name = "valmentaja_sukunimi")
    @NotNull
    private String sukunimi;
    
    @Column(name = "kilpailu_Id")
    @NotNull
    private Long kilpailuId;
    
    @Column(name = "valmentaja_sahkoposti")
    @NotNull
    private String sposti;
    
    
    @Column(name = "koulu_id")
    private Long kouluId;
    
    @OneToOne
    private Koulu koulu;
    
    
    
    public String getSposti() {
		return sposti;
	}

	public void setSposti(String sposti) {
		this.sposti = sposti;
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
	

	public Long getValmentajaId() {
		return valmentajaId;
	}

	public void setValmentajaId(Long valmentajaId) {
		this.valmentajaId = valmentajaId;
	}

	
	public Long getKouluId() {
		return kouluId;
	}

	public void setKouluId(Long kouluId) {
		this.kouluId = kouluId;
	}

	public Koulu getKoulu() {
		return koulu;
	}

	public void setKoulu(Koulu koulu) {
		this.koulu = koulu;
	}

	public Long getKilpailuId() {
		return kilpailuId;
	}

	public void setKilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
	}

	@Override
	public String toString() {
		return "Valmentaja [valmentajaId=" + valmentajaId + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi
				+ ", kilpailuId=" + kilpailuId + ", sposti=" + sposti + ", kouluId=" + kouluId + ", koulu=" + koulu
				+ "]";
	}

	
	
}
