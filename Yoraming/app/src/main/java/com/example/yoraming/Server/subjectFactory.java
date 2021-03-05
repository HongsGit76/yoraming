package com.example.yoraming.Server;

import com.example.yoraming.items.SubjectData;
import com.google.gson.JsonObject;

import javax.security.auth.Subject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//학기 정보 통신 인터페이스
public interface subjectFactory {
    @POST("/user")
    Call<JsonObject> postUser(@Body SubjectData subjectData);
}
