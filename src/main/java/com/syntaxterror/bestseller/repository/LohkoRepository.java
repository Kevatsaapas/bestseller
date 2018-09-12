package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;

@Transactional
@Repository
public interface LohkoRepository extends CrudRepository<Lohko, Long> {
	List<Lohko> findByLohkoId(Long lohkoId);
	Iterable<Lohko> findByKilpailu(Kilpailu kilpailu);
	Iterable<Lohko>deleteByKilpailu(Kilpailu kilpailu);
}