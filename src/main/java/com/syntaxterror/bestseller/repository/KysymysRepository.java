package com.syntaxterror.bestseller.repository;

import com.syntaxterror.bestseller.model.Kysymys;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KysymysRepository extends CrudRepository<Kysymys, Long> {
    List<Kysymys> findByKysymysId(Long kysymysId);
}
