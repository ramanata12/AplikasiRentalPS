package com.example.uaspam.model;

public class User {
    private String id, nama, telp, alamat, jam, pesan, harga;

    public User(String nama, String telp, String alamat, String jam,String pesan,String harga) {
        this.nama = nama;
        this.telp = telp;
        this.alamat = alamat;
        this.jam = jam;
        this.harga = harga;
        this.pesan= pesan;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNotelp() {
        return telp;
    }

    public void setNotelp(String notelp) {
        this.telp = notelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getHarga() { return harga; }

    public void setHarga(String harga) {
        harga = harga;
    }
}
