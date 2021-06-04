package com.sportify.Sportify.security;

import com.sportify.Sportify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        UserService userService;

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf()
                    .disable()

                    .authorizeRequests()
                    //Доступ только для не зарегистрированных пользователей
                    .antMatchers("/", "/index","about","/blog", "/login", "/registration").permitAll()

                    //Доступ разрешен всем
                    .antMatchers("/","/index","/blog","/blogDetail").permitAll()

                    //Доступ только для пользователей с ролью Администратор
                    .antMatchers("/adminPage/**", "/blogDetail","/trainerForm").hasRole("ADMIN")
                    .antMatchers( "/blogDetail","/trainerForm").hasRole("USER")

                    //Все остальные страницы требуют аутентификации
                    .and()
                    //Настройка для входа в систему
                    .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login-error")

                    //Перенарпавление на главную страницу после успешного входа
                    .defaultSuccessUrl("/index")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/index").permitAll();
        }

        @Autowired
        protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/plugins","/styles",
                   "/js/**", "/images/**","/fonts/**" );
        }

}


