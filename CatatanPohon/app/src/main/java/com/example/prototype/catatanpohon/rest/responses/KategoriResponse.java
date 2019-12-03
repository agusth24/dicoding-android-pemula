package com.example.prototype.catatanpohon.rest.responses;

import com.example.prototype.catatanpohon.model.Kategori;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KategoriResponse
{
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Kategori> listDataKetegori;
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

    public List<Kategori> getListDataKetegori()
    {
        return listDataKetegori;
    }

    public void setListDataKetegori(List<Kategori> listDataKetegori)
    {
        this.listDataKetegori = listDataKetegori;
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
