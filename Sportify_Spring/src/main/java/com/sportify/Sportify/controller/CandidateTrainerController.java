package com.sportify.Sportify.controller;

import com.sportify.Sportify.service.CandidateTrainerService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CandidateTrainerController {

    private final CandidateTrainerService candidateService;

    public CandidateTrainerController(CandidateTrainerService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/adminPage/{id}/appointTrainer")
    public String appointTrainer(@PathVariable(value = "id") long id) {
        candidateService.appointTrainer(id);
        return "redirect:/adminPage";
    }

    @PostMapping("/adminPage/{id}/delete")
    public String deleteCandidateTrainer(@PathVariable(value = "id") long id) {
        candidateService.deleteCandidateTrainer(id);
        return "redirect:/adminPage";
    }
}
