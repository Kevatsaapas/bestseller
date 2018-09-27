package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Arviointi;

@Repository
public interface ArviointiRepository extends CrudRepository<Arviointi, Long> {
	List<Arviointi> findByArviointiId(Long arviointiId);
	Iterable<Arviointi> findByKilpailuId(Long kilpailuId);
}
