package com.medicalstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import jakarta.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
@ImportResource("classpath:system-spring-beans.xml")
public class MedicalStoreApplication {

    private static final String APP_URL = "http://localhost:8081";

    public static void main(String[] args) {
        createDataDirectory();
        SpringApplication.run(MedicalStoreApplication.class, args);
    }

    /** Create data directory safely */
    private static void createDataDirectory() {
        try {
            Path dataDir = Path.of(System.getProperty("user.dir"), "data");
            Files.createDirectories(dataDir);
            System.out.println("DB path = " + dataDir.resolve("medical_store.db"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create data directory", e);
        }
    }

    /** Open browser after Spring context is ready */
    @PostConstruct
    public void openBrowser() {
        new Thread(() -> {
            try {
                Thread.sleep(3000); // wait for Tomcat
                Runtime.getRuntime().exec(
                        new String[]{"cmd", "/c", "start", APP_URL}
                );
                System.out.println("Browser opened: " + APP_URL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "browser-opener").start();
    }
}
