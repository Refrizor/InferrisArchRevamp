package com.inferris.model;

public class RankDTO {
    private int staff;
    private int builder;
    private int donor;
    private int other;

    public RankDTO(int staff, int builder, int donor, int other) {
        this.staff = staff;
        this.builder = builder;
        this.donor = donor;
        this.other = other;
    }

    public RankDTO(){
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

    public int getOther() {
        return other;
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

    public void setOther(int other) {
        this.other = other;
    }
}