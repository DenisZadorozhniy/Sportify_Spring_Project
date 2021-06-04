package com.sportify.Sportify.service;

import com.sportify.Sportify.model.AboutSection;
import com.sportify.Sportify.repository.AboutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutBlockService {

    @Autowired
    AboutRepository aboutRepository;

    public Iterable<AboutSection> findAll(){
        return aboutRepository.findAll();
    }

}
