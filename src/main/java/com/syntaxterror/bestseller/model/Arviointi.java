package com.syntaxterror.bestseller.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.syntaxterror.bestseller.model.util.Aloitus;
import com.syntaxterror.bestseller.model.util.KysymystenKasittely;
import com.syntaxterror.bestseller.model.util.Paattaminen;
import com.syntaxterror.bestseller.model.util.Ratkaisu;
import com.syntaxterror.bestseller.model.util.Tarvekartoitus;
import com.syntaxterror.bestseller.model.util.Yleisvaikutelma;

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

    @ManyToOne
    @JoinColumn(name = "lohko_id")
    @NotFound(action=NotFoundAction.IGNORE)
    private Lohko lohko;

    @Column
    private Long kilpailuId;
    
    @Column
    private double kokonaistulos;

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
    	this.kokonaistulos=0;
    	this.lohko=null;
    }

	public Arviointi( Date arviointiPvm, Kilpailija kilpailija, Tuomari tuomari, Long kilpailuId, double kokonaistulos, Lohko lohko) {
		super();
		this.arviointiPvm = arviointiPvm;
		this.kilpailija = kilpailija;
		this.tuomari = tuomari;
		this.kilpailuId = kilpailuId;
		this.kokonaistulos = kokonaistulos;
		this.lohko = lohko;
	}

	
	
	public void setKokonaistulos(double kokonaistulos) {
		this.kokonaistulos = kokonaistulos;
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

    public Lohko getLohko() {
        return lohko;
    }

    public void setLohko(Lohko lohko) {
        this.lohko = lohko;
    }

    @Override
	public String toString() {
		return "Arviointi [arviointiId=" + arviointiId + ", arviointiPvm=" + arviointiPvm + ", kilpailija=" + kilpailija
				+ ", tuomari=" + tuomari + ", kilpailuId=" + kilpailuId + ", kokonaistulos=" + kokonaistulos
				+ ", aloitus=" + aloitus + ", kysymystenKasittely=" + kysymystenKasittely + ", paattaminen="
				+ paattaminen + ", ratkaisu=" + ratkaisu + ", tarvekartoitus=" + tarvekartoitus + ", yleisvaikutelma="
				+ yleisvaikutelma + "]";
	}

	@Override
    public int compareTo(Object o) {

        Arviointi toinen = (Arviointi) o;

        if (this.getKokonaistulos() - toinen.getKokonaistulos() > 0) return 1;
        else if (this.getKokonaistulos() - toinen.getKokonaistulos() == 0) return 0;
        else return -1;
    }

	public double getKokonaistulos() {
		return kokonaistulos;
	}

    
}
