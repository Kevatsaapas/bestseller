package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Tuomari;

@Repository
public interface ArviointiRepository extends CrudRepository<Arviointi, Long> {
	Arviointi findByArviointiId(Long arviointiId);
	List<Arviointi> findByKilpailuId(Long kilpailuId);
	Arviointi deleteByArviointiId(Long arviointiId);
	List<Arviointi> findByTuomari(Tuomari tuomari);
	List<Arviointi> findByKilpailija(Kilpailija kilpailija);
}