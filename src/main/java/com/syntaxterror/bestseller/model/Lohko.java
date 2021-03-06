package com.syntaxterror.bestseller.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.List;

@Entity
public class Lohko {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lohko_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Long lohkoId;

	@Column(name = "lohko_nro")
	@NotNull
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

	public List<Kilpailija> getKilpailijat() {
		return kilpailijat;
	}

	public void setKilpailijat(List<Kilpailija> kilpailijat) {
		this.kilpailijat = kilpailijat;
	}

	public Lohko() {
		this.lohkoNro = null;
		this.kilpailu = null;
		this.ostaja = null;
	}

	public Lohko(String lohkoNro, Kilpailu kilpailu, Ostaja ostaja) {
		super();
		this.lohkoNro = lohkoNro;
		this.kilpailu = kilpailu;
		this.ostaja = ostaja;
	}

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

	@Override
	public String toString() {
		return "Lohko [lohkoId=" + lohkoId + ", lohkoNro=" + lohkoNro + ", kilpailu=" + kilpailu + "]";
	}

}
