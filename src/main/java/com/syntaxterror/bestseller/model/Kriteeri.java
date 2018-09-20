package com.syntaxterror.bestseller.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Kriteeri {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "kriteeri_id")
    private Long kriteeriId;

    @Column(name = "kriteeri_teksti")
    @NotNull
    private String kriteeriTeksti;

    @Min(0)
    @Max(7)
    @Column(name = "kriteeri_piste")
    @NotNull
    private Integer kriteeriPiste;

    public Kriteeri() {
    	this.kriteeriTeksti=null;
    	this.kriteeriPiste=0;
    }

    @Override
	public String toString() {
		return "Kriteeri [kriteeriId=" + kriteeriId + ", kriteeriTeksti=" + kriteeriTeksti + ", kriteeriPiste="
				+ kriteeriPiste + "]";
	}

	public Kriteeri(String kriteeriTeksti, Integer kriteeriPiste) {
		super();
		this.kriteeriTeksti = kriteeriTeksti;
		this.kriteeriPiste = kriteeriPiste;
	}

	public Long getKriteeriId() {
        return kriteeriId;
    }

    public void setKriteeriId(Long kriteeriId) {
        this.kriteeriId = kriteeriId;
    }

    public String getKriteeriTeksti() {
        return kriteeriTeksti;
    }

    public void setKriteeriTeksti(String kriteeriTeksti) {
        this.kriteeriTeksti = kriteeriTeksti;
    }

    public Integer getKriteeriPiste() {
        return kriteeriPiste;
    }

    public void setKriteeriPiste(Integer kriteeriPiste) {
        this.kriteeriPiste = kriteeriPiste;
    }

}

