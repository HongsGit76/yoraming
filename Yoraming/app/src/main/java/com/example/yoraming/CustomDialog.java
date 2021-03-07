package com.example.yoraming;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.yoraming.UI.activity.MainMajorActivity;
import com.example.yoraming.items.MajorItem;

import java.util.ArrayList;
import java.util.List;

public class CustomDialog extends DialogFragment {

    private Button btn_xbutton, btn_ok;
    private EditText auto_major, totalR, majorR, majorS, univR, basicR;
    private CustomDialogListener customDialogListener;
    private List<MajorItem> majorList;


    public interface CustomDialogListener {
        void onPositiveClicked(String add_auto_major, String add_totalR, String add_majorR, String add_majorS, String add_univR, String add_basic);
        void onNegativeClicked();
    }

    public void setDialogListener(CustomDialogListener customDialogListener){
        this.customDialogListener = customDialogListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_dialog, container, false);

        settingList();

        AutoCompleteTextView auto_major = (AutoCompleteTextView) view.findViewById(R.id.auto_major);

        AutoCompleteAdapter adapter = new AutoCompleteAdapter(getActivity(), majorList);
        auto_major.setAdapter(adapter);

        btn_xbutton = (Button) view.findViewById(R.id.btn_xbutton);
        btn_ok = (Button) view.findViewById(R.id.btn_ok);

        btn_xbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogListener.onNegativeClicked();
                getDialog().cancel();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalR = (EditText) view.findViewById(R.id.totalR);
                majorR = (EditText) view.findViewById(R.id.majorR);
                majorS = (EditText) view.findViewById(R.id.majorS);
                univR = (EditText) view.findViewById(R.id.univR);
                basicR = (EditText) view.findViewById(R.id.basicR);

                String add_auto_major = auto_major.getText().toString();
                String add_totalR = totalR.getText().toString();
                String add_majorR = majorR.getText().toString();
                String add_majorS = majorS.getText().toString();
                String add_univR = univR.getText().toString();
                String add_basicR = basicR.getText().toString();

                customDialogListener.onPositiveClicked(add_auto_major, add_totalR, add_majorR, add_majorS, add_univR, add_basicR);
                getDialog().cancel();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        getDialog().getWindow().setLayout(900, 1200);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        super.onResume();
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
