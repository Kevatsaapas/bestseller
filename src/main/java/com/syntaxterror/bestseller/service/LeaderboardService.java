package com.syntaxterror.bestseller.service;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.util.Tarvekartoitus;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private ArviointiRepository arviointiRepository;


    public List<Arviointi> palautaParhaastaHuonoimpaan(Long kilpailuId){

        List<Arviointi> arvioinnit = arviointiRepository.findByKilpailuId(kilpailuId);

        Collections.sort(arvioinnit);
        Collections.reverse(arvioinnit);
        return arvioinnit;
    }

}
