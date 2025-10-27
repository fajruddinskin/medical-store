package com.medicalstore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PrescriptionItems")
public class PrescriptionItem {
    @Id
        private Long id;
    //Medicine medicine
    private String dosage;
    private String frequency;
    private Integer duration;
    private String instructions;

    public PrescriptionItem(Long id, String dosage, String frequency, Integer duration, String instructions) {
        this.id = id;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.instructions = instructions;
    }

    public PrescriptionItem() {
    }

    public Long getId() {  return id;  }

    public void setId(Long id) { this.id = id; }

    public String getDosage() {   return dosage; }

    public void setDosage(String dosage) {  this.dosage = dosage; }

    public String getFrequency() {  return frequency; }

    public void setFrequency(String frequency) {  this.frequency = frequency; }

    public Integer getDuration() {  return duration;   }

    public void setDuration(Integer duration) { this.duration = duration;  }

    public String getInstructions() { return instructions;   }

    public void setInstructions(String instructions) { this.instructions = instructions; }
}
