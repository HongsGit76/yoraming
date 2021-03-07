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
    //요람 통계 불러오는 메소드
    @GET("/subject/yoram")
    Call<JsonObject> getyoram(@Query("user_id") String email, @Query("yoram_id") String yoram_id);
    //시간표 정보 불러오는 메소드
    @GET("/subject/date")
    Call<JsonObject> getsemester(@Query("user_id") String email, @Query("yoram_id") String yoram_id);
    //학기추가 혹은 수정하는 메소
    @POST("/subject")
    Call<JsonObject> postsemester(@Body SubjectData subjectData);

}
