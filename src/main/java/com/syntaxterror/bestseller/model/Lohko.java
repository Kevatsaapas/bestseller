package com.syntaxterror.bestseller.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lohko {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "lohko_id")
    private Long lohkoId;

    @Column(name = "lohko_nro")
    private String lohkoNro;

    @ManyToOne
    @JoinColumn(name = "kilpailu_id")
    private Kilpailu kilpailu;

    @OneToMany
    @JoinColumn(name = "kilpailu_kilpailijat")
    private List<Kilpailija> kilpailijat;

    // ManyToMany??
    @OneToMany
    private List<Tuomari> tuomarit;

    @OneToOne
    @JoinColumn(name = "ostaja_id")
    private Ostaja ostaja;

    public List<Kilpailija> getKilpailijat() { return kilpailijat; }

    public void setKilpailijat(List<Kilpailija> kilpailijat) { this.kilpailijat = kilpailijat; }

    public Long getLohkoId() {
        return lohkoId;
    }

    public void setLohkoId(Long lohkoId) {
        this.lohkoId = lohkoId;
    }

    public String getLohkoNro() {
        return lohkoNro;
    }

    public void setLohkoNro(String lohkoNro) {
        this.lohkoNro = lohkoNro;
    }

    public Kilpailu getKilpailu() {
        return kilpailu;
    }

    public void setKilpailu(Kilpailu kilpailu) {
        this.kilpailu = kilpailu;
    }

    public List<Tuomari> getTuomarit() {
        return tuomarit;
    }

    public void setTuomarit(List<Tuomari> tuomarit) {
        this.tuomarit = tuomarit;
    }

    public Ostaja getOstaja() {
        return ostaja;
    }

    public void setOstaja(Ostaja ostaja) {
        this.ostaja = ostaja;
    }
}
