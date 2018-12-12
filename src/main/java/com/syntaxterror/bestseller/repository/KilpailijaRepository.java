package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Koulu;
import com.syntaxterror.bestseller.model.Lohko;

@Repository
public interface KilpailijaRepository extends CrudRepository<Kilpailija, Long> {
	Kilpailija findByKilpailijaId(Long kilpailijaId);

	List<Kilpailija> findByKilpailuId(Long kilpailuId);

	List<Kilpailija> findByKilpailuIdAndLohko(Long kilpailuId, Lohko lohko);

	List<Kilpailija> deleteByKilpailuId(Long kilpailuId);

	List<Kilpailija> findByLohko(Lohko lohko);

	List<Kilpailija> findByKilpailuIdAndFinaalissa(Long kilpailuId, Long finaalissa);

	List<Kilpailija> findByKoulu(Koulu koulu);
	
	Kilpailija findByKilHash(String kilHash);
}