package com.example.yoraming.Server;

import com.example.yoraming.items.YoramData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
//요람 정보 통신 스
public interface yoramFactory {
    @GET("/yoram")
    Call<JsonObject> getYoram(@Query("user_id") String email);

    @POST("/yoram")
    Call<JsonObject> postYoram(@Body YoramData yoram);

    @DELETE("/yoram")
    Call<JsonObject> deleteYoram(@Query("yoram_id") String yoram_id);

    @PUT("/yoram")
    Call<JsonObject> updateYoram(@Body YoramData yoram);
}
