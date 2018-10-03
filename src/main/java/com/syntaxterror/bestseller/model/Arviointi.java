package com.syntaxterror.bestseller.model;

import com.syntaxterror.bestseller.model.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Arviointi implements Comparable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "arviointi_id")
    private Long arviointiId;

    @Temporal(TemporalType.DATE)
    @Column(name = "arviointi_pvm")
    private Date arviointiPvm;

    @ManyToOne
    @JoinColumn(name = "kilpailija_id")
    private Kilpailija kilpailija;

    // Jos kaikki tuomarit antaa erikseen arvioinnit

    @ManyToOne
    @JoinColumn(name = "tuomari_id")
    private Tuomari tuomari;

    @Column
    private Long kilpailuId;
    
    @Embedded
    private Aloitus aloitus;

    @Embedded
    private KysymystenKasittely kysymystenKasittely;

    @Embedded
    private Paattaminen paattaminen;

    @Embedded
    private Ratkaisu ratkaisu;

    @Embedded
    private Tarvekartoitus tarvekartoitus;

    @Embedded
    private Yleisvaikutelma yleisvaikutelma;

    public Arviointi() {
    	this.arviointiPvm=null;
    	this.kilpailija=null;
    	this.tuomari=null;
    	this.kilpailuId=null;
    }

	public Arviointi( Date arviointiPvm, Kilpailija kilpailija, Tuomari tuomari, Long kilpailuId) {
		super();
		this.arviointiPvm = arviointiPvm;
		this.kilpailija = kilpailija;
		this.tuomari = tuomari;
		this.kilpailuId = kilpailuId;
	}

	public Long getKilpailuId() {
		return kilpailuId;
	}

	public void setKilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
	}

	public Long getArviointiId() {
        return arviointiId;
    }

    public void setArviointiId(Long arviointiId) {
        this.arviointiId = arviointiId;
    }

    public Date getArviointiPvm() {
        return arviointiPvm;
    }

    public void setArviointiPvm(Date arviointiPvm) {
        this.arviointiPvm = arviointiPvm;
    }

    public Kilpailija getKilpailija() {
        return kilpailija;
    }

    public void setKilpailija(Kilpailija kilpailija) {
        this.kilpailija = kilpailija;
    }

    public Tuomari getTuomari() {
        return tuomari;
    }

    public void setTuomari(Tuomari tuomari) {
        this.tuomari = tuomari;
    }

    public Aloitus getAloitus() {
        return aloitus;
    }

    public void setAloitus(Aloitus aloitus) {
        this.aloitus = aloitus;
    }

    public KysymystenKasittely getKysymystenKasittely() {
        return kysymystenKasittely;
    }

    public void setKysymystenKasittely(KysymystenKasittely kysymystenKasittely) {
        this.kysymystenKasittely = kysymystenKasittely;
    }

    public Paattaminen getPaattaminen() {
        return paattaminen;
    }

    public void setPaattaminen(Paattaminen paattaminen) {
        this.paattaminen = paattaminen;
    }

    public Ratkaisu getRatkaisu() {
        return ratkaisu;
    }

    public void setRatkaisu(Ratkaisu ratkaisu) {
        this.ratkaisu = ratkaisu;
    }

    public Tarvekartoitus getTarvekartoitus() {
        return tarvekartoitus;
    }

    public void setTarvekartoitus(Tarvekartoitus tarvekartoitus) {
        this.tarvekartoitus = tarvekartoitus;
    }

    public Yleisvaikutelma getYleisvaikutelma() {
        return yleisvaikutelma;
    }

    public void setYleisvaikutelma(Yleisvaikutelma yleisvaikutelma) {
        this.yleisvaikutelma = yleisvaikutelma;
    }

	@Override
	public String toString() {
		return "Arviointi [arviointiId=" + arviointiId + ", arviointiPvm=" + arviointiPvm + ", kilpailija=" + kilpailija
				+ ", tuomari=" + tuomari + ", kilpailuId=" + kilpailuId + ", aloitus=" + aloitus
				+ ", kysymystenKasittely=" + kysymystenKasittely + ", paattaminen=" + paattaminen + ", ratkaisu="
				+ ratkaisu + ", tarvekartoitus=" + tarvekartoitus + ", yleisvaikutelma=" + yleisvaikutelma + "]";
	}


    @Override
    public int compareTo(Object o) {

        Arviointi toinen = (Arviointi) o;

        if (this.getKokonaistulos() - toinen.getKokonaistulos() > 0) return 1;
        else if (this.getKokonaistulos() - toinen.getKokonaistulos() == 0) return 0;
        else return -1;
    }

    public double getKokonaistulos() {

        double painotettuAloitus = this.aloitus.getKokonaistulos() * 0.1;
        double painotettuKasittely = this.kysymystenKasittely.getKokonaistulos() * 0.3;
        double painotettuPaattaminen = this.paattaminen.getKokonaistulos() * 0.25;
        double painotettuRatkaisu = this.ratkaisu.getKokonaistulos() * 0.1;
        double painotettuTarvekartoitus = this.tarvekartoitus.getKokonaistulos() * 0.1;
        double painotettuYleisvaikutelma = this.yleisvaikutelma.getKokonaistulos() * 0.1;

        return painotettuAloitus + painotettuKasittely + painotettuPaattaminen + painotettuRatkaisu + painotettuTarvekartoitus + painotettuYleisvaikutelma;
    }
}
