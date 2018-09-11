package com.syntaxterror.bestseller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.syntaxterror.bestseller.model.Kilpailija;

@Repository
@Transactional
public interface KilpailijaRepository extends CrudRepository<Kilpailija, Long> {
	Kilpailija findByKilpailijaId(Long kilpailijaId);
	Iterable<Kilpailija> findByKilpailuId(Long kilpailuId);
	Iterable<Kilpailija>deleteByKilpailuId(Long kilpailuId);
}