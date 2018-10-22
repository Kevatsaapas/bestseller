package com.syntaxterror.bestseller.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Kilpailija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "kilpailija_id")
    private Long kilpailijaId;

    @Column(name = "kilpailija_etunimi")
    @NotNull
    private String etunimi;

    @Column(name = "kilpailija_sukunimi")
    @NotNull
    private String sukunimi;

    @Column(name = "kilpailija_nro")
    @NotNull
    private int kilpailijaNro;

    @Column(name = "kilpailija_koulu")
    @NotNull
    private String koulu;

    @Column(name = "kilpailu_Id")
    @NotNull
    private Long kilpailuId;

    @ManyToOne
    private Lohko lohko;

    @OneToMany
    private List<Arviointi> arvioinnit;

    public Kilpailija() {
        this.etunimi = null;
        this.sukunimi = null;
        this.kilpailijaNro = 0;
        this.koulu = null;
        this.lohko = null;
        this.kilpailuId = null;
    }

    public Kilpailija(String etunimi, String sukunimi, int kilpailijaNro, String koulu, Lohko lohko, Long kilpailuId) {
        super();
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.kilpailijaNro = kilpailijaNro;
        this.koulu = koulu;
        this.lohko = lohko;
        this.kilpailuId = kilpailuId;
    }

    public Long getKilpailijaId() {
        return kilpailijaId;
    }

    public void setKilpailijaId(Long kilpailijaId) {
        this.kilpailijaId = kilpailijaId;
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

    public int getKilpailijaNro() {
        return kilpailijaNro;
    }

    public void setKilpailijaNro(int kilpailijaNro) {
        this.kilpailijaNro = kilpailijaNro;
    }

    public String getKoulu() {
        return koulu;
    }

    public void setKoulu(String koulu) {
        this.koulu = koulu;
    }

    public Lohko getLohko() {
        return lohko;
    }

    public void setLohko(Lohko lohko) {
        this.lohko = lohko;
    }

    public List<Arviointi> getArvioinnit() {
        return arvioinnit;
    }

    public void setArvioinnit(List<Arviointi> arvioinnit) {
        this.arvioinnit = arvioinnit;
    }

    public Long getKilpailuId() {
        return kilpailuId;
    }

    public void setKilpailuId(Long kilpailuId) {
        this.kilpailuId = kilpailuId;
    }

    @Override
    public String toString() {
        return "Kilpailija [kilpailijaId=" + kilpailijaId + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi
                + ", kilpailijaNro=" + kilpailijaNro + ", koulu=" + koulu + ", kilpailuId=" + kilpailuId + ", lohko="
                + lohko + "]";
    }
}
