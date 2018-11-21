package com.syntaxterror.bestseller.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kysymys {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kysymys_id")
    private Long kysymysId;

    @Column(name = "kysymys_teksti")
    private String kysymysTeksti;

    @Column(name = "kysymys_psiteet")
    private Integer vastausPisteet;

    public Kysymys(String kysymysTeksti, Integer vastausPisteet) {
        this.kysymysTeksti = kysymysTeksti;
        this.vastausPisteet = vastausPisteet;
    }

    public Kysymys(String kysymysTeksti){
        this.kysymysTeksti = kysymysTeksti;
    }

    public Integer getVastausPisteet() {
        return vastausPisteet;
    }

    public void setVastausPisteet(Integer vastausPisteet) {
        this.vastausPisteet = vastausPisteet;
    }

    public String getKysymysTeksi() {
        return kysymysTeksti;
    }

    public void setKysymysTeksi(String kysymysTeksi) {
        this.kysymysTeksti = kysymysTeksi;
    }
}
