package com.example.siair.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tagihan {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("id_pelanggan")
    @Expose
    private int idPelanggan;
    @SerializedName("meteran_baru")
    @Expose
    private int meteranBaru;
    @SerializedName("volume")
    @Expose
    private int volume;
    @SerializedName("tagihan")
    @Expose
    private int tagihan;
    @SerializedName("status_bayar")
    @Expose
    private int statusBayar;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("bulan")
    @Expose
    private int bulan;
    @SerializedName("tahun")
    @Expose
    private int tahun;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("pelanggan")
    @Expose
    private Pelanggan pelanggan;

    @SerializedName("transaksi")
    @Expose
    private Transaksi transaksi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public int getMeteranBaru() {
        return meteranBaru;
    }

    public void setMeteranBaru(int meteranBaru) {
        this.meteranBaru = meteranBaru;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getTagihan() {
        return tagihan;
    }

    public void setTagihan(int tagihan) {
        this.tagihan = tagihan;
    }

    public int getStatusBayar() {
        return statusBayar;
    }

    public void setStatusBayar(int statusBayar) {
        this.statusBayar = statusBayar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }
}
