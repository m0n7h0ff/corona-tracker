package com.tracker;

import com.tracker.models.Location;
import com.tracker.services.VirusDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    final VirusDataService virusDataService;

    public HomeController(VirusDataService virusDataService) {
        this.virusDataService = virusDataService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Location> allStats = virusDataService.getAllStats();
        int totalCasesWorlwide = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDelta()).sum();
        model.addAttribute("locationStatistics",allStats);
        model.addAttribute("totalCasesWorldwide", totalCasesWorlwide);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
