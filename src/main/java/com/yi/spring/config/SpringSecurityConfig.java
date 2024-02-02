package com.yi.spring.config;


import com.yi.spring.handler.MyAccessDeniedHandler;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/images/**","/css/**", "/js/**","/user/join", "/auth/join").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER","MANAGER","ADMIN")
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .anyRequest().permitAll()
                ).formLogin(login -> login
                        .loginPage("/user/login")
                        .loginProcessingUrl("/login-process")
                        .usernameParameter("userid")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/user/dashboard", true)
                        .failureUrl("/user/login")
                        .permitAll()

                ).exceptionHandling( e -> e.accessDeniedHandler(new AccessDeniedHandlerImpl()))
                .logout(Customizer.withDefaults());

       return http.build();
    }

    private static class AccessDeniedHandlerImpl implements AccessDeniedHandler {
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
            response.sendRedirect("/access-denied");
        }
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
