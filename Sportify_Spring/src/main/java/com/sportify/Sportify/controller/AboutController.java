package com.sportify.Sportify.controller;

import com.sportify.Sportify.model.AboutSection;
import com.sportify.Sportify.model.Trainer;
import com.sportify.Sportify.service.AboutBlockService;
import com.sportify.Sportify.service.CandidateTrainerService;
import com.sportify.Sportify.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class AboutController {

    @Autowired
    AboutBlockService aboutBlockService;

    @Autowired
    TrainerService trainerService;

    @Autowired
    CandidateTrainerService candidateTrainerService;

    @GetMapping("/about")
    public String about(Model model) {
        Iterable<AboutSection> aboutText = aboutBlockService.findAll();
        List<Trainer> trainers = trainerService.findAllTrainers();

        model.addAttribute("trainers", trainers);
        model.addAttribute("aboutText", aboutText);

        return "/about";
    }

    @GetMapping("/trainerForm")
    public String trainerForm(Model model) {
        return "/trainerForm";
    }

    @PostMapping("/trainerForm")
    public String sendTrainerForm(@RequestParam String fullName, @RequestParam String email,
                                  @RequestParam String phone, @RequestParam String category,
                                  @RequestParam String aboutCandidate, @RequestParam("file") MultipartFile file) {

        String image = null;
        try {
            image = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        candidateTrainerService.saveCandidateTrainer(image, fullName, email, phone, category, aboutCandidate);

        return "redirect:/about";
    }
}
