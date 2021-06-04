package com.sportify.Sportify.repository;

import com.sportify.Sportify.model.AboutSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutRepository extends JpaRepository<AboutSection, Long> {
}
