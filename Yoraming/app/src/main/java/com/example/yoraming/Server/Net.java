package com.example.yoraming.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Net {
    private static Net ourinstance = new Net();

    public static Net getInstance(){
        return ourinstance;
    }

    Gson gson = new GsonBuilder().setLenient().create();
    private Net(){
    }
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor()).build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://13.58.240.144:3000") //<= 서버 주소값 입력
            .client(client) //<= OkHttpClient 연동
            .addConverterFactory(ScalarsConverterFactory.create()) //<= 고질적인 에러 :JSON document was not fully consumed 해결
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    //각각의 정보에 대한 서버 호출 인터페이스 선언
    noticeFactory noticeFactory;
    specFactory specFactory;
    subjectFactory subjectFactory;
    suggestionFactory suggestionFactory;
    userFactory userFactory;
    yoramFactory yoramFactory;

    // 각각의 인터페이스 객체를 받는 메소드 선언
    public noticeFactory getnoticeFactory(){
        if(noticeFactory == null){
            noticeFactory = retrofit.create(noticeFactory.class);
        }
        return noticeFactory;
    }

    public specFactory getspecFactory(){
        if(specFactory == null){
            specFactory = retrofit.create(specFactory.class);
        }
        return specFactory;
    }

    public subjectFactory getsubjectFactory(){
        if(subjectFactory == null){
            subjectFactory = retrofit.create(subjectFactory.class);
        }
        return subjectFactory;
    }

    public suggestionFactory getsuggestionFactory(){
        if(suggestionFactory == null){
            suggestionFactory = retrofit.create(suggestionFactory.class);
        }
        return suggestionFactory;
    }

    public userFactory getuserFactory(){
        if(userFactory == null){
            userFactory = retrofit.create(userFactory.class);
        }
        return userFactory;
    }

    public yoramFactory getyoramFactory(){
        if(noticeFactory == null){
            yoramFactory = retrofit.create(yoramFactory.class);
        }
        return yoramFactory;
    }
    private HttpLoggingInterceptor httpLoggingInterceptor(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                android.util.Log.e("MyLogis :", message + "");
            }
        });

        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
