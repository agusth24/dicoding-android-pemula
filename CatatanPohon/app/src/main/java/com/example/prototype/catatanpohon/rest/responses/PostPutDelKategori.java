package com.example.prototype.catatanpohon.rest.responses;

import com.example.prototype.catatanpohon.model.Kategori;
import com.google.gson.annotations.SerializedName;

public class PostPutDelKategori
{
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private Kategori mKategori;
    @SerializedName("message")
    private String message;

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public Kategori getmKategori()
    {
        return mKategori;
    }

    public void setmKategori(Kategori mKategori)
    {
        this.mKategori = mKategori;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
