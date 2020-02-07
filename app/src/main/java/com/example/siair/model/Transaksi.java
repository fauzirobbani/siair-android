package com.example.siair.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaksi {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("id_tagihan")
    @Expose
    private int idTagihan;
    @SerializedName("id_pelanggan")
    @Expose
    private int idPelanggan;
    @SerializedName("pembayaran")
    @Expose
    private int pembayaran;
    @SerializedName("kembalian")
    @Expose
    private int kembalian;
    @SerializedName("tanggal_transaksi")
    @Expose
    private String tanggalTransaksi;
    @SerializedName("bulan_transaksi")
    @Expose
    private int bulanTransaksi;
    @SerializedName("tahun_transaksi")
    @Expose
    private int tahunTransaksi;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTagihan() {
        return idTagihan;
    }

    public void setIdTagihan(int idTagihan) {
        this.idTagihan = idTagihan;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public int getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(int pembayaran) {
        this.pembayaran = pembayaran;
    }

    public int getKembalian() {
        return kembalian;
    }

    public void setKembalian(int kembalian) {
        this.kembalian = kembalian;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public int getBulanTransaksi() {
        return bulanTransaksi;
    }

    public void setBulanTransaksi(int bulanTransaksi) {
        this.bulanTransaksi = bulanTransaksi;
    }

    public int getTahunTransaksi() {
        return tahunTransaksi;
    }

    public void setTahunTransaksi(int tahunTransaksi) {
        this.tahunTransaksi = tahunTransaksi;
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
}
