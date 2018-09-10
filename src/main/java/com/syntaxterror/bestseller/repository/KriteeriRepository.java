package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Kriteeri;

@Repository
public interface KriteeriRepository extends CrudRepository<Kriteeri, Long> {
	List<Kriteeri> findByKriteeriId(Long kriteeriId);
}