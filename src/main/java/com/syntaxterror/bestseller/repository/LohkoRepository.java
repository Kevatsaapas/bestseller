package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Lohko;

@Repository
public interface LohkoRepository extends CrudRepository<Lohko, Long> {
	List<Lohko> findByLohkoId(Long lohkoId);
}