package com.medicalstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.medicalstore.repository")
public class SQLiteConfig {
    // This enables JPA repositories scanning
}