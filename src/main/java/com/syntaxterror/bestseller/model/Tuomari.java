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

    @ManyToOne
    private Lohko lohko;

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

    public Lohko getLohko() {
        return lohko;
    }

    public void setLohko(Lohko lohko) {
        this.lohko = lohko;
    }

}
