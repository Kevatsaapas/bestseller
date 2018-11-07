package com.syntaxterror.bestseller.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Valmentaja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "valmentaja_id")
    private Long valmentajaId;

    @Column(name = "valmentaja_etunimi")
    @NotNull
    private String etunimi;

    @Column(name = "valmentaja_sukunimi")
    @NotNull
    private String sukunimi;


    @OneToOne
    @JoinColumn(name = "koulu_Id")
    private Koulu koulu;

    @Column(name = "valmentaja_sahkoposti")
    @NotNull
    private String sposti;

    @Column(name = "kilpailu_Id")
    @NotNull
    private Long kilpailuId;

   

    public Valmentaja() {
        this.etunimi = null;
        this.sukunimi = null;
        this.koulu = null;
        this.sposti = null;
        this.kilpailuId = null;
    }

    public Valmentaja(String etunimi, String sukunimi, Koulu koulu,String sposti, Long kilpailuId) {
        super();
        this.etunimi = etunimi;
        this.sukunimi = sukunimi; 
        this.koulu = koulu;
        this.sposti = sposti;
        this.kilpailuId = kilpailuId;
    }

	public Long getValmentajaId() {
		return valmentajaId;
	}

	public void setValmentajaId(Long valmentajaId) {
		this.valmentajaId = valmentajaId;
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

	public Koulu getKoulu() {
		return koulu;
	}

	public void setKoulu(Koulu koulu) {
		this.koulu = koulu;
	}

	public String getSposti() {
		return sposti;
	}

	public void setSposti(String sposti) {
		this.sposti = sposti;
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
				+ ", koulu=" + koulu + ", sposti=" + sposti + ", kilpailuId=" + kilpailuId + "]";
	}

}

   

