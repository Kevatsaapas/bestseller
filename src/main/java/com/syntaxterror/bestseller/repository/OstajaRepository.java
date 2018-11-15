package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Ostaja;

@Repository
public interface OstajaRepository extends CrudRepository<Ostaja, Long> {
	List<Ostaja> findByOstajaId(Long ostajaId);
}