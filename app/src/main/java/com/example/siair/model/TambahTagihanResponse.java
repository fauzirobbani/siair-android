package com.example.siair.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TambahTagihanResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private Tagihan data = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Tagihan getData() {
        return data;
    }

    public void setData(Tagihan data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
