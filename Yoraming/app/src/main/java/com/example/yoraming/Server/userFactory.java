package com.example.yoraming.Server;

import com.example.yoraming.items.UserData;
import com.example.yoraming.items.YoramData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//유저정보 통신 인터페이스
public interface userFactory {
    @GET("/user")
    Call<JsonObject> getUser(@Query("user_id") String email);

    @POST("/user")
    Call<JsonObject> postUser(@Body UserData user);
}
