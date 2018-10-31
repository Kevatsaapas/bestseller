package com.syntaxterror.bestseller.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Tuomari;

@Repository
public interface TuomariRepository extends CrudRepository<Tuomari, Long> {
	Tuomari findByTuomariId(Long tuomariId);
	Iterable<Tuomari> findByKilpailuId(Long kilpailuId);
	Iterable<Tuomari> findByLohkoNro(Long lohkoNro);
	Iterable<Tuomari> findByKilpailuIdAndLohkoNro(Long kilpailuId, String lohkoNro);
}