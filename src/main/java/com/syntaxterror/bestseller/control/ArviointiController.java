package com.syntaxterror.bestseller.control;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;

@Controller
@RequestMapping(value = "/arviointi")
@Transactional
public class ArviointiController {

	@Autowired
	private ArviointiRepository arviointiRepository;

	@Autowired
	private TuomariRepository tuomariRepository;

	@Autowired
	private LohkoRepository lohkoRepository;

	

	@RequestMapping(value = "/tallenna/{kilpailuId}/{lohkoId}/{tuomariId}", method = RequestMethod.POST)
	public String tallennaArviointi(Arviointi arviointi, @PathVariable Long kilpailuId, @PathVariable Long lohkoId, @PathVariable Long tuomariId) {

		Date arviointipvm = new Date();
		arviointi.setArviointiPvm(arviointipvm);
		arviointi.setKilpailuId(kilpailuId);
		Tuomari tuo = tuomariRepository.findByTuomariId(tuomariId);
		arviointi.setTuomari(tuo);
		Lohko loh = lohkoRepository.findByLohkoId(lohkoId);
		arviointi.setLohko(loh);

		double painotettuAloitus = arviointi.getAloitus().getKokonaistulos() * 0.1;
		double painotettuKasittely = arviointi.getKysymystenKasittely().getKokonaistulos() * 0.3;
		double painotettuPaattaminen = arviointi.getPaattaminen().getKokonaistulos() * 0.25;
		double painotettuRatkaisu = arviointi.getRatkaisu().getKokonaistulos() * 0.1;
		double painotettuTarvekartoitus = arviointi.getTarvekartoitus().getKokonaistulos() * 0.1;
		double painotettuYleisvaikutelma = arviointi.getYleisvaikutelma().getKokonaistulos() * 0.1;

		double kokonaistulos = painotettuAloitus + painotettuKasittely + painotettuPaattaminen + painotettuRatkaisu
				+ painotettuTarvekartoitus + painotettuYleisvaikutelma;

		arviointi.setKokonaistulos(kokonaistulos);
		arviointiRepository.save(arviointi);

		return "redirect:/";
	}


}
