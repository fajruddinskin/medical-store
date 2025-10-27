package com.medicalstore.entity;

public enum MedicineType {
    TABLET("Tablet"),
    CAPSULE("Capsule"),
    SYRUP("Syrup"),
    INJECTION("Injection"),
    OINTMENT("Ointment"),
    DROPS("Drops"),
    INHALER("Inhaler"),
    OTHER("Other");

    private final String displayName;

    MedicineType(String displayName) {
        this.displayName = displayName;
    }

    public String getType() {
        return displayName;
    }

}