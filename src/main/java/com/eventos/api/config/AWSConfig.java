package com.eventos.api.config;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public AmazonS3 createS3Instance(){ //Verificar como cadastrar credenciais AWS no PC
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(awsRegion)
                .build();
    }

}
