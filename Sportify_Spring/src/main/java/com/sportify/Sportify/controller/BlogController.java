package com.sportify.Sportify.controller;

import com.sportify.Sportify.model.Post;
import com.sportify.Sportify.model.User;
import com.sportify.Sportify.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

@Controller
public class BlogController {

    @Autowired
    PostService postService;

    @GetMapping("/blog")
    public String paginationBlog(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        int sizePost = 5;
        Page<Post> pagePosts = postService.findAll(page, sizePost);

        model.addAttribute("pagePost", pagePosts.getContent());
        model.addAttribute("pagePostTotalPage", pagePosts.getTotalPages());
        model.addAttribute("pagePostNumber", pagePosts.getNumber());
        model.addAttribute("numbers", IntStream.range(0, pagePosts.getTotalPages()).toArray());

        return "/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetail(Model model, @PathVariable(value = "id") Long id) {

        Optional<Post> post = postService.findPostById(id);
        if (post.isPresent()) {
            ArrayList<Post> result = new ArrayList<>();
            post.ifPresent(result::add);
            model.addAttribute("postDetail", result);
            return "blogDetail";
        } else {
            return "redirect/blog";
        }
    }

    @GetMapping("/adminPage/{id}/addArticle")
    public String addArticle(Model model) {
        model.addAttribute("message", "Максимум 5MB");
        return "addArticle";
    }

    @PostMapping("addArticle")
    public String postAddArticle(@AuthenticationPrincipal User user, @RequestParam String title, @RequestParam String anons,
                                 @RequestParam String text, @RequestParam("file") MultipartFile file) {

        String image = null;
        String author;
        Date timeArticle;

        Byte[] bArray = null;
        author = user.getUsername();

        Calendar calendar = Calendar.getInstance();
        timeArticle = calendar.getTime();

        try {
            bArray = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                bArray[i++] = b;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a valid file");
        }

        try {
            image = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        postService.addPost(image, title, timeArticle, author, anons, text);

        return "redirect:/adminPage";
    }

    @GetMapping("/blog/{id}/edit")
    public String editArticle(@PathVariable(value = "id") long id, Model model) {

        Optional<Post> post = postService.findPostById(id);
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        model.addAttribute("postDetail", result);
        return "/edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String updateArticle(@PathVariable(value = "id") long id, @RequestParam String title,
                                @RequestParam String anons, @RequestParam String text, @RequestParam("file") MultipartFile file
    ) {

        String image = null;
        try {
            image = Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        postService.updateInformation(id, image, title, anons, text);

        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String removeArticle(@PathVariable(value = "id") long id) {

        postService.deletePost(id);
        return "redirect:/blog";
    }
}
