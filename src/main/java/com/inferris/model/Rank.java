package com.inferris.model;

public class Rank {
    private int staff;
    private int builder;
    private int donor;
    private int special;

    public Rank(int staff, int builder, int donor, int other) {
        this.staff = staff;
        this.builder = builder;
        this.donor = donor;
        this.special = other;
    }

    public Rank(){
    }

    public int getStaff() {
        return staff;
    }

    public int getBuilder() {
        return builder;
    }

    public int getDonor() {
        return donor;
    }

    public int getSpecial() {
        return special;
    }

    public void setStaff(int staff) {
        this.staff = staff;
    }

    public void setBuilder(int builder) {
        this.builder = builder;
    }

    public void setDonor(int donor) {
        this.donor = donor;
    }

    public void setSpecial(int other) {
        this.special = other;
    }
}