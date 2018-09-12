package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Kilpailu;

@Repository
public interface KilpailuRepository extends CrudRepository<Kilpailu, Long> {
	List<Kilpailu> findByKilpailuId(Long kilpailuId);
}