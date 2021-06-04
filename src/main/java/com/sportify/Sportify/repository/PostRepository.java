package com.sportify.Sportify.repository;

import com.sportify.Sportify.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll (Pageable pageable);
    Page<Post> findByTitleContainingIgnoreCase (String title, Pageable pageable);
}
