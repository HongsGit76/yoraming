package com.example.yoraming.Server;

import com.example.yoraming.items.NoticeData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

//공지 정보 통신 인터페이스
public interface noticeFactory {
    @GET("/notice")
    Call<JsonObject> getNotice(@Query("user_id") String email);

    @POST("/notice")
    Call<JsonObject> postNotice(@Body NoticeData notice);

    @DELETE("/notice")
    Call<JsonObject> deleteNotice(@Query("yoram_id") String notice_id);

}
