package com.sportify.Sportify.controller;

import com.sportify.Sportify.model.CandidateTrainer;
import com.sportify.Sportify.model.Trainer;
import com.sportify.Sportify.model.User;
import com.sportify.Sportify.service.CandidateTrainerService;
import com.sportify.Sportify.service.TrainerService;
import com.sportify.Sportify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    TrainerService trainerService;

    @Autowired
    CandidateTrainerService candidateTrainerService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model) {
        List<CandidateTrainer> candidateTrainers = candidateTrainerService.findAllCandidate();
        model.addAttribute("candidateTrainers", candidateTrainers);
        int sizeCadidates = candidateTrainers.size();
        model.addAttribute("sizeCadidates", sizeCadidates);

        List<User> allUser = userService.allUsers();
        model.addAttribute("allUser", allUser);
        int size = allUser.size();
        model.addAttribute("size", size);

        List<Trainer> allTrainers = trainerService.findAllTrainers();
        model.addAttribute("allTrainers", allTrainers);
        int sizeTrainer = allTrainers.size();
        model.addAttribute("sizeTrainer", sizeTrainer);

        return "/adminPage";
    }
}
