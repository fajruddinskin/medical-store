package com.medicalstore.entity;

public enum ReportStatus {

    IN_PROGRESS("In Progress"),
    DONE("Done"),
    PENDING("Pending");

    private final String displayName;

    ReportStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
