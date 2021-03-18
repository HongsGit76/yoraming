package com.example.yoraming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yoraming.Server.Net;
import com.example.yoraming.items.SpecCareerData;
import com.example.yoraming.items.SpecCertificateData;
import com.example.yoraming.items.SpecEtcData;
import com.example.yoraming.items.SpecLanguageData;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class spec_input_popup_activity extends Activity {
    private TextView tv_add_spec_title,tv_spec_content_1,tv_spec_content_2,tv_spec_content_3;
    private EditText et_spec_content_1, et_spec_content_2, et_spec_content_3;
    private int textlength;
    private String spec_type, user_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_spec_input_popup_activity);
        SharedPreferences SP_user = getSharedPreferences("user", MODE_PRIVATE);
        user_ID = SP_user.getString("user_id", "");

        Intent intent = getIntent();
        spec_type = intent.getStringExtra("spec_type");

        tv_add_spec_title = (TextView)findViewById(R.id.tv_add_spec_title);
        tv_spec_content_1 = (TextView)findViewById(R.id.tv_spec_content_1);
        tv_spec_content_2 = (TextView)findViewById(R.id.tv_spec_content_2);
        tv_spec_content_3 = (TextView)findViewById(R.id.tv_spec_content_3);

        et_spec_content_1 = (EditText)findViewById(R.id.et_spec_content_1);
        et_spec_content_2 = (EditText)findViewById(R.id.et_spec_content_2);
        et_spec_content_3 = (EditText)findViewById(R.id.et_spec_content_3);

        //각각의 스펙 종류에 따라 뷰를 나눔
        if (spec_type.equals("certificate")){
            tv_add_spec_title.setText("자격증 추가");

            tv_spec_content_1.setText("자격증명");
            tv_spec_content_2.setText("취득일");
            tv_spec_content_3.setText("자격증 기한");
            et_spec_content_2.setHint("ex) 2021.01.01");
            // 최대 길이 설정
            InputFilter[] FilterArray = new InputFilter[1];
            FilterArray[0] = new InputFilter.LengthFilter(10);
            et_spec_content_2.setFilters(FilterArray);
            et_spec_content_2.setInputType(InputType.TYPE_CLASS_NUMBER);
            et_spec_content_2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(et_spec_content_2.isFocusable() && !s.toString().equals("")) {
                        try{
                            textlength = et_spec_content_2.getText().toString().length();
                        }catch (NumberFormatException e){
                            e.printStackTrace();
                            return;
                        }

                        if (textlength == 4 && before != 1) {
                            et_spec_content_2.setText(et_spec_content_2.getText().toString()+".");
                            et_spec_content_2.setSelection(et_spec_content_2.getText().length());
                        }else if (textlength == 7&& before != 1){
                            et_spec_content_2.setText(et_spec_content_2.getText().toString()+".");
                            et_spec_content_2.setSelection(et_spec_content_2.getText().length());
                        }else if(textlength == 5 && !et_spec_content_2.getText().toString().contains(".")){
                            et_spec_content_2.setText(et_spec_content_2.getText().toString().substring(0,4)+"."+et_spec_content_2.getText().toString().substring(4));
                            et_spec_content_2.setSelection(et_spec_content_2.getText().length());
                        }else if(textlength == 8 && !et_spec_content_2.getText().toString().substring(7,8).equals(".")){
                            et_spec_content_2.setText(et_spec_content_2.getText().toString().substring(0,7)+"."+et_spec_content_2.getText().toString().substring(7));
                            et_spec_content_2.setSelection(et_spec_content_2.getText().length());
                        }
                    }


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }else if(spec_type.equals("career")){
            tv_add_spec_title.setText("경력사항 추가");

            tv_spec_content_1.setText("업무내용");
            tv_spec_content_2.setText("기관명");
            tv_spec_content_3.setText("기간");

        }else if(spec_type.equals("language")){
            tv_add_spec_title.setText("외국어 공인 성적 추가");

           tv_spec_content_1.setText("시험명");
            tv_spec_content_2.setText("성적");
            tv_spec_content_3.setText("시험 날짜");

        }else if(spec_type.equals("etc")){
            tv_add_spec_title.setText("기타 추가");

            tv_spec_content_1.setText("제목");
            tv_spec_content_2.setText("내용");
            tv_spec_content_3.setText("기간");

        }
        et_spec_content_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(et_spec_content_3.isFocusable() && !s.toString().equals("")) {
                    try{
                        textlength = et_spec_content_3.getText().toString().length();
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                        return;
                    }

                    if (textlength == 4 && before != 1) {
                        et_spec_content_3.setText(et_spec_content_3.getText().toString()+".");
                        et_spec_content_3.setSelection(et_spec_content_3.getText().length());
                    }else if (textlength == 7&& before != 1){
                        et_spec_content_3.setText(et_spec_content_3.getText().toString()+".");
                        et_spec_content_3.setSelection(et_spec_content_3.getText().length());
                    }else if(textlength == 5 && !et_spec_content_3.getText().toString().contains(".")){
                        et_spec_content_3.setText(et_spec_content_3.getText().toString().substring(0,4)+"."+et_spec_content_3.getText().toString().substring(4));
                        et_spec_content_3.setSelection(et_spec_content_3.getText().length());
                    }else if(textlength == 8 && !et_spec_content_3.getText().toString().substring(7,8).equals(".")){
                        et_spec_content_3.setText(et_spec_content_3.getText().toString().substring(0,7)+"."+et_spec_content_3.getText().toString().substring(7));
                        et_spec_content_3.setSelection(et_spec_content_3.getText().length());
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }
    public void mOnSend(View v){
        Call<JsonObject> res = null;

    if(spec_type.equals("certificate")){
        SpecCertificateData specCertificateData = new SpecCertificateData(user_ID,et_spec_content_1.getText().toString(),et_spec_content_2.getText().toString(),et_spec_content_3.getText().toString());
        res = Net.getInstance().getspecFactory().postcertificate(specCertificateData);
    }else if(spec_type.equals("career")){
        SpecCareerData specCareerData = new SpecCareerData(user_ID,et_spec_content_1.getText().toString(),et_spec_content_2.getText().toString(),et_spec_content_3.getText().toString());
        res = Net.getInstance().getspecFactory().postcareer(specCareerData);
    }else if(spec_type.equals("language")){
        SpecLanguageData specLanguageData = new SpecLanguageData(user_ID,et_spec_content_1.getText().toString(),et_spec_content_2.getText().toString(),et_spec_content_3.getText().toString());
        res = Net.getInstance().getspecFactory().postlanguage(specLanguageData);
    }else if(spec_type.equals("etc")){
        SpecEtcData specEtcData = new SpecEtcData(user_ID,et_spec_content_1.getText().toString(),et_spec_content_2.getText().toString(),et_spec_content_3.getText().toString());
        res = Net.getInstance().getspecFactory().postetc(specEtcData);

    }
    res.enqueue(new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if(response.isSuccessful()){
                if(response.body() != null){
                    String success = response.body().toString();
                    Log.d("spec_input_popup 통신", response.body().toString());
                }else{
                    Log.e("spec_input_popup 통신", "실패 1 response 내용이 없음");
                }
            }else{
                Log.e("spec_input_popup 통신", "실패 2 서버 에러");
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
            Log.e("spec_input_popup 통신", "실패 3 통신 에러 "+t.getLocalizedMessage());
        }
    });

        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}