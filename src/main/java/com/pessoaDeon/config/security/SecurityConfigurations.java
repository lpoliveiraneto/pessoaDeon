package com.pessoaDeon.config.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "/api/v1/login").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/v1/analista/**").hasRole("ADM");
                    req.requestMatchers(HttpMethod.POST, "/api/v1/autenticacaoAnalista/**").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/v1/resetSenha").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/api/v1/cadastro/salvar").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/api/v1/esqueciMinhaSenha").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/api/v1/natureza/listaNaturezas").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/api/v1/cadastro/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/api/v1/lista/**").permitAll();
                    req.requestMatchers(HttpMethod.GET, "/api/v1/ocorrencia/**").hasRole("ANALISTA");
                    req.requestMatchers(HttpMethod.GET, "/api/v1/violenciaDomestica/**").hasRole("MULHER");
                    req.anyRequest().authenticated(); 
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .cors( c -> {
                        var configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Arrays.asList("*"));
                        configuration.setAllowedMethods(Arrays.asList("GET","POST","DELETE","PUT", "PATCH"));
                    })
                .build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws  Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

