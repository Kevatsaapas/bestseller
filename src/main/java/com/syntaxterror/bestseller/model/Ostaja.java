package com.syntaxterror.bestseller.model;

import javax.persistence.*;

@Entity
public class Ostaja {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "ostaja_id")
    private Long ostajaId;

    @Column(name = "ostaja_nimi")
    private String ostajaNimi;

    @OneToOne
    private Lohko lohko;

    public Ostaja() {
    	this.ostajaNimi = null;
    }

    public Ostaja(String ostajaNimi) {
		super();
		this.ostajaNimi = ostajaNimi;
	}

	public Long getOstajaId() {
        return ostajaId;
    }

    public void setOstajaId(Long ostajaId) {
        this.ostajaId = ostajaId;
    }

    public String getOstajaNimi() {
        return ostajaNimi;
    }

    public void setOstajaNimi(String ostajaNimi) {
        this.ostajaNimi = ostajaNimi;
    }

    public Lohko getLohko() {
        return lohko;
    }

    public void setLohko(Lohko lohko) {
        this.lohko = lohko;
    }

	@Override
	public String toString() {
		return "Ostaja [ostajaId=" + ostajaId + ", ostajaNimi=" + ostajaNimi + ", lohko=" + lohko + "]";
	}


}
