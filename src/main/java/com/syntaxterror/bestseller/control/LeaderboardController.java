package com.syntaxterror.bestseller.control;


        import com.syntaxterror.bestseller.service.LeaderboardService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LeaderboardController {

    @Autowired
    public LeaderboardService leaderboardService;


    @RequestMapping("/pisteet")
    private String luoLeaderboard(){

        return "";
    }

}
