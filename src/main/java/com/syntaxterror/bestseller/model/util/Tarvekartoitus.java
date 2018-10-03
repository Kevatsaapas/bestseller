package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Tarvekartoitus {

    @Column
    private int perustietojenSelvitysPist;

    @Column
    private int asiakkaanNykytilaPist;

    @Column
    private int tarpeenKehittaminenPist;

    @Column
    private int paatoksentekoprosessiPist;

    @Column
    private String tarvekartoitusVapaaPalaute;

    public int getPerustietojenSelvitysPist() {
        return perustietojenSelvitysPist;
    }

    public void setPerustietojenSelvitysPist(int perustietojenSelvitysPist) {
        this.perustietojenSelvitysPist = perustietojenSelvitysPist;
    }

    public int getAsiakkaanNykytilaPist() {
        return asiakkaanNykytilaPist;
    }

    public void setAsiakkaanNykytilaPist(int asiakkaanNykytilaPist) {
        this.asiakkaanNykytilaPist = asiakkaanNykytilaPist;
    }

    public int getTarpeenKehittaminenPist() {
        return tarpeenKehittaminenPist;
    }

    public void setTarpeenKehittaminenPist(int tarpeenKehittaminenPist) {
        this.tarpeenKehittaminenPist = tarpeenKehittaminenPist;
    }

    public int getPaatoksentekoprosessiPist() {
        return paatoksentekoprosessiPist;
    }

    public void setPaatoksentekoprosessiPist(int paatoksentekoprosessiPist) {
        this.paatoksentekoprosessiPist = paatoksentekoprosessiPist;
    }

    public String getTarvekartoitusVapaaPalaute() {
        return tarvekartoitusVapaaPalaute;
    }

    public void setTarvekartoitusVapaaPalaute(String tarvekartoitusVapaaPalaute) {
        this.tarvekartoitusVapaaPalaute = tarvekartoitusVapaaPalaute;
    }

    public int getKokonaistulos(){
        return perustietojenSelvitysPist + asiakkaanNykytilaPist + tarpeenKehittaminenPist + paatoksentekoprosessiPist;
    }
}
