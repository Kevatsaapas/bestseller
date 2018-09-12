package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.OsaAlue;

@Repository
public interface OsaAlueRepository extends CrudRepository<OsaAlue, Long> {
	List<OsaAlue> findByOsaAlueId(Long osaAlueId);
}