package com.syntaxterror.bestseller.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class Kriteeri {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "kriteeri_id")
    private Long kriteeriId;

    @Column(name = "kriteeri_teksti")
    private String kriteeriTeksti;

    @Min(0)
    @Max(7)
    @Column(name = "kriteeri_piste")
    private Integer kriteeriPiste;

    
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

   

	public Kriteeri(String kriteeriTeksti, Integer kriteeriPiste, Long OsaAlueId, Long kilpailuId) {
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

    public Integer getKriteeriPiste() {
        return kriteeriPiste;
    }

    public void setKriteeriPiste(Integer kriteeriPiste) {
        this.kriteeriPiste = kriteeriPiste;
    }

}

