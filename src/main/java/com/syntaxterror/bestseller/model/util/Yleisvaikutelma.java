package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Yleisvaikutelma {

	@Column
	private String aktiivinenKuunteluPist;

	@Column
	private String tilannetajuPist;

	@Column
	private String yleisvaikutelmaVapaaPalaute;

	@Column
	private String omaKayttaytyminenPist;

	public String getAktiivinenKuunteluPist() {
		return aktiivinenKuunteluPist;
	}

	public void setAktiivinenKuunteluPist(String aktiivinenKuunteluPist) {
		this.aktiivinenKuunteluPist = aktiivinenKuunteluPist;
	}

	public String getTilannetajuPist() {
		return tilannetajuPist;
	}

	public void setTilannetajuPist(String tilannetajuPist) {
		this.tilannetajuPist = tilannetajuPist;
	}

	public String getYleisvaikutelmaVapaaPalaute() {
		return yleisvaikutelmaVapaaPalaute;
	}

	public void setYleisvaikutelmaVapaaPalaute(String yleisvaikutelmaVapaaPalaute) {
		this.yleisvaikutelmaVapaaPalaute = yleisvaikutelmaVapaaPalaute;
	}

	public String getOmaKayttaytyminenPist() {
		return omaKayttaytyminenPist;
	}

	public void setOmaKayttaytyminenPist(String omaKayttaytyminenPist) {
		this.omaKayttaytyminenPist = omaKayttaytyminenPist;
	}

	public int getKokonaistulos() {
		return Integer.parseInt(aktiivinenKuunteluPist) + Integer.parseInt(tilannetajuPist);
	}
}
