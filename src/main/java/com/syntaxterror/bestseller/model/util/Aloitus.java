package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Aloitus {

	@Column
	private String selkeaEsittaytyminenPist;

	@Column
	private String tapaamisenLahtotilannePist;

	@Column
	private String tapaamisenAjankayttoPist;

	@Column
	private String aloitusVapaaPalaute;

	public String getSelkeaEsittaytyminenPist() {
		return selkeaEsittaytyminenPist;
	}

	public void setSelkeaEsittaytyminenPist(String selkeaEsittaytyminenPist) {
		this.selkeaEsittaytyminenPist = selkeaEsittaytyminenPist;
	}

	public String getTapaamisenLahtotilannePist() {
		return tapaamisenLahtotilannePist;
	}

	public void setTapaamisenLahtotilannePist(String tapaamisenLahtotilannePist) {
		this.tapaamisenLahtotilannePist = tapaamisenLahtotilannePist;
	}

	public String getTapaamisenAjankayttoPist() {
		return tapaamisenAjankayttoPist;
	}

	public void setTapaamisenAjankayttoPist(String tapaamisenAjankayttoPist) {
		this.tapaamisenAjankayttoPist = tapaamisenAjankayttoPist;
	}

	public String getAloitusVapaaPalaute() {
		return aloitusVapaaPalaute;
	}

	public void setAloitusVapaaPalaute(String aloitusVapaaPalaute) {
		this.aloitusVapaaPalaute = aloitusVapaaPalaute;
	}

	public int getKokonaistulos() {

		return Integer.parseInt(selkeaEsittaytyminenPist) + Integer.parseInt(tapaamisenAjankayttoPist)
				+ Integer.parseInt(tapaamisenLahtotilannePist);
	}
}
