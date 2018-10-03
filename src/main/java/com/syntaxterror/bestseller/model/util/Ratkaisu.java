package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Ratkaisu {

    @Column
    private int haasteYhteenvetoPist;

    @Column
    private int ratkaisunEsittaminenPist;

    @Column
    private int hyotyjenEsilletuontiPist;

    @Column
    private String ratkaisuVapaaPalaute;

    public int getHaasteYhteenvetoPist() {
        return haasteYhteenvetoPist;
    }

    public void setHaasteYhteenvetoPist(int haasteYhteenvetoPist) {
        this.haasteYhteenvetoPist = haasteYhteenvetoPist;
    }

    public int getRatkaisunEsittaminenPist() {
        return ratkaisunEsittaminenPist;
    }

    public void setRatkaisunEsittaminenPist(int ratkaisunEsittaminenPist) {
        this.ratkaisunEsittaminenPist = ratkaisunEsittaminenPist;
    }

    public int getHyotyjenEsilletuontiPist() {
        return hyotyjenEsilletuontiPist;
    }

    public void setHyotyjenEsilletuontiPist(int hyotyjenEsilletuontiPist) {
        this.hyotyjenEsilletuontiPist = hyotyjenEsilletuontiPist;
    }

    public String getRatkaisuVapaaPalaute() {
        return ratkaisuVapaaPalaute;
    }

    public void setRatkaisuVapaaPalaute(String ratkaisuVapaaPalaute) {
        this.ratkaisuVapaaPalaute = ratkaisuVapaaPalaute;
    }

    public int getKokonaistulos(){
        return haasteYhteenvetoPist + ratkaisunEsittaminenPist + hyotyjenEsilletuontiPist;
    }
}

