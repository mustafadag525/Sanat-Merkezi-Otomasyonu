package com.company;

public class Kurs {

    private int kursID;
    private String kursAD;

    public Kurs(int kursID, String kursAD) {
        this.kursID = kursID;
        this.kursAD = kursAD;
    }

    public int getKursID() {
        return kursID;
    }

    public void setKursID(int kursID) {
        this.kursID = kursID;
    }

    public String getKursAD() {
        return kursAD;
    }

    public void setKursAD(String kursAD) {
        this.kursAD = kursAD;
    }
}
