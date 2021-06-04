package com.sportify.Sportify.controller;

import com.sportify.Sportify.model.Trainer;
import com.sportify.Sportify.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TrainerController {
    @Autowired
    TrainerService trainerService;

    @GetMapping("/adminPage/{id}/editTrainer")
    public String editTrainerInform(@PathVariable(value = "id") long id, Model model) {

        Optional<Trainer> trainer = trainerService.findByIDTrainerForDetailInformation(id);
        ArrayList<Trainer> trainerDetail = new ArrayList<>();
        trainer.ifPresent(trainerDetail::add);
        model.addAttribute("trainerDetail", trainerDetail);
        return "/editTrainer";
    }

    @PostMapping("/adminPage/{id}/editTrainer")
    public String updateTrainerInform(@PathVariable(value = "id") long id, @RequestParam String fullName,
                                      @RequestParam String category, @RequestParam String description,
                                      @RequestParam("file") MultipartFile file) {

        trainerService.updateInform(id, file, fullName, category, description);
        return "redirect:/adminPage";
    }

    @PostMapping("/adminPage/{id}/deleteTrainer")
    public String removeTrainer(@PathVariable(value = "id") long id) {

        trainerService.deleteTrainer(id);
        return "redirect:/adminPage";
    }

}
