package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Kilpailu;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface KilpailuRepository extends CrudRepository<Kilpailu, Long> {
	Kilpailu findByKilpailuId(Long kilpailuId);

	Kilpailu findByNimi(String nimi);

	List<Kilpailu> findByAuki(Long auki);
}
