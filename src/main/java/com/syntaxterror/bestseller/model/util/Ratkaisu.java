package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Ratkaisu {

	@Column
	private String haasteYhteenvetoPist;

	@Column
	private String ratkaisunEsittaminenPist;

	@Column
	private String hyotyjenEsilletuontiPist;

	@Column
	private String ratkaisuVapaaPalaute;

	public String getHaasteYhteenvetoPist() {
		return haasteYhteenvetoPist;
	}

	public void setHaasteYhteenvetoPist(String haasteYhteenvetoPist) {
		this.haasteYhteenvetoPist = haasteYhteenvetoPist;
	}

	public String getRatkaisunEsittaminenPist() {
		return ratkaisunEsittaminenPist;
	}

	public void setRatkaisunEsittaminenPist(String ratkaisunEsittaminenPist) {
		this.ratkaisunEsittaminenPist = ratkaisunEsittaminenPist;
	}

	public String getHyotyjenEsilletuontiPist() {
		return hyotyjenEsilletuontiPist;
	}

	public void setHyotyjenEsilletuontiPist(String hyotyjenEsilletuontiPist) {
		this.hyotyjenEsilletuontiPist = hyotyjenEsilletuontiPist;
	}

	public String getRatkaisuVapaaPalaute() {
		return ratkaisuVapaaPalaute;
	}

	public void setRatkaisuVapaaPalaute(String ratkaisuVapaaPalaute) {
		this.ratkaisuVapaaPalaute = ratkaisuVapaaPalaute;
	}

	public int getKokonaistulos() {
		return Integer.parseInt(haasteYhteenvetoPist) + Integer.parseInt(ratkaisunEsittaminenPist)
				+ Integer.parseInt(hyotyjenEsilletuontiPist);
	}
}
