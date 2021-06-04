package com.sportify.Sportify.controller;

import com.sportify.Sportify.model.Post;
import com.sportify.Sportify.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.stream.IntStream;

@Controller
public class SearchController {

    @Autowired
    PostService postService;

    @GetMapping("/search")
    public String searchPage(){
        return "/search";
    }

    @PostMapping("search")
    public String searchPage(@RequestParam("searchString") String searchString,
                             @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                             Model model){
        int sizePost = 5;
        Page<Post> pagePosts = postService.searchPage(searchString,page,sizePost);
        model.addAttribute("pagePost", pagePosts.getContent());
        model.addAttribute("pagePostTotalPage", pagePosts.getTotalPages());
        model.addAttribute("pagePostNumber", pagePosts.getNumber());
        model.addAttribute("numbers", IntStream.range(0, pagePosts.getTotalPages()).toArray());

        return "search";
    }

}
