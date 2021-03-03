package com.example.yoraming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainMajorActivity extends AppCompatActivity {

    private Button btn_main_major;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_major);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 리스트에 검색될 데이터(단어)를 추가한다.
        settingList();

        final AutoCompleteTextView auto_major = (AutoCompleteTextView) findViewById(R.id.auto_major);

        // AutoCompleteTextView 에 아답터를 연결한다.
        auto_major.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,  list ));

        btn_main_major = (Button)findViewById(R.id.btn_main_major);
        btn_main_major.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMajorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void settingList(){
        list.add("기계공학전공");
        list.add("산업공학전공");
        list.add("화학공학전공");
        list.add("신소재공학전공");
        list.add("응용화학생명공학전공");
        list.add("환경안전공학전공");
        list.add("건설시스템공학전공");
        list.add("교통시스템공학전공");
        list.add("건축학전공(5년)");
        list.add("건축공학전공");
        list.add("전자공학전공");
        list.add("소프트웨어및컴퓨터공학전공");
        list.add("사이버보안전공");
        list.add("디지털미디어전공");
        list.add("공군ICT전공");
        list.add("ICT융합전공");
        list.add("글로벌IT전공");
        list.add("인공지능융합전공");
        list.add("수학전공");
        list.add("화학전공");
        list.add("물리학전공");
        list.add("생명과학전공");
        list.add("e-비즈니스학전공");
        list.add("금융공학전공");
        list.add("글로벌경영학전공");
        list.add("국어국문학전공");
        list.add("영어영문학전공");
        list.add("불어불문학전공");
        list.add("사학전공");
        list.add("문화콘텐츠학전공");
        list.add("경제학전공");
        list.add("행정학전공");
        list.add("심리학전공");
        list.add("사회학전공");
        list.add("정치외교학학전공");
        list.add("스포츠레저학전공");
        list.add("의학전공");
        list.add("간호학전공");
        list.add("간호학전공(특별과정)");
        list.add("약학전공");
        list.add("한국학전공");
        list.add("국제통상전공");
        list.add("문화산업과커뮤니케이션전공");
        list.add("중국지역연구전공");
        list.add("일본지역연구전공");
        list.add("인문사회데이터분석전공");
        list.add("융합시스템공학전공");
        list.add("자동차SW전공");
    }
}