package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class KysymystenKasittely {

    @Column
    private int vastavaitteidenYmmartaminenPist;

    @Column
    private int huolenaiheidenKasittelyPist;

    @Column
    private String kasittelyVapaaPalaute;

    public int getVastavaitteidenYmmartaminenPist() {
        return vastavaitteidenYmmartaminenPist;
    }

    public void setVastavaitteidenYmmartaminenPist(int vastavaitteidenYmmartaminenPist) {
        this.vastavaitteidenYmmartaminenPist = vastavaitteidenYmmartaminenPist;
    }

    public int getHuolenaiheidenKasittelyPist() {
        return huolenaiheidenKasittelyPist;
    }

    public void setHuolenaiheidenKasittelyPist(int huolenaiheidenKasittelyPist) {
        this.huolenaiheidenKasittelyPist = huolenaiheidenKasittelyPist;
    }

    public String getKasittelyVapaaPalaute() {
        return kasittelyVapaaPalaute;
    }

    public void setKasittelyVapaaPalaute(String kasittelyVapaaPalaute) {
        this.kasittelyVapaaPalaute = kasittelyVapaaPalaute;
    }

    public int getKokonaistulos(){
        return vastavaitteidenYmmartaminenPist + huolenaiheidenKasittelyPist;
    }
}
