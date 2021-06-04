package com.sportify.Sportify.security;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/blog").setViewName("blog");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/blog").setViewName("blog");
        registry.addViewController("/blogDetail").setViewName("blogDetail");
        registry.addViewController("/forAdmin").setViewName("forAdmin");

    }
}
