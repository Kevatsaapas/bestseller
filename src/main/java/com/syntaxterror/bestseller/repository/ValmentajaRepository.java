package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Valmentaja;

public interface ValmentajaRepository extends CrudRepository<Valmentaja, Long>{
	Valmentaja findByValmentajaId(Long ValmentajaId);
	List<Valmentaja> findByKilpailuId(Long kilpailuId);
	Iterable<Valmentaja> findByKouluId(Long kouluId);
	

}
