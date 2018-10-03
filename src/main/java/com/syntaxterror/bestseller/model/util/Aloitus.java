package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Aloitus {

    @Column
    private int selkeaEsittaytyminenPist;

    @Column
    private int tapaamisenLahtotilannePist;

    @Column
    private int tapaamisenAjankayttoPist;

    @Column
    private String aloitusVapaaPalaute;

    public int getSelkeaEsittaytyminenPist() {
        return selkeaEsittaytyminenPist;
    }

    public void setSelkeaEsittaytyminenPist(int selkeaEsittaytyminenPist) {
        this.selkeaEsittaytyminenPist = selkeaEsittaytyminenPist;
    }

    public int getTapaamisenLahtotilannePist() {
        return tapaamisenLahtotilannePist;
    }

    public void setTapaamisenLahtotilannePist(int tapaamisenLahtotilannePist) {
        this.tapaamisenLahtotilannePist = tapaamisenLahtotilannePist;
    }

    public int getTapaamisenAjankayttoPist() {
        return tapaamisenAjankayttoPist;
    }

    public void setTapaamisenAjankayttoPist(int tapaamisenAjankayttoPist) {
        this.tapaamisenAjankayttoPist = tapaamisenAjankayttoPist;
    }

    public String getAloitusVapaaPalaute() {
        return aloitusVapaaPalaute;
    }

    public void setAloitusVapaaPalaute(String aloitusVapaaPalaute) {
        this.aloitusVapaaPalaute = aloitusVapaaPalaute;
    }

    public int getKokonaistulos(){

        return selkeaEsittaytyminenPist + tapaamisenAjankayttoPist + tapaamisenLahtotilannePist;
    }
}
