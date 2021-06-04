package com.sportify.Sportify.repository;

import com.sportify.Sportify.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByFullName(String fullName);
}
