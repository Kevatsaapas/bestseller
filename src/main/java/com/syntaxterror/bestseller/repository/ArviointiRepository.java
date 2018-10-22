package com.syntaxterror.bestseller.repository;

import java.util.List;

import com.syntaxterror.bestseller.model.Lohko;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Arviointi;

@Repository
public interface ArviointiRepository extends CrudRepository<Arviointi, Long> {
	Arviointi findByArviointiId(Long arviointiId);
	List<Arviointi> findByKilpailuId(Long kilpailuId);
	List<Arviointi> findByLohko(Lohko lohko);
	Arviointi deleteByArviointiId(Long arviointiId);

}