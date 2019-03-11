package com.example.webservicetugas;

public class JadwalSholatModel {
    private String kota;
    private String subuh, dhuhur, ashar, maghrib, isya;

    public JadwalSholatModel(String kota, String subuh, String dhuhur, String ashar, String maghrib, String isya) {
        this.kota = kota;
        this.subuh = subuh;
        this.dhuhur = dhuhur;
        this.ashar = ashar;
        this.maghrib = maghrib;
        this.isya = isya;
    }

    public String getKota() {
        return kota;
    }

    public String getSubuh() {
        return subuh;
    }

    public String getDhuhur() {
        return dhuhur;
    }

    public String getAshar() {
        return ashar;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public String getIsya() {
        return isya;
    }
}
