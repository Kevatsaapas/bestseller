package com.syntaxterror.bestseller.service;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.util.Tarvekartoitus;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private ArviointiRepository arviointiRepository;
    
    @Autowired
    private KilpailijaRepository kilpailijaRepository;
    
    @Autowired
    private KilpailuRepository kilpailuRepository;
    
    @Autowired
    private LohkoRepository lohkoRepository;


    public List<Kilpailija> palautaParhaastaHuonoimpaan(Long kilpailuId){

        List<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuId(kilpailuId);

        Collections.sort(kilpailijat, new Comparator<Kilpailija>() {
            @Override
            public int compare(Kilpailija kilpailija2, Kilpailija kilpailija1)
            {
                return  Double.compare(kilpailija1.getKokonaistulos(), kilpailija2.getKokonaistulos());
            }
        });
        return kilpailijat;
    }
    
    public List<Kilpailija> palautaFinalistitParhaastaHuonoimpaan(Long kilpailuId){

        List<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuIdAndFinaalissa(kilpailuId, new Long(1));

        Collections.sort(kilpailijat, new Comparator<Kilpailija>() {
            @Override
            public int compare(Kilpailija kilpailija2, Kilpailija kilpailija1)
            {
                return  Double.compare(kilpailija1.getFinaaliKokonaistulos(), kilpailija2.getFinaaliKokonaistulos());
            }
        });
        return kilpailijat;
    }
    
    public List<Kilpailija> palautaFinalistit(Long kilpailuId){
    	Kilpailu kilpailu=kilpailuRepository.findByKilpailuId(kilpailuId);
    	List<Lohko> lohkot = lohkoRepository.findByKilpailu(kilpailu);
    	List<Kilpailija> finalistit = new ArrayList<Kilpailija>();
    	for(Lohko lohko:lohkot) {
    		String lohkonro = lohko.getLohkoNro();
    		if(lohkonro!="finaali") {
    			List<Kilpailija> kilpailijat = kilpailijaRepository.findByKilpailuIdAndLohko(kilpailuId, lohko);
    			Collections.sort(kilpailijat, new Comparator<Kilpailija>() {
    	            @Override
    	            public int compare(Kilpailija kilpailija2, Kilpailija kilpailija1)
    	            {
    	                return  Double.compare(kilpailija1.getKokonaistulos(), kilpailija2.getKokonaistulos());
    	            }
    	        });
    			if(kilpailijat.size()>0) {
    			finalistit.add(kilpailijat.get(0));
    			}
    			kilpailijat.clear();
    		}
    	}
    	System.out.println(finalistit);
        return finalistit;
    }
    
    public void laskeLopputulokset(Long kilpailuId){
    	List<Kilpailija> kaikkikilpailijat=kilpailijaRepository.findByKilpailuId(kilpailuId);
    	
		List<Integer> aloitus1= new ArrayList<Integer>();
		double aloitusKa1=0;
		List<Integer> aloitus2= new ArrayList<Integer>();
		double aloitusKa2=0;
		List<Integer> aloitus3= new ArrayList<Integer>();
		double aloitusKa3=0;
		double aloitus=0;
		List<Integer> kysymystenKasittely1= new ArrayList<Integer>();
		double kysymystenKasittelyKa1=0;
		List<Integer> kysymystenKasittely2= new ArrayList<Integer>();
		double kysymystenKasittelyKa2=0;
		double kysymystenKasittely=0;
		List<Integer> paattaminen1= new ArrayList<Integer>();
		double paattaminenKa1=0;
		List<Integer> paattaminen2= new ArrayList<Integer>();
		double paattaminenKa2=0;
		double paattaminen = 0;
		List<Integer> ratkaisu1= new ArrayList<Integer>();
		double ratkaisuKa1=0;
		List<Integer> ratkaisu2= new ArrayList<Integer>();
		double ratkaisuKa2=0;
		List<Integer> ratkaisu3= new ArrayList<Integer>();
		double ratkaisuKa3=0;
		double ratkaisu=0;
		List<Integer> tarvekartoitus1= new ArrayList<Integer>();
		double tarvekartoitusKa1=0;
		List<Integer> tarvekartoitus2= new ArrayList<Integer>();
		double tarvekartoitusKa2=0;
		List<Integer> tarvekartoitus3= new ArrayList<Integer>();
		double tarvekartoitusKa3=0;
		List<Integer> tarvekartoitus4= new ArrayList<Integer>();
		double tarvekartoitusKa4=0;
		double tarvekartoitus = 0;
		List<Integer> yleisvaikutelma1= new ArrayList<Integer>();
		double yleisvaikutelmaKa1=0;
		List<Integer> yleisvaikutelma2= new ArrayList<Integer>();
		double yleisvaikutelmaKa2=0;
		List<Integer> yleisvaikutelma3= new ArrayList<Integer>();
		double yleisvaikutelmaKa3=0;
		double yleisvaikutelma = 0;
		double kokonaistulos=0;
		Kilpailu kilpailu=kilpailuRepository.findByKilpailuId(kilpailuId);
    	if(kilpailu.getFinaali().equals(new Long(0))) {
    	for(Kilpailija kilpailija : kaikkikilpailijat) {
    		List<Arviointi> arvioinnit = arviointiRepository.findByKilpailija(kilpailija);
    		if(arvioinnit.size()>0 ) {
    			for(Arviointi arviointi:arvioinnit) {
    				aloitus1.add(Integer.parseInt(arviointi.getAloitus().getSelkeaEsittaytyminenPist()));
    				aloitus2.add(Integer.parseInt(arviointi.getAloitus().getTapaamisenAjankayttoPist()));
    				aloitus3.add(Integer.parseInt(arviointi.getAloitus().getTapaamisenLahtotilannePist()));
    				kysymystenKasittely1.add(Integer.parseInt(arviointi.getKysymystenKasittely().getHuolenaiheidenKasittelyPist()));
    				kysymystenKasittely2.add(Integer.parseInt(arviointi.getKysymystenKasittely().getVastavaitteidenYmmartaminenPist()));
    				paattaminen1.add(Integer.parseInt(arviointi.getPaattaminen().getSitoutumisenEhdotusPist()));
    				paattaminen2.add(Integer.parseInt(arviointi.getPaattaminen().getSitoutumisenSaaminenPist()));
    				ratkaisu1.add(Integer.parseInt(arviointi.getRatkaisu().getHaasteYhteenvetoPist()));
    				ratkaisu2.add(Integer.parseInt(arviointi.getRatkaisu().getHyotyjenEsilletuontiPist()));
    				ratkaisu3.add(Integer.parseInt(arviointi.getRatkaisu().getRatkaisunEsittaminenPist()));
    				tarvekartoitus1.add(Integer.parseInt(arviointi.getTarvekartoitus().getAsiakkaanNykytilaPist()));
    				tarvekartoitus2.add(Integer.parseInt(arviointi.getTarvekartoitus().getPaatoksentekoprosessiPist()));
    				tarvekartoitus3.add(Integer.parseInt(arviointi.getTarvekartoitus().getPerustietojenSelvitysPist()));
    				tarvekartoitus4.add(Integer.parseInt(arviointi.getTarvekartoitus().getTarpeenKehittaminenPist()));
    				yleisvaikutelma1.add(Integer.parseInt(arviointi.getYleisvaikutelma().getAktiivinenKuunteluPist()));
    				yleisvaikutelma2.add(Integer.parseInt(arviointi.getYleisvaikutelma().getTilannetajuPist()));
    				yleisvaikutelma3.add(Integer.parseInt(arviointi.getYleisvaikutelma().getOmaKayttaytyminenPist()));
    			}
    			for(int luku : aloitus1) {	aloitusKa1+=luku;}
    			aloitusKa1 = aloitusKa1/aloitus1.size();
    			for(int luku : aloitus2) {	aloitusKa2+=luku;}
    			aloitusKa2 = aloitusKa2/aloitus1.size();
    			for(int luku : aloitus3) {	aloitusKa3+=luku;}
    			aloitusKa3 = aloitusKa3/aloitus1.size();
    			aloitus = (aloitusKa1+aloitusKa2+aloitusKa3)/3;
    			
    			for(int luku : kysymystenKasittely1) {	kysymystenKasittelyKa1+=luku;}
    			kysymystenKasittelyKa1 = kysymystenKasittelyKa1/aloitus1.size();
    			for(int luku : kysymystenKasittely2) {	kysymystenKasittelyKa2+=luku;}
    			kysymystenKasittelyKa2 = kysymystenKasittelyKa2/aloitus1.size();
    			kysymystenKasittely=(kysymystenKasittelyKa1+kysymystenKasittelyKa2)/2;
    			
    			for(int luku : paattaminen1) {	paattaminenKa1+=luku;}
    			paattaminenKa1 = paattaminenKa1/aloitus1.size();
    			for(int luku : paattaminen2) {	paattaminenKa2+=luku;}
    			paattaminenKa2 = paattaminenKa2/aloitus1.size();
    			paattaminen=(paattaminenKa1+paattaminenKa2)/2;
    			
    			for(int luku : ratkaisu1) {	ratkaisuKa1+=luku;}
    			ratkaisuKa1 = ratkaisuKa1/ratkaisu1.size();
    			for(int luku : ratkaisu2) {	ratkaisuKa2+=luku;}
    			ratkaisuKa2 = ratkaisuKa2/ratkaisu1.size();
    			for(int luku : ratkaisu3) {	ratkaisuKa3+=luku;}
    			ratkaisuKa3 = ratkaisuKa3/ratkaisu1.size();
    			ratkaisu = (ratkaisuKa1+ratkaisuKa2+ratkaisuKa3)/3;
    			
    			for(int luku : tarvekartoitus1) {	tarvekartoitusKa1+=luku;}
    			tarvekartoitusKa1 = tarvekartoitusKa1/tarvekartoitus1.size();
    			for(int luku : tarvekartoitus2) {	tarvekartoitusKa2+=luku;}
    			tarvekartoitusKa2 = tarvekartoitusKa2/tarvekartoitus1.size();
    			for(int luku : tarvekartoitus3) {	tarvekartoitusKa3+=luku;}
    			tarvekartoitusKa3 = tarvekartoitusKa3/tarvekartoitus1.size();
    			for(int luku : tarvekartoitus4) {	tarvekartoitusKa4+=luku;}
    			tarvekartoitusKa4 = tarvekartoitusKa4/tarvekartoitus1.size();
    			tarvekartoitus = (tarvekartoitusKa1+tarvekartoitusKa2+tarvekartoitusKa3+tarvekartoitusKa4)/4;
    			
    			for(int luku : yleisvaikutelma1) {	yleisvaikutelmaKa1+=luku;}
    			yleisvaikutelmaKa1 = yleisvaikutelmaKa1/aloitus1.size();
    			for(int luku : yleisvaikutelma2) {	yleisvaikutelmaKa2+=luku;}
    			yleisvaikutelmaKa2 = yleisvaikutelmaKa2/aloitus1.size();
    			for(int luku : yleisvaikutelma3) {	yleisvaikutelmaKa3+=luku;}
    			yleisvaikutelmaKa3 = yleisvaikutelmaKa3/aloitus1.size();
    			yleisvaikutelma=(yleisvaikutelmaKa1+yleisvaikutelmaKa2+yleisvaikutelmaKa3)/3;
    			
    			kokonaistulos = (aloitus*0.05)+(tarvekartoitus*0.3)+(ratkaisu*0.25)+(kysymystenKasittely*0.1)+(paattaminen*0.1)+(yleisvaikutelma*0.15);
    			kilpailija.setKokonaistulos(kokonaistulos);
    			kilpailijaRepository.save(kilpailija);
    			//System.out.println(kilpailija.getKilpailijaId()+"Kilpailija: "+(kilpailija.getEtunimi()+" "+kilpailija.getSukunimi())+" lohko: "+kilpailija.getLohko().getLohkoNro()+" kokonaistulos: "+kokonaistulos);
    			arvioinnit.clear();
    			
    		}
    		aloitus1.clear();aloitus2.clear();aloitus3.clear();aloitusKa1=0;aloitusKa2=0;aloitusKa3=0;aloitus=0;
			kysymystenKasittely1.clear();kysymystenKasittely2.clear();kysymystenKasittelyKa1=0;kysymystenKasittelyKa2=0;kysymystenKasittely=0; 
			paattaminen1.clear();paattaminen2.clear();paattaminenKa1=0;paattaminenKa2=0;paattaminen=0;
			ratkaisu1.clear();ratkaisu2.clear();ratkaisu3.clear();ratkaisuKa1=0;ratkaisuKa2=0;ratkaisuKa3=0;ratkaisu=0;
			tarvekartoitus1.clear();tarvekartoitus2.clear();tarvekartoitus3.clear();tarvekartoitus4.clear();tarvekartoitusKa1=0;tarvekartoitusKa2=0;tarvekartoitusKa3=0;tarvekartoitusKa4=0;tarvekartoitus=0;
			yleisvaikutelma1.clear();yleisvaikutelma2.clear();yleisvaikutelma3.clear();yleisvaikutelmaKa1=0;yleisvaikutelmaKa2=0;yleisvaikutelmaKa3=0;yleisvaikutelma=0;
			kokonaistulos=0;
    	}
    }else {
		System.out.println("finaalissa ollaan");
	}
    }
    public void laskeFinaalinLopputulokset(Long kilpailuId) {
    	
List<Kilpailija> kaikkikilpailijat=kilpailijaRepository.findByKilpailuIdAndFinaalissa(kilpailuId, new Long(1));
		Kilpailu kilpailu=kilpailuRepository.findByKilpailuId(kilpailuId);
    	Lohko lohko=lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, "finaali");
		List<Integer> aloitus1= new ArrayList<Integer>();
		double aloitusKa1=0;
		List<Integer> aloitus2= new ArrayList<Integer>();
		double aloitusKa2=0;
		List<Integer> aloitus3= new ArrayList<Integer>();
		double aloitusKa3=0;
		double aloitus=0;
		List<Integer> kysymystenKasittely1= new ArrayList<Integer>();
		double kysymystenKasittelyKa1=0;
		List<Integer> kysymystenKasittely2= new ArrayList<Integer>();
		double kysymystenKasittelyKa2=0;
		double kysymystenKasittely=0;
		List<Integer> paattaminen1= new ArrayList<Integer>();
		double paattaminenKa1=0;
		List<Integer> paattaminen2= new ArrayList<Integer>();
		double paattaminenKa2=0;
		double paattaminen = 0;
		List<Integer> ratkaisu1= new ArrayList<Integer>();
		double ratkaisuKa1=0;
		List<Integer> ratkaisu2= new ArrayList<Integer>();
		double ratkaisuKa2=0;
		List<Integer> ratkaisu3= new ArrayList<Integer>();
		double ratkaisuKa3=0;
		double ratkaisu=0;
		List<Integer> tarvekartoitus1= new ArrayList<Integer>();
		double tarvekartoitusKa1=0;
		List<Integer> tarvekartoitus2= new ArrayList<Integer>();
		double tarvekartoitusKa2=0;
		List<Integer> tarvekartoitus3= new ArrayList<Integer>();
		double tarvekartoitusKa3=0;
		List<Integer> tarvekartoitus4= new ArrayList<Integer>();
		double tarvekartoitusKa4=0;
		double tarvekartoitus = 0;
		List<Integer> yleisvaikutelma1= new ArrayList<Integer>();
		double yleisvaikutelmaKa1=0;
		List<Integer> yleisvaikutelma2= new ArrayList<Integer>();
		double yleisvaikutelmaKa2=0;
		List<Integer> yleisvaikutelma3= new ArrayList<Integer>();
		double yleisvaikutelmaKa3=0;
		double yleisvaikutelma = 0;
		double kokonaistulos=0;
    	
		for(Kilpailija kilpailija : kaikkikilpailijat) {
    		List<Arviointi> arvioinnit = arviointiRepository.findByKilpailijaAndLohko(kilpailija, lohko);
    		if(arvioinnit.size()>0 ) {
    			for(Arviointi arviointi:arvioinnit) {
    				aloitus1.add(Integer.parseInt(arviointi.getAloitus().getSelkeaEsittaytyminenPist()));
    				aloitus2.add(Integer.parseInt(arviointi.getAloitus().getTapaamisenAjankayttoPist()));
    				aloitus3.add(Integer.parseInt(arviointi.getAloitus().getTapaamisenLahtotilannePist()));
    				kysymystenKasittely1.add(Integer.parseInt(arviointi.getKysymystenKasittely().getHuolenaiheidenKasittelyPist()));
    				kysymystenKasittely2.add(Integer.parseInt(arviointi.getKysymystenKasittely().getVastavaitteidenYmmartaminenPist()));
    				paattaminen1.add(Integer.parseInt(arviointi.getPaattaminen().getSitoutumisenEhdotusPist()));
    				paattaminen2.add(Integer.parseInt(arviointi.getPaattaminen().getSitoutumisenSaaminenPist()));
    				ratkaisu1.add(Integer.parseInt(arviointi.getRatkaisu().getHaasteYhteenvetoPist()));
    				ratkaisu2.add(Integer.parseInt(arviointi.getRatkaisu().getHyotyjenEsilletuontiPist()));
    				ratkaisu3.add(Integer.parseInt(arviointi.getRatkaisu().getRatkaisunEsittaminenPist()));
    				tarvekartoitus1.add(Integer.parseInt(arviointi.getTarvekartoitus().getAsiakkaanNykytilaPist()));
    				tarvekartoitus2.add(Integer.parseInt(arviointi.getTarvekartoitus().getPaatoksentekoprosessiPist()));
    				tarvekartoitus3.add(Integer.parseInt(arviointi.getTarvekartoitus().getPerustietojenSelvitysPist()));
    				tarvekartoitus4.add(Integer.parseInt(arviointi.getTarvekartoitus().getTarpeenKehittaminenPist()));
    				yleisvaikutelma1.add(Integer.parseInt(arviointi.getYleisvaikutelma().getAktiivinenKuunteluPist()));
    				yleisvaikutelma2.add(Integer.parseInt(arviointi.getYleisvaikutelma().getTilannetajuPist()));
    				yleisvaikutelma3.add(Integer.parseInt(arviointi.getYleisvaikutelma().getOmaKayttaytyminenPist()));
    			}
    			for(int luku : aloitus1) {	aloitusKa1+=luku;}
    			aloitusKa1 = aloitusKa1/aloitus1.size();
    			for(int luku : aloitus2) {	aloitusKa2+=luku;}
    			aloitusKa2 = aloitusKa2/aloitus1.size();
    			for(int luku : aloitus3) {	aloitusKa3+=luku;}
    			aloitusKa3 = aloitusKa3/aloitus1.size();
    			aloitus = (aloitusKa1+aloitusKa2+aloitusKa3)/3;
    			
    			for(int luku : kysymystenKasittely1) {	kysymystenKasittelyKa1+=luku;}
    			kysymystenKasittelyKa1 = kysymystenKasittelyKa1/aloitus1.size();
    			for(int luku : kysymystenKasittely2) {	kysymystenKasittelyKa2+=luku;}
    			kysymystenKasittelyKa2 = kysymystenKasittelyKa2/aloitus1.size();
    			kysymystenKasittely=(kysymystenKasittelyKa1+kysymystenKasittelyKa2)/2;
    			
    			for(int luku : paattaminen1) {	paattaminenKa1+=luku;}
    			paattaminenKa1 = paattaminenKa1/aloitus1.size();
    			for(int luku : paattaminen2) {	paattaminenKa2+=luku;}
    			paattaminenKa2 = paattaminenKa2/aloitus1.size();
    			paattaminen=(paattaminenKa1+paattaminenKa2)/2;
    			
    			for(int luku : ratkaisu1) {	ratkaisuKa1+=luku;}
    			ratkaisuKa1 = ratkaisuKa1/ratkaisu1.size();
    			for(int luku : ratkaisu2) {	ratkaisuKa2+=luku;}
    			ratkaisuKa2 = ratkaisuKa2/ratkaisu1.size();
    			for(int luku : ratkaisu3) {	ratkaisuKa3+=luku;}
    			ratkaisuKa3 = ratkaisuKa3/ratkaisu1.size();
    			ratkaisu = (ratkaisuKa1+ratkaisuKa2+ratkaisuKa3)/3;
    			
    			for(int luku : tarvekartoitus1) {	tarvekartoitusKa1+=luku;}
    			tarvekartoitusKa1 = tarvekartoitusKa1/tarvekartoitus1.size();
    			for(int luku : tarvekartoitus2) {	tarvekartoitusKa2+=luku;}
    			tarvekartoitusKa2 = tarvekartoitusKa2/tarvekartoitus1.size();
    			for(int luku : tarvekartoitus3) {	tarvekartoitusKa3+=luku;}
    			tarvekartoitusKa3 = tarvekartoitusKa3/tarvekartoitus1.size();
    			for(int luku : tarvekartoitus4) {	tarvekartoitusKa4+=luku;}
    			tarvekartoitusKa4 = tarvekartoitusKa4/tarvekartoitus1.size();
    			tarvekartoitus = (tarvekartoitusKa1+tarvekartoitusKa2+tarvekartoitusKa3+tarvekartoitusKa4)/4;
    			
    			for(int luku : yleisvaikutelma1) {	yleisvaikutelmaKa1+=luku;}
    			yleisvaikutelmaKa1 = yleisvaikutelmaKa1/aloitus1.size();
    			for(int luku : yleisvaikutelma2) {	yleisvaikutelmaKa2+=luku;}
    			yleisvaikutelmaKa2 = yleisvaikutelmaKa2/aloitus1.size();
    			for(int luku : yleisvaikutelma3) {	yleisvaikutelmaKa3+=luku;}
    			yleisvaikutelmaKa3 = yleisvaikutelmaKa3/aloitus1.size();
    			yleisvaikutelma=(yleisvaikutelmaKa1+yleisvaikutelmaKa2+yleisvaikutelmaKa3)/3;
    			
    			kokonaistulos = (aloitus*0.1)+(tarvekartoitus*0.25)+(ratkaisu*0.25)+(kysymystenKasittely*0.1)+(paattaminen*0.1)+(yleisvaikutelma*0.15);
    			kilpailija.setFinaaliKokonaistulos(kokonaistulos);
    			kilpailijaRepository.save(kilpailija);
    			//System.out.println(kilpailija.getKilpailijaId()+"Kilpailija: "+(kilpailija.getEtunimi()+" "+kilpailija.getSukunimi())+" lohko: "+kilpailija.getLohko().getLohkoNro()+" kokonaistulos: "+kokonaistulos);
    			arvioinnit.clear();
    			
    		}
    		aloitus1.clear();aloitus2.clear();aloitus3.clear();aloitusKa1=0;aloitusKa2=0;aloitusKa3=0;aloitus=0;
			kysymystenKasittely1.clear();kysymystenKasittely2.clear();kysymystenKasittelyKa1=0;kysymystenKasittelyKa2=0;kysymystenKasittely=0; 
			paattaminen1.clear();paattaminen2.clear();paattaminenKa1=0;paattaminenKa2=0;paattaminen=0;
			ratkaisu1.clear();ratkaisu2.clear();ratkaisu3.clear();ratkaisuKa1=0;ratkaisuKa2=0;ratkaisuKa3=0;ratkaisu=0;
			tarvekartoitus1.clear();tarvekartoitus2.clear();tarvekartoitus3.clear();tarvekartoitus4.clear();tarvekartoitusKa1=0;tarvekartoitusKa2=0;tarvekartoitusKa3=0;tarvekartoitusKa4=0;tarvekartoitus=0;
			yleisvaikutelma1.clear();yleisvaikutelma2.clear();yleisvaikutelma3.clear();yleisvaikutelmaKa1=0;yleisvaikutelmaKa2=0;yleisvaikutelmaKa3=0;yleisvaikutelma=0;
			kokonaistulos=0;
    	}
		
    }
}
