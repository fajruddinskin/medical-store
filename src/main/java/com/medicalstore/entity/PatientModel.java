package com.medicalstore.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class PatientModel extends UserModel {

    private String medicalHistory;
    private LocalDate dateOfBirth;
    private String gender;
    private int age;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BloodGroup bloodGroup;
    private String doctor;
    private String reffered_By;
    public PatientModel() {
        super();
    }

    public PatientModel(String name, String phoneNumber, String email, String medicalHistory, LocalDate dateOfBirth, String gender,int age,String doctor,String reffered_By, BloodGroup  bloodGroup) {
        super(name, phoneNumber, email);
        this.medicalHistory = medicalHistory;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.doctor = doctor;
        this.reffered_By = reffered_By;
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

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getReffered_By() {
        return reffered_By;
    }

    public void setReffered_By(String reffered_By) {
        this.reffered_By = reffered_By;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}