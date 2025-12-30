package com.medicalstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "report_results")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicalReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // Test ID (String for flexibility)
    @Column(columnDefinition = "TEXT")
    private String content;
    // Optional: save creation date
    private String createdAt;

    // Getters and setters
    public String getId() {
        return id; }
    public void setId(String id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
