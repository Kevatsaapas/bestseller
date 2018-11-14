package com.syntaxterror.bestseller.service;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.model.util.Aloitus;
import com.syntaxterror.bestseller.model.util.KysymystenKasittely;
import com.syntaxterror.bestseller.model.util.Paattaminen;
import com.syntaxterror.bestseller.model.util.Ratkaisu;
import com.syntaxterror.bestseller.model.util.Tarvekartoitus;
import com.syntaxterror.bestseller.model.util.Yleisvaikutelma;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ArviointiService {

    @Autowired
    private TuomariRepository tuomariRepository;
    
    @Autowired
    private KilpailijaRepository kilpailijaRepository;
    
    @Autowired
    private ArviointiRepository arviointiRepository;
    
    @Autowired
    private LohkoRepository lohkoRepository;
    
    @Autowired
    private KilpailuRepository kilpailuRepository;
    
    @Autowired
    public JdbcTemplate jdbctemplate;
    
    public void nuke(Long kilpailuId) {
    	String sql = "DELETE FROM kilpailija WHERE kilpailu_id=" + kilpailuId.toString() + ";";
		jdbctemplate.execute(sql);
		sql = "DELETE FROM tuomari WHERE kilpailu_id=" + kilpailuId.toString() + ";";
		jdbctemplate.execute(sql);
		sql = "DELETE FROM lohko WHERE kilpailu_id=" + kilpailuId.toString() + ";";
		jdbctemplate.execute(sql);
		sql = "DELETE FROM koulu WHERE kilpailu_id=" + kilpailuId.toString() + ";";
		jdbctemplate.execute(sql);
		sql = "DELETE FROM ostaja WHERE kilpailu_id=" + kilpailuId.toString() + ";";
		jdbctemplate.execute(sql);
		sql = "DELETE FROM arviointi WHERE kilpailu_id=" + kilpailuId.toString() + ";";
		jdbctemplate.execute(sql);
		sql = "DELETE FROM valmentaja WHERE kilpailu_id=" + kilpailuId.toString() + ";";
		jdbctemplate.execute(sql);
        kilpailuRepository.deleteById(kilpailuId);
    }
    
    public int laskeArviointienSumma(Long kilpailuId) {
    	int maara = 0;
    	Kilpailu kilpailu=kilpailuRepository.findByKilpailuId(kilpailuId);
    	List<Lohko> lohkot = lohkoRepository.findByKilpailu(kilpailu);
    	for(Lohko lohko: lohkot) {
    		String lohkonro = lohko.getLohkoNro();
    		if(lohkonro!="finaali") {
    			List<Tuomari> tuomarit = tuomariRepository.findByKilpailuIdAndLohkoNro(kilpailuId, lohkonro);
    			List<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuIdAndLohko(kilpailuId, lohko);
    			maara+= tuomarit.size()*kilpailijat.size();
    		}
    	}
    	return maara;
    }
    
    public int laskeFinaaliArviointienSumma(Long kilpailuId) {
    			List<Tuomari> tuomarit = tuomariRepository.findByKilpailuIdAndFinaaliin(kilpailuId, new Long(1));
    			List<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuIdAndFinaalissa(kilpailuId, new Long(1));
    			int maara= tuomarit.size()*kilpailijat.size();
    	return maara;
    }

    public void arvioi(Long kilpailuId){
    	Kilpailu kilpailu=kilpailuRepository.findByKilpailuId(kilpailuId);
    	List<Long> arvot = new ArrayList<Long>();
    	for(int i =0; i<4; i++) {
    		String lohkonro = Integer.toString(i+1);
    		Lohko lohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, lohkonro);
    		List<Tuomari> tuomarit = tuomariRepository.findByKilpailuIdAndLohkoNro(kilpailuId, lohkonro);
    		List<Kilpailija> kilpailijat = kilpailijaRepository.findByLohko(lohko);
    		for(Tuomari tuomari:tuomarit) {
    			for(Kilpailija kilpailija:kilpailijat) {
    				for(int u=0; u<16; u++) {
    	            	int randomNum = ThreadLocalRandom.current().nextInt(0, 6 + 1);
    	            	Long arvo = new Long(randomNum+1);
    	            	arvot.add(arvo);
    	            }
    				Arviointi arviointi= new Arviointi();
    				Date arviointipvm = new Date();
    				arviointi.setKilpailija(kilpailija);
    		        arviointi.setArviointiPvm(arviointipvm);
    		        arviointi.setKilpailuId(kilpailuId);
    		        arviointi.setLohko(lohko);
    		        arviointi.setTuomari(tuomari);
    		        Aloitus aloitus=new Aloitus();
    		        arviointi.setAloitus(aloitus);
    		        KysymystenKasittely kysymystenKasittely = new KysymystenKasittely();
    		        arviointi.setKysymystenKasittely(kysymystenKasittely);
    		        Paattaminen paattaminen = new Paattaminen();
    		        arviointi.setPaattaminen(paattaminen);
    		        Ratkaisu ratkaisu = new Ratkaisu();
    		        arviointi.setRatkaisu(ratkaisu);
    		        Tarvekartoitus tarvekartoitus = new Tarvekartoitus();
    		        arviointi.setTarvekartoitus(tarvekartoitus);
    		        Yleisvaikutelma yleisvaikutelma = new Yleisvaikutelma();
    		        arviointi.setYleisvaikutelma(yleisvaikutelma);
    		        
    		        arviointi.getAloitus().setSelkeaEsittaytyminenPist(arvot.get(0).toString());
    		        arviointi.getAloitus().setTapaamisenAjankayttoPist(arvot.get(1).toString());
    		        arviointi.getAloitus().setTapaamisenLahtotilannePist(arvot.get(2).toString());
    		        arviointi.getKysymystenKasittely().setHuolenaiheidenKasittelyPist(arvot.get(3).toString());
    		        arviointi.getKysymystenKasittely().setVastavaitteidenYmmartaminenPist(arvot.get(4).toString());
    		        arviointi.getPaattaminen().setSitoutumisenEhdotusPist(arvot.get(5).toString());
    		        arviointi.getPaattaminen().setSitoutumisenSaaminenPist(arvot.get(6).toString());
    		        arviointi.getRatkaisu().setHaasteYhteenvetoPist(arvot.get(7).toString());
    		        arviointi.getRatkaisu().setHyotyjenEsilletuontiPist(arvot.get(8).toString());
    		        arviointi.getRatkaisu().setRatkaisunEsittaminenPist(arvot.get(9).toString());
    		        arviointi.getTarvekartoitus().setAsiakkaanNykytilaPist(arvot.get(10).toString());
    		        arviointi.getTarvekartoitus().setPaatoksentekoprosessiPist(arvot.get(11).toString());
    		        arviointi.getTarvekartoitus().setPerustietojenSelvitysPist(arvot.get(12).toString());
    		        arviointi.getTarvekartoitus().setTarpeenKehittaminenPist(arvot.get(12).toString());
    		        arviointi.getYleisvaikutelma().setAktiivinenKuunteluPist(arvot.get(13).toString());
    		        arviointi.getYleisvaikutelma().setTilannetajuPist(arvot.get(13).toString());
    		        arviointi.getYleisvaikutelma().setOmaKayttaytyminenPist(arvot.get(13).toString());
    		        double painotettuAloitus = arviointi.getAloitus().getKokonaistulos() * 0.1;
    		        double painotettuKasittely = arviointi.getKysymystenKasittely().getKokonaistulos() * 0.3;
    		        double painotettuPaattaminen = arviointi.getPaattaminen().getKokonaistulos() * 0.25;
    		        double painotettuRatkaisu = arviointi.getRatkaisu().getKokonaistulos() * 0.1;
    		        double painotettuTarvekartoitus = arviointi.getTarvekartoitus().getKokonaistulos() * 0.1;
    		        double painotettuYleisvaikutelma = arviointi.getYleisvaikutelma().getKokonaistulos() * 0.1;
    		        
    		        double kokonaistulos = painotettuAloitus + painotettuKasittely + painotettuPaattaminen + painotettuRatkaisu + painotettuTarvekartoitus + painotettuYleisvaikutelma;
    		        arviointi.setKokonaistulos(kokonaistulos);
    		        arviointiRepository.save(arviointi);
    		        arvot.clear();
    			}
    		}
    		
    	}
    		
    	}
    }