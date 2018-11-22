package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class KysymystenKasittely {

	@Column
	private String vastavaitteidenYmmartaminenPist;

	@Column
	private String huolenaiheidenKasittelyPist;

	@Column
	private String kasittelyVapaaPalaute;

	public String getVastavaitteidenYmmartaminenPist() {
		return vastavaitteidenYmmartaminenPist;
	}

	public void setVastavaitteidenYmmartaminenPist(String vastavaitteidenYmmartaminenPist) {
		this.vastavaitteidenYmmartaminenPist = vastavaitteidenYmmartaminenPist;
	}

	public String getHuolenaiheidenKasittelyPist() {
		return huolenaiheidenKasittelyPist;
	}

	public void setHuolenaiheidenKasittelyPist(String huolenaiheidenKasittelyPist) {
		this.huolenaiheidenKasittelyPist = huolenaiheidenKasittelyPist;
	}

	public String getKasittelyVapaaPalaute() {
		return kasittelyVapaaPalaute;
	}

	public void setKasittelyVapaaPalaute(String kasittelyVapaaPalaute) {
		this.kasittelyVapaaPalaute = kasittelyVapaaPalaute;
	}

	public int getKokonaistulos() {
		return Integer.parseInt(vastavaitteidenYmmartaminenPist) + Integer.parseInt(huolenaiheidenKasittelyPist);
	}
}
