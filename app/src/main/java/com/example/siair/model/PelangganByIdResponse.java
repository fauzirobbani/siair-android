package com.example.siair.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PelangganByIdResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private PelangganById data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public PelangganById getData() {
        return data;
    }

    public void setData(PelangganById data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


