package com.company;

import java.util.ArrayList;

public class Kursiyer {

    private int kursiyerID;
    private String kursiyerAD;
    private int kursiyerYas;
    public ArrayList<Kurs> kurs1;

    public Kursiyer(int kursiyerID, String kursiyerAD, int kursiyerYas) {
        this.kursiyerID = kursiyerID;
        this.kursiyerAD = kursiyerAD;
        this.kursiyerYas = kursiyerYas;
        this.kurs1=new ArrayList<Kurs>();
    }

    public int getKursiyerID() {
        return kursiyerID;
    }

    public void setKursiyerID(int kursiyerID) {
        this.kursiyerID = kursiyerID;
    }

    public String getKursiyerAD() {
        return kursiyerAD;
    }

    public void setKursiyerAD(String kursiyerAD) {
        this.kursiyerAD = kursiyerAD;
    }

    public int getKursiyerYas() {
        return kursiyerYas;
    }

    public void setKursiyerYas(int kursiyerYas) {
        this.kursiyerYas = kursiyerYas;
    }
}
