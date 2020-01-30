package com.example.siair.model;

public class TagihanRequestBody {

    private int no_rekening;
    private int meteran_baru;
    private int volume;
    private int total;

    public TagihanRequestBody(int no_rekening, int meteran_baru, int volume, int total) {
        this.no_rekening = no_rekening;
        this.meteran_baru = meteran_baru;
        this.volume = volume;
        this.total = total;
    }

    public int getNo_rekening() {
        return no_rekening;
    }

    public void setNo_rekening(int no_rekening) {
        this.no_rekening = no_rekening;
    }

    public int getMeteran_baru() {
        return meteran_baru;
    }

    public void setMeteran_baru(int meteran_baru) {
        this.meteran_baru = meteran_baru;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
