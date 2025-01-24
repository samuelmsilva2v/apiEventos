package com.example.demo.infrastructure.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        modelMapper.getConfiguration()
                   .setSkipNullEnabled(true) 
                   .setFieldMatchingEnabled(true) 
                   .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return modelMapper;
    }
}
