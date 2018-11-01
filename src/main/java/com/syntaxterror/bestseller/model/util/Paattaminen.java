package com.syntaxterror.bestseller.model.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Paattaminen {

    @Column
    private String sitoutumisenEhdotusPist;

    @Column
    private String sitoutumisenSaaminenPist;

    @Column
    private String paattaminenVapaaPalaute;

    public String getSitoutumisenEhdotusPist() {
        return sitoutumisenEhdotusPist;
    }

    public void setSitoutumisenEhdotusPist(String sitoutumisenEhdotusPist) {
        this.sitoutumisenEhdotusPist = sitoutumisenEhdotusPist;
    }

    public String getSitoutumisenSaaminenPist() {
        return sitoutumisenSaaminenPist;
    }

    public void setSitoutumisenSaaminenPist(String sitoutumisenSaaminenPist) {
        this.sitoutumisenSaaminenPist = sitoutumisenSaaminenPist;
    }

    public String getPaattaminenVapaaPalaute() {
        return paattaminenVapaaPalaute;
    }

    public void setPaattaminenVapaaPalaute(String paattaminenVapaaPalaute) {
        this.paattaminenVapaaPalaute = paattaminenVapaaPalaute;
    }

    public int getKokonaistulos(){
        return Integer.parseInt(sitoutumisenEhdotusPist) + Integer.parseInt(sitoutumisenSaaminenPist);
    }
}

