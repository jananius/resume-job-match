package com.jan.resume_job_match;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AnalysisHistoryController {

    private final AnalysisRepository analysisRepository;


    public AnalysisHistoryController(
            AnalysisRepository analysisRepository) {

        this.analysisRepository = analysisRepository;
    }


    @GetMapping("/history")
    public String history(Model model) {

        List<Analysis> analyses =
                analysisRepository.findAll();

        model.addAttribute(
                "analyses",
                analyses
        );

        return "history";
    }
}