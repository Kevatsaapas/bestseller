package com.syntaxterror.bestseller.control;


        import com.syntaxterror.bestseller.repository.KilpailuRepository;
import com.syntaxterror.bestseller.service.LeaderboardService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeaderboardController {

    @Autowired
    public LeaderboardService leaderboardService;
    @Autowired
    public KilpailuRepository kilpailuRepository;


    @RequestMapping("/pisteet/{kilpailuId}")
    private String luoLeaderboard(@PathVariable Long kilpailuId, Model model){
    	model.addAttribute("kilpailu", kilpailuRepository.findByKilpailuId(kilpailuId));
    	model.addAttribute("arvioinnit",leaderboardService.palautaParhaastaHuonoimpaan(kilpailuId));
        return "pisteet";
    }

}
