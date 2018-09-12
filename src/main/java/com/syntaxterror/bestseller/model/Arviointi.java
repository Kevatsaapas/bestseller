package com.syntaxterror.bestseller.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Arviointi {

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

    @ManyToMany(mappedBy = "arvioinnit")
    private List<OsaAlue> osaAlueet;

    public Arviointi() {
    	this.arviointiPvm=null;
    	this.kilpailija=null;
    	this.tuomari=null;
    }

	public Arviointi( Date arviointiPvm, Kilpailija kilpailija, Tuomari tuomari) {
		super();
		this.arviointiPvm = arviointiPvm;
		this.kilpailija = kilpailija;
		this.tuomari = tuomari;
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

    public List<OsaAlue> getOsaAlueet() {
        return osaAlueet;
    }

    public void setOsaAlueet(List<OsaAlue> osaAlueet) {
        this.osaAlueet = osaAlueet;
    }

<<<<<<< HEAD
=======
    @Override
   	public String toString() {
   		return "Arviointi [arviointiId=" + arviointiId + ", arviointiPvm=" + arviointiPvm + ", kilpailija=" + kilpailija
   				+ ", tuomari=" + tuomari + ", osaAlueet=" + osaAlueet + "]";
   	}
    
>>>>>>> testaus
}
