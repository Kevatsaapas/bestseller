package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Kilpailu;

@Repository
public interface KilpailuRepository extends CrudRepository<Kilpailu, Long> {
	Kilpailu findByKilpailuId(Long kilpailuId);
	Kilpailu findByNimi(String nimi);
	List<Kilpailu> findByAuki(Long auki);
}
