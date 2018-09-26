package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Yleisvaikutelma {

    @Column
    private int aktiivinenKuunteluPist;

    @Column
    private int tilannetajuPist;

    @Column
    private String yleisvaikutelmaVapaaPalaute;

    public int getAktiivinenKuunteluPist(){
        return aktiivinenKuunteluPist;
    }

    public void setAktiivinenKuunteluPist(int aktiivinenKuunteluPist) {
        this.aktiivinenKuunteluPist = aktiivinenKuunteluPist;
    }

    public int getTilannetajuPist() {
        return tilannetajuPist;
    }

    public void setTilannetajuPist(int tilannetajuPist) {
        this.tilannetajuPist = tilannetajuPist;
    }

    public String getYleisvaikutelmaVapaaPalaute() {
        return yleisvaikutelmaVapaaPalaute;
    }

    public void setYleisvaikutelmaVapaaPalaute(String yleisvaikutelmaVapaaPalaute) {
        this.yleisvaikutelmaVapaaPalaute = yleisvaikutelmaVapaaPalaute;
    }
}
