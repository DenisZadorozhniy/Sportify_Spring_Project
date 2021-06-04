package com.sportify.Sportify.repository;

import com.sportify.Sportify.model.CandidateTrainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateTrainerRpository extends JpaRepository<CandidateTrainer, Long> {
    @Override
    List<CandidateTrainer> findAllById(Iterable<Long> longs);

    CandidateTrainer findByFullName(String fullName);
}
