package com.sportify.Sportify.service;

import com.sportify.Sportify.model.Trainer;
import com.sportify.Sportify.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    TrainerRepository trainerRepository;

    public List<Trainer> findAllTrainers() {
        return trainerRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public boolean saveTrainer(Trainer trainer) {

        Trainer trainerFromDB = trainerRepository.findByFullName(trainer.getFullName());

        if (trainerFromDB != null) {
            return false;
        }

        trainer.setImage(trainer.getImage());
        trainer.setFullName(trainer.getFullName());
        trainer.setCategory(trainer.getCategory());
        trainer.setDescription(trainer.getDescription());
        trainerRepository.save(trainer);

        return true;
    }

    public Optional findByIDTrainerForDetailInformation(Long id) {
        Optional<Trainer> trainer = null;
        if (trainerRepository.existsById(id)) {
            trainer = trainerRepository.findById(id);
        }
        return trainer;
    }

    public void updateInform(Long id, MultipartFile file, String fullName, String category, String description) {

        Trainer trainer = null;
        try {
            trainer = trainerRepository.findById(id).orElseThrow(() ->
                    new Exception(String.valueOf(id) + "Такой id не найден"));
            trainer.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            trainer.setFullName(fullName);
            trainer.setCategory(category);
            trainer.setDescription(description);
            trainerRepository.save(trainer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteTrainer(Long id) {
        if (trainerRepository.findById(id).isPresent()) {
            trainerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
