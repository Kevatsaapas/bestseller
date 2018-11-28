package com.syntaxterror.bestseller.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syntaxterror.bestseller.model.Arviointi;
import com.syntaxterror.bestseller.model.Kilpailija;
import com.syntaxterror.bestseller.model.Kilpailu;
import com.syntaxterror.bestseller.model.Lohko;
import com.syntaxterror.bestseller.model.Ostaja;
import com.syntaxterror.bestseller.model.OstajaArviointi;
import com.syntaxterror.bestseller.model.Tuomari;
import com.syntaxterror.bestseller.repository.ArviointiRepository;
import com.syntaxterror.bestseller.repository.KilpailijaRepository;
import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.repository.LohkoRepository;
import com.syntaxterror.bestseller.repository.OstajaArviointiRepository;
import com.syntaxterror.bestseller.repository.TuomariRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TuomariService {

	@Autowired
	private TuomariRepository tuomariRepository;

	@Autowired
	private KilpailuRepository kilpailuRepository;

	@Autowired
	private LohkoRepository lohkoRepository;

	@Autowired
	private KilpailijaRepository kilpailijaRepository;

	@Autowired
	private ArviointiRepository arviointiRepository;

	@Autowired
	private OstajaArviointiRepository ostajaArviointiRepository;

	public List<Kilpailija> haeKilpailijatTuomarille(Tuomari tuomari, Lohko lohko) {

		List<Kilpailija> kilp = kilpailijaRepository.findByKilpailuIdAndLohko(tuomari.getKilpailuId(), lohko);
		List<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
		List<Arviointi> arvioinnit = arviointiRepository.findByTuomari(tuomari);

		for (Kilpailija kilpailija : kilp) {
			Boolean match = false;
			for (int i = 0; i < arvioinnit.size(); i++) {
				Arviointi arviointi = arvioinnit.get(i);
				Lohko loh = arviointi.getLohko();
				if (kilpailija.equals(arviointi.getKilpailija()) && loh.equals(kilpailija.getLohko())) {
					match = true;
				}
			}
			if (!match) {
				kilpailijat.add(kilpailija);
			}
		}

		return kilpailijat;
	}

	public List<Kilpailija> haeFinalistitTuomarille(Tuomari tuomari, Lohko lohko) {

		List<Kilpailija> kilp = kilpailijaRepository.findByKilpailuIdAndFinaalissa(tuomari.getKilpailuId(),
				new Long(1));
		List<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
		List<Arviointi> arvioinnit = arviointiRepository.findByTuomariAndLohko(tuomari, lohko);

		for (Kilpailija kilpailija : kilp) {
			Boolean match = false;
			for (int i = 0; i < arvioinnit.size(); i++) {
				Arviointi arviointi = arvioinnit.get(i);
				if (kilpailija.equals(arviointi.getKilpailija())) {
					match = true;
				}
			}
			if (!match) {
				kilpailijat.add(kilpailija);
			}
		}
		;
		return kilpailijat;
	}

	public List<Kilpailija> haeFinalistitOstajalle(Ostaja ostaja, Lohko lohko) {

		List<Kilpailija> kilp = kilpailijaRepository.findByKilpailuIdAndFinaalissa(ostaja.getKilpailuId(), new Long(1));
		List<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
		List<OstajaArviointi> arvioinnit = ostajaArviointiRepository.findByOstajaAndLohko(ostaja, lohko);

		for (Kilpailija kilpailija : kilp) {
			Boolean match = false;
			for (int i = 0; i < arvioinnit.size(); i++) {
				OstajaArviointi arviointi = arvioinnit.get(i);
				if (kilpailija.equals(arviointi.getKilpailija())) {
					match = true;
				}
			}
			if (!match) {
				kilpailijat.add(kilpailija);
			}
		}

		return kilpailijat;
	}

	public List<Kilpailija> haeKilpailijatOstajalle(Ostaja ostaja, Lohko lohko) {

		List<Kilpailija> kilp = kilpailijaRepository.findByKilpailuIdAndLohko(ostaja.getKilpailuId(), lohko);
		List<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();
		List<OstajaArviointi> ostarvioinnit = ostajaArviointiRepository.findByOstaja(ostaja);

		for (Kilpailija kilpailija : kilp) {
			Boolean match = false;
			for (int i = 0; i < ostarvioinnit.size(); i++) {
				OstajaArviointi ostajaArviointi = ostarvioinnit.get(i);
				Lohko loh = ostajaArviointi.getLohko();
				if (kilpailija.equals(ostajaArviointi.getKilpailija()) && loh.equals(kilpailija.getLohko())) {
					match = true;
				}
			}
			if (!match) {
				kilpailijat.add(kilpailija);
			}
		}

		return kilpailijat;
	}

	public Boolean onkotuomariValmis(Tuomari tuomari, Lohko lohko) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(tuomari.getKilpailuId());

		List<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();

		if (kilpailu.getFinaali() == 0) {
			List<Kilpailija> kilp = kilpailijaRepository.findByKilpailuIdAndLohko(tuomari.getKilpailuId(), lohko);
			List<Arviointi> arvioinnit = arviointiRepository.findByTuomari(tuomari);

			for (Kilpailija kilpailija : kilp) {
				Boolean match = false;

				for (int i = 0; i < arvioinnit.size(); i++) {
					Arviointi arviointi = arvioinnit.get(i);
					Lohko loh = arviointi.getLohko();
					if (kilpailija.equals(arviointi.getKilpailija()) && loh.equals(kilpailija.getLohko())) {
						match = true;
					}
				}
				if (!match) {
					kilpailijat.add(kilpailija);
				}
			}
		} else {

			Lohko finaalilohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, "finaali");
			List<Kilpailija> kilp = kilpailijaRepository.findByKilpailuIdAndFinaalissa(tuomari.getKilpailuId(),
					new Long(1));
			List<Arviointi> arvioinnit = arviointiRepository.findByTuomariAndLohko(tuomari, finaalilohko);
			for (Kilpailija kilpailija : kilp) {
				Boolean match = false;

				for (int i = 0; i < arvioinnit.size(); i++) {
					Arviointi arviointi = arvioinnit.get(i);
					if (kilpailija.equals(arviointi.getKilpailija())) {
						match = true;
					}
				}

				if (!match) {
					kilpailijat.add(kilpailija);
				}
			}
		}

		if (kilpailijat.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public Boolean onkoOstajaValmis(Ostaja ostaja, Lohko lohko) {
		Kilpailu kilpailu = kilpailuRepository.findByKilpailuId(ostaja.getKilpailuId());

		List<Kilpailija> kilpailijat = new ArrayList<Kilpailija>();

		if (kilpailu.getFinaali() == 0) {
			List<Kilpailija> kilp = kilpailijaRepository.findByKilpailuIdAndLohko(ostaja.getKilpailuId(), lohko);
			List<OstajaArviointi> ostarvioinnit = ostajaArviointiRepository.findByOstaja(ostaja);

			for (Kilpailija kilpailija : kilp) {
				Boolean match = false;

				for (int i = 0; i < ostarvioinnit.size(); i++) {
					OstajaArviointi ostajaArviointi = ostarvioinnit.get(i);
					Lohko loh = ostajaArviointi.getLohko();
					if (kilpailija.equals(ostajaArviointi.getKilpailija()) && loh.equals(kilpailija.getLohko())) {
						match = true;
					}
				}
				if (!match) {
					kilpailijat.add(kilpailija);
				}
			}
		} else {
			Lohko finaalilohko = lohkoRepository.findByKilpailuAndLohkoNro(kilpailu, "finaali");
			List<Kilpailija> kilp = kilpailijaRepository.findByKilpailuIdAndFinaalissa(ostaja.getKilpailuId(),
					new Long(1));
			List<OstajaArviointi> ostarvioinnit = ostajaArviointiRepository.findByOstajaAndLohko(ostaja, finaalilohko);

			for (Kilpailija kilpailija : kilp) {
				Boolean match = false;
				for (int i = 0; i < ostarvioinnit.size(); i++) {
					OstajaArviointi arviointi = ostarvioinnit.get(i);
					if (kilpailija.equals(arviointi.getKilpailija())) {
						match = true;
					}
				}
				if (!match) {
					kilpailijat.add(kilpailija);
				}
			}
		}
		if (kilpailijat.size() > 0) {

		    return false;

		} else {

			return true;
		}
	}
}
