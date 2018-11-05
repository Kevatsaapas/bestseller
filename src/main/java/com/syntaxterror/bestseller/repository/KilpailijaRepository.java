package com.syntaxterror.bestseller.repository;

import java.util.List;

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
	List<Kilpailija> findByKilpailuIdAndLohko(Long kilpailuId, Lohko lohko);
	Iterable<Kilpailija>deleteByKilpailuId(Long kilpailuId);
	List<Kilpailija> findByLohko(Lohko lohko);
}