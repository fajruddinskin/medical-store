package com.medicalstore.entity;

public enum BloodGroup {
    A("A"),
    AB("AB"),
    O("O"),
    A_possetive("A+"),

    A_negative("A-"),

    B_possetive("B+"),

    Bnegative("B-"),

    ABposetive("AB+"),

    ABnegative("AB-"),

    Oposetive("o+"),

    Onegative("B-");
    private final String displayGroup;

          BloodGroup(String displayGroup) {
        this.displayGroup = displayGroup;

    }
    public String getDisplayGroup() {
        return displayGroup;
    }

}
