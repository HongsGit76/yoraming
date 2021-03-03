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
    private List<MajorItem> majorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_major);
        // 리스트에 검색될 데이터(단어)를 추가한다.
        settingList();

        AutoCompleteTextView auto_major = (AutoCompleteTextView) findViewById(R.id.auto_major);

        // 커스텀 어댑터 생성
        AutoCompleteAdapter adapter = new AutoCompleteAdapter(this, majorList);
        auto_major.setAdapter(adapter);

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
        majorList = new ArrayList<>();
        majorList.add(new MajorItem("기계공학전공"));
        majorList.add(new MajorItem("산업공학전공"));
        majorList.add(new MajorItem("화학공학전공"));
        majorList.add(new MajorItem("신소재공학전공"));
        majorList.add(new MajorItem("응용화학생명공학전공"));
        majorList.add(new MajorItem("환경안전공학전공"));
        majorList.add(new MajorItem("건설시스템공학전공"));
        majorList.add(new MajorItem("교통시스템공학전공"));
        majorList.add(new MajorItem("건축학전공(5년)"));
        majorList.add(new MajorItem("건축공학전공"));
        majorList.add(new MajorItem("전자공학전공"));
        majorList.add(new MajorItem("소프트웨어및컴퓨터공학전공"));
        majorList.add(new MajorItem("사이버보안전공"));
        majorList.add(new MajorItem("디지털미디어전공"));
        majorList.add(new MajorItem("공군ICT전공"));
        majorList.add(new MajorItem("ICT융합전공"));
        majorList.add(new MajorItem("글로벌IT전공"));
        majorList.add(new MajorItem("인공지능융합전공"));
        majorList.add(new MajorItem("수학전공"));
        majorList.add(new MajorItem("화학전공"));
        majorList.add(new MajorItem("물리학전공"));
        majorList.add(new MajorItem("생명과학전공"));
        majorList.add(new MajorItem("e-비즈니스학전공"));
        majorList.add(new MajorItem("금융공학전공"));
        majorList.add(new MajorItem("글로벌경영학전공"));
        majorList.add(new MajorItem("국어국문학전공"));
        majorList.add(new MajorItem("영어영문학전공"));
        majorList.add(new MajorItem("불어불문학전공"));
        majorList.add(new MajorItem("사학전공"));
        majorList.add(new MajorItem("문화콘텐츠학전공"));
        majorList.add(new MajorItem("경제학전공"));
        majorList.add(new MajorItem("행정학전공"));
        majorList.add(new MajorItem("심리학전공"));
        majorList.add(new MajorItem("사회학전공"));
        majorList.add(new MajorItem("정치외교학학전공"));
        majorList.add(new MajorItem("스포츠레저학전공"));
        majorList.add(new MajorItem("의학전공"));
        majorList.add(new MajorItem("간호학전공"));
        majorList.add(new MajorItem("간호학전공(특별과정)"));
        majorList.add(new MajorItem("약학전공"));
        majorList.add(new MajorItem("한국학전공"));
        majorList.add(new MajorItem("국제통상전공"));
        majorList.add(new MajorItem("문화산업과커뮤니케이션전공"));
        majorList.add(new MajorItem("중국지역연구전공"));
        majorList.add(new MajorItem("일본지역연구전공"));
        majorList.add(new MajorItem("인문사회데이터분석전공"));
        majorList.add(new MajorItem("융합시스템공학전공"));
        majorList.add(new MajorItem("자동차SW전공"));
    }
}