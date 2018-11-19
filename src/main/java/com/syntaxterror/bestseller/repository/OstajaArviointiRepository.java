package com.syntaxterror.bestseller.repository;


import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.Ostaja;
import com.syntaxterror.bestseller.model.OstajaArviointi;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OstajaArviointiRepository extends CrudRepository<OstajaArviointi, Long>{
    OstajaArviointi findByOstajaArviointiId(Long ostajaArviointiId);
    List<OstajaArviointi> findByKilpailuId(Long kilpailuId);
    List<OstajaArviointi> findByOstaja(Ostaja ostaja);
    List<OstajaArviointi> findByKilpailija(Kilpailija kilpailija);
    List<OstajaArviointi> findByKilpailijaAndLohko(Kilpailija kilpailija, Lohko lohko);
    List<OstajaArviointi> findByKilpailuIdAndLohko(Long kilpailuId, Lohko lohko);
    List<OstajaArviointi> findByOstajaAndLohko(Ostaja ostaja, Lohko lohko);
}
