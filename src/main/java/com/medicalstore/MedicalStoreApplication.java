package com.medicalstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:system-spring-beans.xml")
public class MedicalStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicalStoreApplication.class, args);
    }
}