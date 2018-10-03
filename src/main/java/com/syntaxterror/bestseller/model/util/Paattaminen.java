package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Paattaminen {

    @Column
    private int sitoutumisenEhdotusPist;

    @Column
    private int sitoutumisenSaaminenPist;

    @Column
    private String paattaminenVapaaPalaute;

    public int getSitoutumisenEhdotusPist() {
        return sitoutumisenEhdotusPist;
    }

    public void setSitoutumisenEhdotusPist(int sitoutumisenEhdotusPist) {
        this.sitoutumisenEhdotusPist = sitoutumisenEhdotusPist;
    }

    public int getSitoutumisenSaaminenPist() {
        return sitoutumisenSaaminenPist;
    }

    public void setSitoutumisenSaaminenPist(int sitoutumisenSaaminenPist) {
        this.sitoutumisenSaaminenPist = sitoutumisenSaaminenPist;
    }

    public String getPaattaminenVapaaPalaute() {
        return paattaminenVapaaPalaute;
    }

    public void setPaattaminenVapaaPalaute(String paattaminenVapaaPalaute) {
        this.paattaminenVapaaPalaute = paattaminenVapaaPalaute;
    }

    public int getKokonaistulos(){
        return sitoutumisenEhdotusPist + sitoutumisenSaaminenPist;
    }
}

