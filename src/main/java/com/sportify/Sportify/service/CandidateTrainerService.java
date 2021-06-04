package com.sportify.Sportify.service;

import com.sportify.Sportify.model.CandidateTrainer;
import com.sportify.Sportify.model.Trainer;
import com.sportify.Sportify.repository.CandidateTrainerRpository;
import com.sportify.Sportify.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class CandidateTrainerService {

    @Autowired
    public CandidateTrainerRpository candidateTrainerRpository;

    @Autowired
    public TrainerRepository trainerRepository;

    public List<CandidateTrainer> findAllCandidate(){
       return candidateTrainerRpository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public CandidateTrainer loadCandidateByFullName(String fullName){
        CandidateTrainer candidateTrainer = candidateTrainerRpository.findByFullName(fullName);
        if (candidateTrainer == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return candidateTrainer;
    }

    public boolean saveCandidateTrainer(String image,String fullName,String email,String phone,String category,String aboutCandidate){

        CandidateTrainer candidateFromDB = candidateTrainerRpository.findByFullName(fullName);

        if(candidateFromDB != null){
            return false;
        }

        CandidateTrainer candidateTrainer = new CandidateTrainer();
        candidateTrainer.setImage(image);
        candidateTrainer.setFullName(fullName);
        candidateTrainer.setCategory(category);
        candidateTrainer.setEmail(email);
        candidateTrainer.setPhone(phone);
        candidateTrainer.setAboutCandidate(aboutCandidate);
        candidateTrainerRpository.save(candidateTrainer);

        return true; 
    }

    public boolean deleteCandidateTrainer(Long id) {
        if (candidateTrainerRpository.findById(id).isPresent()) {
            candidateTrainerRpository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean appointTrainer(Long id){
        CandidateTrainer candidat = null;
        try {
            candidat = candidateTrainerRpository.findById(id).orElseThrow(() ->
                    new Exception(String.valueOf(id) + "Такой id не найден"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String image = candidat.getImage();
        String fullName = candidat.getFullName();
        String category = candidat.getCategory();
        String description = candidat.getAboutCandidate();

        Trainer trainer = new Trainer(image, fullName, category, description);
        trainerRepository.save(trainer);
        candidateTrainerRpository.delete(candidat);

        return true;
    }

}
