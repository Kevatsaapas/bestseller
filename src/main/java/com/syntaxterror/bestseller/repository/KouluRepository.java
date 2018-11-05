package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Koulu;

@Repository
public interface KouluRepository extends CrudRepository<Koulu, Long> {
	Koulu findByKouluId(Long kouluId);
	Iterable<Koulu> findByKilpailuId(Kilpailu kilpailu);
	List<Koulu> findByKilpailuId(Long kilpailuId);
}