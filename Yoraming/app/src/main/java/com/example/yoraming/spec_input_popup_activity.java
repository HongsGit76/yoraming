package com.example.yoraming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class spec_input_popup_activity extends Activity {
    TextView tv_add_spec_title,tv_spec_content_1,tv_spec_content_2,tv_spec_content_3;
    EditText et_spec_content_1, et_spec_content_2, et_spec_content_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_spec_input_popup_activity);


        Intent intent = getIntent();
        String spec_type = intent.getStringExtra("spec_type");

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

            tv_spec_content_1.setText("내용");
            tv_spec_content_2.setText("기간");
            tv_spec_content_3.setVisibility(View.GONE);
            et_spec_content_3.setVisibility(View.GONE);

            //tv_spec_content_3.setText("");

        }

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