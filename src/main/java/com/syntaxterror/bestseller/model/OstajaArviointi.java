package com.syntaxterror.bestseller.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OstajaArviointi {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ostaja_arviointi_id")
	private Long ostajaArviointiId;

	@Temporal(TemporalType.DATE)
	@Column(name = "ostaja_arviointi_pvm")
	private Date arviointiPvm;

	@ManyToOne
	@JoinColumn(name = "kilpailija_id")
	private Kilpailija kilpailija;

	@ManyToOne
	@JoinColumn(name = "ostaja_id")
	private Ostaja ostaja;

	@OneToOne
	@JoinColumn(name = "lohko_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Lohko lohko;

	@Column
	private Long kilpailuId;

	@Column(name="arvosana")
	private Long ostajanArvio;
	
	@Column(name="vapaa_palaute")
	private String ostajanVapaaPalaute;

	public OstajaArviointi(Date arviointiPvm, Kilpailija kilpailija, Ostaja ostaja, Lohko lohko, Long kilpailuId,
			Long ostajanArvio, String ostajanVapaaPalaute) {
		this.arviointiPvm = arviointiPvm;
		this.kilpailija = kilpailija;
		this.ostaja = ostaja;
		this.lohko = lohko;
		this.kilpailuId = kilpailuId;
		this.ostajanArvio = ostajanArvio;
		this.ostajanVapaaPalaute = ostajanVapaaPalaute;
	}

	public OstajaArviointi() {

	}

	public Long getOstajaArviointiId() {
		return ostajaArviointiId;
	}

	public void setOstajaArviointiId(Long ostajaArviointiId) {
		this.ostajaArviointiId = ostajaArviointiId;
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

	public Ostaja getOstaja() {
		return ostaja;
	}

	public void setOstaja(Ostaja ostaja) {
		this.ostaja = ostaja;
	}

	public Lohko getLohko() {
		return lohko;
	}

	public void setLohko(Lohko lohko) {
		this.lohko = lohko;
	}

	public Long getKilpailuId() {
		return kilpailuId;
	}

	public void setKilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
	}

	public Long getOstajanArvio() {
		return ostajanArvio;
	}

	public void setOstajanArvio(Long ostajanArvio) {
		this.ostajanArvio = ostajanArvio;
	}
	
	

	public String getOstajanVapaaPalaute() {
		return ostajanVapaaPalaute;
	}

	public void setOstajanVapaaPalaute(String ostajanVapaaPalaute) {
		this.ostajanVapaaPalaute = ostajanVapaaPalaute;
	}

	@Override
	public String toString() {
		return "OstajaArviointi [ostajaArviointiId=" + ostajaArviointiId + ", arviointiPvm=" + arviointiPvm
				+ ", kilpailija=" + kilpailija + ", ostaja=" + ostaja + ", lohko=" + lohko + ", kilpailuId="
				+ kilpailuId + ", ostajanArvio=" + ostajanArvio + ", ostajanVapaaPalaute=" + ostajanVapaaPalaute + "]";
	}

	

}
