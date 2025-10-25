package com.medicalstore.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.PERSIST , fetch = FetchType.LAZY)
    @JoinColumn(name = "medicines_id")
    private List<Medicine> medicines = new ArrayList<>();

    // Constructors
    public Category() {
    }

    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    // Helper methods for bidirectional relationship
    public void addMedicine(Medicine medicine) {
        medicines.add(medicine);
        medicine.setCategory(this);
    }

    public void removeMedicine(Medicine medicine) {
        medicines.remove(medicine);
        medicine.setCategory(null);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", medicinesCount=" + (medicines != null ? medicines.size() : 0) +
                '}';
    }
}