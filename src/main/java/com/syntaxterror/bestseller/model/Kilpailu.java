package com.syntaxterror.bestseller.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


@EnableAutoConfiguration
@Entity
public class Kilpailu {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kilpailu_id")
	private Long kilpailuId;

	@Column(name = "kilpailu_nimi")
	@NotNull
	private String nimi;

    @Temporal(TemporalType.DATE)
	@Column(name = "kilpailu_pvm")
	private Date pvm;

	@Column(name = "kilpailu_paikka")
	@NotNull
	private String paikka;

	@OneToMany
	private List<Lohko> lohkot;

	@OneToMany
	private List<Arviointi> arvioinnit;
	
	private Long paiva;
	
	private Long kuukausi;
	
	private Long vuosi;

	public Kilpailu() {
		super();
		this.nimi = null;
		this.pvm = null;
		this.paikka = null;
		this.paiva= null;
		this.kuukausi = null;
		this.vuosi = null;
	}

	public Kilpailu(String nimi, Date pvm, String paikka, Long paiva, Long kuukausi, Long vuosi) {
		super();
		this.paiva=paiva;
		this.kuukausi=kuukausi;
		this.vuosi=vuosi;
		this.nimi = nimi;
		this.pvm = pvm;
		this.paikka = paikka;
	}

	
	
	public Long getPaiva() {
		return paiva;
	}

	public void setPaiva(Long paiva) {
		this.paiva = paiva;
	}

	public Long getKuukausi() {
		return kuukausi;
	}

	public void setKuukausi(Long kuukausi) {
		this.kuukausi = kuukausi;
	}

	public Long getVuosi() {
		return vuosi;
	}

	public void setVuosi(Long vuosi) {
		this.vuosi = vuosi;
	}

	public Long getkilpailuId() {
		return kilpailuId;
	}

	public void setkilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public Date getPvm() {
		return pvm;
	}

	public void setPvm(Date pvm) {
		this.pvm = pvm;
	}

	public String getPaikka() {
		return paikka;
	}

	public void setPaikka(String paikka) {
		this.paikka = paikka;
	}

	public Long getKilpailuId() { return kilpailuId; }

	public void setKilpailuId(Long kilpailuId) { this.kilpailuId = kilpailuId; }

    public List<Lohko> getLohkot() {
        return lohkot;
    }

    public void setLohkot(List<Lohko> lohkot) {
        this.lohkot = lohkot;
    }

	@Override
	public String toString() {
		return "Kilpailu [kilpailuId=" + kilpailuId + ", nimi=" + nimi + ", pvm=" + pvm + ", paikka=" + paikka
				+ ", lohkot=" + lohkot + ", arvioinnit=" + arvioinnit + ", paiva=" + paiva + ", kuukausi=" + kuukausi
				+ ", vuosi=" + vuosi + "]";
	}

    
	
}