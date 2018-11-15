package com.syntaxterror.bestseller.repository;

import java.util.List;

import com.syntaxterror.bestseller.model.Lohko;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syntaxterror.bestseller.model.Ostaja;

@Repository
public interface OstajaRepository extends CrudRepository<Ostaja, Long> {
    Ostaja findByOstajaId(Long ostajaId);
    Iterable<Ostaja> findByKilpailuId(Long kilpailuId);
    Iterable<Ostaja> findByLohkoNro(String lohkoNro);
    List<Ostaja> findByKilpailuIdAndLohkoNro(Long kilpailuId, String lohkoNro);
    List<Ostaja> findByKilpailuIdAndFinaaliin(Long kilpailuId, Long finaaliin);
}