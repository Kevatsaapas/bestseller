package com.syntaxterror.bestseller.model;

import javax.persistence.*;

@Entity
public class Tuomari {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "tuomari_id")
    private Long tuomariId;

    @Column(name = "tuomari_nro")
    private String tuomariNro;

    // Oletuksella, että joka lohkossa eri tuomarit
    @Column(name = "lohko_id")
    private String lohkoNro;
    
    @Column(name = "kilpailu_id")
    private Long kilpailuId;


    public Tuomari() {
    	this.tuomariNro = null;
    	this.lohkoNro = null;
    	this.kilpailuId=null;
    }



	public Tuomari(String tuomariNro, String lohkoNro, Long kilpailuId) {
		super();
		this.tuomariNro = tuomariNro;
		this.lohkoNro = lohkoNro;
		this.kilpailuId=kilpailuId;
	}

	
	
	public Long getKilpailuId() {
		return kilpailuId;
	}



	public void setKilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
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




	@Override
	public String toString() {
		return "Tuomari [tuomariId=" + tuomariId + ", tuomariNro=" + tuomariNro + ", lohkoNro=" + lohkoNro
				+ ", kilpailuId=" + kilpailuId + "]";
	}




}
