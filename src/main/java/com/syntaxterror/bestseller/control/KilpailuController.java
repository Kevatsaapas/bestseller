package com.syntaxterror.bestseller.control;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class KilpailuController {

    @Autowired
    public KilpailuRepository kilpailuRepository;

    @Autowired
    public LohkoRepository lohkoRepository;

    @Autowired
    public KilpailijaRepository kilpailijaRepository;

    @RequestMapping("/luokilpailu")
    public String luoKilpailu(Model model, Kilpailu kilpailu) {
        model.addAttribute(kilpailu);
        return "luonti";
    }
    
    @RequestMapping("/editkilpailu/{kilpailuId}")
    public String editKilpailu(Model model, @PathVariable Long kilpailuId) {
        model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
        return "editkilpailu";
    }
    
    @RequestMapping("/updatekilpailu")
    public String updateKilpailu(Model model, Kilpailu kilpailu) {
    	Date pvm = new Date();
        kilpailu.setPvm(pvm);
        kilpailuRepository.save(kilpailu);
        model.addAttribute("kilpailut", kilpailuRepository.findAll());
        return "testaus";
    }
    
    private void luoLohkot(Long kilpailuId) {
        Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(kilpailuId);
        for (int i = 1; i < 5; i++) {
            Lohko loh = new Lohko((Integer.toString(i)), kilpailu, null);
            lohkoRepository.save(loh);
            System.out.println(loh);
        }

        Lohko loh = new Lohko("finaali", kilpailu, null);
        lohkoRepository.save(loh);
        System.out.println(loh);
        kilpailuRepository.save(kilpailu);
    }

    @RequestMapping(value = "/tallennakilpailu", method = RequestMethod.POST)
    @Transactional
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy@HH:mm:ss.SSSZ")
    public String tallennaKilpailu(Kilpailu kilpailu) throws ParseException {
    	Long paiva=kilpailu.getPaiva();
        Long kuukausi=kilpailu.getKuukausi();
        Long vuosi=kilpailu.getVuosi();
        
        String pai=null;
        String kk=null;
        String vuo =null;
        
        if (paiva <10) {
        	pai = "0"+paiva.toString();
        }
        else {
        	pai=paiva.toString();
        }
        if (kuukausi <10) {
        	kk="0"+kuukausi.toString();
        }
        else {
        	kk=kuukausi.toString();
        }
        
        if (vuosi <10) {
        	vuo="0"+vuosi.toString();
        }
        else {
        	vuo=vuosi.toString();
        }
        
    	String pvm = pai+"/"+kk+"/"+vuo;
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		kilpailu.setPvm(dateFormat.parse(pvm));
    	
		//Date menee väärinpäin mut toimii muuten
        System.out.println(kilpailu.getPvm());
       
        kilpailuRepository.save(kilpailu);
        luoLohkot(kilpailu.getKilpailuId());
        System.out.println(kilpailu);
        return "redirect:/testaus";
    }

    @RequestMapping(value = "/poistakilpailu/{kilpailuId}", method = RequestMethod.GET)
    @Transactional
    public String poistaKilpailu(@PathVariable Long kilpailuId) {
        kilpailijaRepository.deleteByKilpailuId(kilpailuId);
        Kilpailu kilpailu= kilpailuRepository.findByKilpailuId(kilpailuId);
        lohkoRepository.deleteByKilpailu(kilpailu);
        kilpailuRepository.deleteById(kilpailuId);
        return "redirect:/testaus";
    }

}
