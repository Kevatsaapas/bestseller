package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Tarvekartoitus {

    @Column
    private String perustietojenSelvitysPist;

    @Column
    private String asiakkaanNykytilaPist;

    @Column
    private String tarpeenKehittaminenPist;

    @Column
    private String paatoksentekoprosessiPist;

    @Column
    private String tarvekartoitusVapaaPalaute;

    public String getPerustietojenSelvitysPist() {
        return perustietojenSelvitysPist;
    }

    public void setPerustietojenSelvitysPist(String perustietojenSelvitysPist) {
        this.perustietojenSelvitysPist = perustietojenSelvitysPist;
    }

    public String getAsiakkaanNykytilaPist() {
        return asiakkaanNykytilaPist;
    }

    public void setAsiakkaanNykytilaPist(String asiakkaanNykytilaPist) {
        this.asiakkaanNykytilaPist = asiakkaanNykytilaPist;
    }

    public String getTarpeenKehittaminenPist() {
        return tarpeenKehittaminenPist;
    }

    public void setTarpeenKehittaminenPist(String tarpeenKehittaminenPist) {
        this.tarpeenKehittaminenPist = tarpeenKehittaminenPist;
    }

    public String getPaatoksentekoprosessiPist() {
        return paatoksentekoprosessiPist;
    }

    public void setPaatoksentekoprosessiPist(String paatoksentekoprosessiPist) {
        this.paatoksentekoprosessiPist = paatoksentekoprosessiPist;
    }

    public String getTarvekartoitusVapaaPalaute() {
        return tarvekartoitusVapaaPalaute;
    }

    public void setTarvekartoitusVapaaPalaute(String tarvekartoitusVapaaPalaute) {
        this.tarvekartoitusVapaaPalaute = tarvekartoitusVapaaPalaute;
    }

    public int getKokonaistulos(){
        return Integer.parseInt(perustietojenSelvitysPist) + Integer.parseInt(asiakkaanNykytilaPist) + Integer.parseInt(tarpeenKehittaminenPist) + Integer.parseInt(paatoksentekoprosessiPist);
    }
}
