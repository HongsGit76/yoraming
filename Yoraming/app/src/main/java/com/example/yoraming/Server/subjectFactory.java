package com.example.yoraming.Server;

import com.example.yoraming.items.SubjectData;
import com.google.gson.JsonObject;

import javax.security.auth.Subject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//학기 정보 통신 인터페이스
public interface subjectFactory {
    @GET("/subject/yoram")
    Call<JsonObject> getyoram(@Query("user_id") String email, @Query("yoram_id") String yoram_id);
}
