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
    private int kriteeriPiste;
    
    
    @Column(name = "osaAlue_id")
    private Long OsaAlueId;
    
    @Column(name = "kilpailuId")
    private Long kilpailuId;
    
    public Kriteeri() {
    	this.kriteeriTeksti=null;
    	this.kriteeriPiste=0;
    	this.OsaAlueId=null;
    	this.kilpailuId=null;
    }

   

	public Kriteeri(String kriteeriTeksti, int kriteeriPiste, Long OsaAlueId, Long kilpailuId) {
		super();
		this.kriteeriTeksti = kriteeriTeksti;
		this.kriteeriPiste = kriteeriPiste;
		this.OsaAlueId=OsaAlueId;
    	this.kilpailuId=kilpailuId;
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

    public int getKriteeriPiste() {
        return kriteeriPiste;
    }

    public void setKriteeriPiste(int kriteeriPiste) {
        this.kriteeriPiste = kriteeriPiste;
    }

}

