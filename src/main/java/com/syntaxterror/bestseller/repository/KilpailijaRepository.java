package com.syntaxterror.bestseller.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Lohko;

@Repository
@Transactional
public interface KilpailijaRepository extends CrudRepository<Kilpailija, Long> {
	Kilpailija findByKilpailijaId(Long kilpailijaId);
	Iterable<Kilpailija> findByKilpailuId(Long kilpailuId);
	Iterable<Kilpailija> findByKilpailuIdAndLohko(Long kilpailuId, Lohko lohko);
	Iterable<Kilpailija> deleteByKilpailuId(Long kilpailuId);
	Iterable<Kilpailija> findByLohko(Lohko lohko);
}