package com.example.travel_logistic_code.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.stream.Collectors;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
////    @Bean
////    public SecurityFilterChain SecurityFilterChain (HttpSecurity httpSecurity) throws Exception{
////
////        return httpSecurity
////                .csrf(csrf -> csrf.disable())
////                .formLogin(Customizer.withDefaults())
////                .oauth2Login(Customizer.withDefaults())
////                .build();
////    }
////
////    //Estableciendo nomenclatura de permisos
////    private Collection<SimpleGrantedAuthority> mapAuthorities (Collection<? extends GrantedAuthority> authorities){
////
////        return authorities.stream()
////                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().
////                        replace("SCOPE_","")))
////                .collect(Collectors.toList());
////    }
//}
