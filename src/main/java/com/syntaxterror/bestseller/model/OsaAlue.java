package com.syntaxterror.bestseller.model;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
public class OsaAlue {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "osa_alue_id")
	private Long osaAlueId;

	@Column(name = "osa_alue_nimi")
	private String nimi;

	@Column(name="tavoite")
	private String tavoite;

	@Column(name = "osa_alue_painoarvo")
	private Long painoarvo;
	
	@Column(name = "kilpailu_id")
	private Long kilpailuId;

	@Min(0)
    @Max(7)
    @Column(name = "osa_alue_piste")
	private Integer osaAluePiste;


	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "arviointi_osa_alue",
            joinColumns = {@JoinColumn(name = "osa_alue_id")},
            inverseJoinColumns = { @JoinColumn(name = "arviointi_id")}
        )
	private List<Arviointi> arvioinnit;

	public OsaAlue() {
		super();
		this.nimi=null;
		this.painoarvo=null;
		this.tavoite=null;
		this.kilpailuId=null;
	}

	public OsaAlue(String nimi, String tavoite, Long painoarvo, Long kilpailuId) {
		super();
		this.nimi = nimi;
		this.tavoite=tavoite;
		this.painoarvo = painoarvo;
		this.kilpailuId=kilpailuId;
	}

	
	
	public String getTavoite() {
		return tavoite;
	}

	public void setTavoite(String tavoite) {
		this.tavoite = tavoite;
	}

	public Long getKilpailuId() {
		return kilpailuId;
	}

	public void setKilpailuId(Long kilpailuId) {
		this.kilpailuId = kilpailuId;
	}

	public Long getOsaAlueId() {
		return osaAlueId;
	}

	public void setOsaAlueId(Long osaAlueId) {
		this.osaAlueId = osaAlueId;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public Long getPainoarvo() {
		return painoarvo;
	}

	public void setPainoarvo(Long painoarvo) {
		this.painoarvo = painoarvo;
	}

    public Integer getOsaAluePiste() {
        return osaAluePiste;
    }

    public void setOsaAluePiste(Integer osaAluePiste) {
        this.osaAluePiste = osaAluePiste;
    }

    public List<Arviointi> getArvioinnit() {
        return arvioinnit;
    }

    public void setArvioinnit(List<Arviointi> arvioinnit) {
        this.arvioinnit = arvioinnit;
    }

	@Override
	public String toString() {
		return "OsaAlue [osaAlueId=" + osaAlueId + ", nimi=" + nimi + ", tavoite=" + tavoite + ", painoarvo="
				+ painoarvo + ", kilpailuId=" + kilpailuId + ", osaAluePiste=" + osaAluePiste + ", arvioinnit="
				+ arvioinnit + "]";
	}


    
	
}