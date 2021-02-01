package jatin.mohanty.coronavirustracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jatin.mohanty.coronavirustracker.services.CoronaVirusDataService;
import jatin.mohanty.coronavirustracker.services.models.LocationStats;
import java.util.List;

//Rest Controller is shorthand for saying all methods in controller return rest response, return to json response and returned back
@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;
    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> all_stats = coronaVirusDataService.getAll_stats();
        int total_us_cases = all_stats.stream().mapToInt(stat -> stat.getLatest_cases()).sum();
        int total_new_cases = all_stats.stream().mapToInt(stat -> stat.getTodays_cases()).sum();
        model.addAttribute( "locationStats",all_stats);
        model.addAttribute("total_reported_cases", total_us_cases);
        model.addAttribute("total_new_cases", total_new_cases);
        //return template value
        return "home";
    }
}
