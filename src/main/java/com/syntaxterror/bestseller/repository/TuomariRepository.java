package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Tuomari;

@Repository
public interface TuomariRepository extends CrudRepository<Tuomari, Long> {
	Tuomari findByTuomariId(Long tuomariId);

	List<Tuomari> findByKilpailuId(Long kilpailuId);

	Iterable<Tuomari> findByLohkoNro(String lohkoNro);

	List<Tuomari> findByKilpailuIdAndLohkoNro(Long kilpailuId, String lohkoNro);

	List<Tuomari> findByKilpailuIdAndFinaaliin(Long kilpailuId, Long finaaliin);
}