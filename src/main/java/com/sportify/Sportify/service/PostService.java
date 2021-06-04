package com.sportify.Sportify.service;

import com.sportify.Sportify.model.Post;
import com.sportify.Sportify.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Date;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Page<Post> findAll(int page, int sizePost){
        return  postRepository.findAll(PageRequest.of(page, sizePost));
    }

    public void addPost(String image, String title, Date timeArticle, String author, String anons, String text) {

        Post post = new Post();
        post.setImage(image);
        post.setTitle(title);
        post.setTimeArticle(timeArticle);
        post.setAuthor(author);
        post.setAnons(anons);
        post.setText(text);
        postRepository.save(post);
    }

    public Optional<Post> findPostById(Long id) {
        Optional<Post> post = null;
        if (postRepository.existsById(id)) {
            post = postRepository.findById(id);
        }
        return post;
    }

    public boolean updateInformation(Long id, String image, String title, String anons, String text) {
        Post post = new Post();

        try {
            post = postRepository.findById(id).orElseThrow(() ->
                    new Exception(String.valueOf(id) + "Такой id не найден"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        post.setImage(image);
        post.setTitle(title);
        post.setAnons(anons);
        post.setText(text);
        postRepository.save(post);
        return true;
    }

    public boolean deletePost(Long id) {
        if (postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<Post> searchPage(String searchString, int page, int sizePost ){
        Page<Post> pagePosts = null;
        if(searchString != null){
            pagePosts = postRepository.findByTitleContainingIgnoreCase(searchString, PageRequest.of(page, sizePost));
        }
        return pagePosts;
    }



}
