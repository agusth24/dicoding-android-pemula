package com.example.prototype.catatanpohon.model;

import com.google.gson.annotations.SerializedName;

public class Kategori
{
    @SerializedName("katId")
    private String katId;
    @SerializedName("katNama")
    private String katNama;

    public Kategori(){}

    public String getKatId()
    {
        return katId;
    }

    public void setKatId(String katId)
    {
        this.katId = katId;
    }

    public String getKatNama()
    {
        return katNama;
    }

    public void setKatNama(String katNama)
    {
        this.katNama = katNama;
    }
}
