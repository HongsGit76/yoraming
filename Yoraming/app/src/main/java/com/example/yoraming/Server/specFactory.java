package com.example.yoraming.Server;

import com.example.yoraming.items.SpecCareerData;
import com.example.yoraming.items.SpecCertificateData;
import com.example.yoraming.items.SpecEtcData;
import com.example.yoraming.items.SpecLanguageData;
import com.example.yoraming.items.UserData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//스펙 정보 통신 인터페이스
public interface specFactory {
    @POST("/spec/career")
    Call<JsonObject> postcareer(@Body SpecCareerData specCareerData);

    @GET("/spec/career")
    Call<JsonObject> getcareer(@Query("user_id") String email);

    

    @POST("/spec/certificate")
    Call<JsonObject> postcertificate(@Body SpecCertificateData specCertificateData);

    @GET("/spec/certificate")
    Call<JsonObject> getcertificate(@Query("user_id") String email);




    @POST("/spec/language")
    Call<JsonObject> postlanguage(@Body SpecLanguageData specLanguageData);

    @GET("/spec/language")
    Call<JsonObject> getlanguage(@Query("user_id") String email);




    @POST("/spec/etc")
    Call<JsonObject> postetc(@Body SpecEtcData specEtcData);

    @GET("/spec/etc")
    Call<JsonObject> getetc(@Query("user_id") String email);
}
