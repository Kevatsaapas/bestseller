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

	@OneToOne
	@JoinColumn(name = "koulu_Id")
	private Koulu koulu;

	@Column(name = "kilpailija_sahkoposti")
	@NotNull
	private String sposti;

	@Column(name = "kilpailu_Id")
	@NotNull
	private Long kilpailuId;

	@Column(name = "kilpailija_kokonaistulos")
	public double kokonaistulos;

	@Column(name = "kilpailija_finaalissa")
	public Long finaalissa;

	@Column(name = "finaali_kokonaistulos")
	public double finaaliKokonaistulos;
	
	@Column(name="kilpailija_hash")
	public String kilHash;

	@ManyToOne
	private Lohko lohko;

	@OneToMany
	private List<Arviointi> arvioinnit;

	public Kilpailija() {
		this.etunimi = null;
		this.sukunimi = null;
		this.kilpailijaNro = 0;
		this.koulu = null;
		this.sposti = null;
		this.lohko = null;
		this.kilpailuId = null;
		this.kokonaistulos = 0;
		this.finaalissa = null;
		this.finaaliKokonaistulos = 0;
		this.kilHash="";
	}

	public Kilpailija(String etunimi, String sukunimi, int kilpailijaNro, Koulu koulu, String sposti, Lohko lohko,
			Long kilpailuId, double kokonaistulos, String kilHash) {
		super();
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.kilpailijaNro = kilpailijaNro;
		this.koulu = koulu;
		this.sposti = sposti;
		this.lohko = lohko;
		this.kilpailuId = kilpailuId;
		this.kokonaistulos = kokonaistulos;
		this.finaalissa = new Long(0);
		this.kilHash = kilHash;
	}

	public String getSposti() {
		return sposti;
	}

	public void setSposti(String sposti) {
		this.sposti = sposti;
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

	public Koulu getKoulu() {
		return koulu;
	}

	public void setKoulu(Koulu koulu) {
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

	public double getKokonaistulos() {
		return kokonaistulos;
	}

	public void setKokonaistulos(double kokonaistulos) {
		this.kokonaistulos = kokonaistulos;
	}

	public Long getFinaalissa() {
		return finaalissa;
	}

	public void setFinaalissa(Long finaalissa) {
		this.finaalissa = finaalissa;
	}

	public double getFinaaliKokonaistulos() {
		return finaaliKokonaistulos;
	}

	public void setFinaaliKokonaistulos(double finaaliKokonaistulos) {
		this.finaaliKokonaistulos = finaaliKokonaistulos;
	}
	
	

	public String getKilHash() {
		return kilHash;
	}

	public void setKilHash(String kilHash) {
		this.kilHash = kilHash;
	}

	@Override
	public String toString() {
		return "Kilpailija [kilpailijaId=" + kilpailijaId + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi
				+ ", kilpailijaNro=" + kilpailijaNro + ", koulu=" + koulu + ", sposti=" + sposti + ", kilpailuId="
				+ kilpailuId + ", kokonaistulos=" + kokonaistulos + ", finaalissa=" + finaalissa
				+ ", finaaliKokonaistulos=" + finaaliKokonaistulos + ", kilHash=" + kilHash + ", lohko=" + lohko
				+ ", arvioinnit=" + arvioinnit + "]";
	}

	

}
