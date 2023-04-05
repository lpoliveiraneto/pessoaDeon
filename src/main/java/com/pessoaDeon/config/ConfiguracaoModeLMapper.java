package com.pessoaDeon.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoModeLMapper {

    @Bean
    public ModelMapper obterModelMapper(){
        return new ModelMapper();
    }
}
