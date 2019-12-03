package com.example.prototype.catatanpohon.rest.services;

import com.example.prototype.catatanpohon.rest.responses.KategoriResponse;
import com.example.prototype.catatanpohon.rest.responses.PostPutDelKategori;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface KategoriService
{
    @GET("kategori")
    Call<KategoriResponse> getListDataKetegori();
    @FormUrlEncoded
    @POST("kategori")
    Call<PostPutDelKategori> postKategori(@Field("katNama") String katNama);
    @FormUrlEncoded
    @PUT("kategori")
    Call<PostPutDelKategori> putKategori(@Field("katNama") String katNama,@Field("id") String id);
    @FormUrlEncoded
    @HTTP(method="DELETE",path="kategori",hasBody=true)
    Call<PostPutDelKategori> deleteKontak(@Field("id") String id);
}
