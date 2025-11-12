package com.medicalstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class PatientModel extends UserModel {

    private String medicalHistory;
    private LocalDate dateOfBirth;
    private String gender;
    private int age;

    public PatientModel() {
        super();
    }

    public PatientModel(String name, String phoneNumber, String email, String medicalHistory, LocalDate dateOfBirth, String gender,int age) {
        super(name, phoneNumber, email);
        this.medicalHistory = medicalHistory;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.age = age;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() { return age;  }

    public void setAge(int age) { this.age = age; }
}