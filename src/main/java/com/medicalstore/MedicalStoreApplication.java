package com.medicalstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@SpringBootApplication
@ImportResource("classpath:system-spring-beans.xml")
public class MedicalStoreApplication {
    public static void main(String[] args) throws IOException {
        Path dataDir = Path.of(System.getProperty("user.dir"), "data");
        Files.createDirectories(dataDir);

        SpringApplication.run(MedicalStoreApplication.class, args);
        System.out.println("DB path = " + System.getProperty("user.dir") + "/data/medical_store.db");
    }
   /* @PostConstruct
    public void openBrowser() throws Exception {
        Desktop.getDesktop().browse(new URI("http://localhost:8080"));
    }*/
}