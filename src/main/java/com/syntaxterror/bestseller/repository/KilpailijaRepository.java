package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Kilpailija;

@Repository
public interface KilpailijaRepository extends CrudRepository<Kilpailija, Long> {
	Kilpailija findByKilpailijaId(Long kilpailijaId);
}