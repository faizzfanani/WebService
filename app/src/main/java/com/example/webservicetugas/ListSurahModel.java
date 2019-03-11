package com.example.webservicetugas;

public class ListSurahModel {
    private String nama;
    private String nomor;
    private String arti;

    public ListSurahModel(String nama, String nomor, String arti) {
        this.nama = nama;
        this.nomor = nomor;
        this.arti = arti;
    }

    public String getNama() {
        return nama;
    }

    public String getNomor() {
        return nomor;
    }

    public String getArti() {
        return arti;
    }

}
