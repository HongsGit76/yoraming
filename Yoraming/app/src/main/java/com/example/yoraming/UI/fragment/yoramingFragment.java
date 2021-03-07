package com.example.yoraming.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.yoraming.OnBackPressedListener;
import com.example.yoraming.R;
import com.example.yoraming.UI.activity.LoginActivity;
import com.example.yoraming.UI.activity.MainActivity;

import org.jetbrains.annotations.NotNull;

public class yoramingFragment extends Fragment implements OnBackPressedListener {

    View rootView;
    HorizontalScrollView scrollView;
    //final int rootID = 10000;
    //final int childID = 20000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_yoraming, container, false);
        scrollView = (HorizontalScrollView) rootView.findViewById(R.id.scrollView);
        scrollView.setHorizontalScrollBarEnabled(false);

        onCreateRootLayout(rootView,"G11",1);
        onCreateRootLayout(rootView, "G12",2);
        onCreateRootLayout(rootView, "G13",3);
        onCreateRootLayout(rootView, "G14",4);
        onCreateRootLayout(rootView, "G21",5);

        onCreateChildLayout(rootView,"G11","Major",R.drawable.blue_rounded);
        onCreateChildLayout(rootView,"G11","yellow",R.drawable.yellow_rounded);
        onCreateChildLayout(rootView,"G11","orange",R.drawable.orange_rounded);
        onCreateChildLayout(rootView,"G11","green",R.drawable.green_rounded);

        onCreateMajorText(rootView,"Major","전공");
        onCreateMajorText(rootView,"Major","전공");
        onCreateMajorText(rootView,"yellow","전공");
        onCreateMajorText(rootView,"yellow","전공");
        onCreateMajorText(rootView,"yellow","전공");
        onCreateMajorText(rootView,"orange","전공");
        onCreateMajorText(rootView,"orange","전공");
        onCreateMajorText(rootView,"orange","전공");

        setRootLayoutSize(rootView, "G11");
        setChildLayoutSize(rootView,"Major");
        setChildLayoutSize(rootView,"yellow");
        setChildLayoutSize(rootView,"orange");
        setChildLayoutSize(rootView,"green");
        return rootView;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("key", 0);
        ((MainActivity)getActivity()).replaceFragment(new HomeFragment());
    }

    // 동적으로 학기 별 LinearLayout을 생성해주는 함수
    public void onCreateRootLayout(View rootView, String tag, int i) {
        FrameLayout frame_View = (FrameLayout)rootView.findViewById(R.id.frame_yoraming);
        LinearLayout ll = new LinearLayout(getActivity());
        LinearLayout.LayoutParams lpm = new LinearLayout.LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 95, getResources().getDisplayMetrics()),
                ViewGroup.LayoutParams.MATCH_PARENT);
        lpm.setMarginStart((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15 + ((i-1) * 110), getResources().getDisplayMetrics()));
        ll.setLayoutParams(lpm);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setTag(tag);
        frame_View.addView(ll);
    }

    // 동적으로 학기 별 과목 구분 LinearLayout을 생성해주는 함수
    public void onCreateChildLayout(@NotNull View rootView, String root_tag, String tag, int lineColor) {
        LinearLayout root_View = (LinearLayout)rootView.findViewWithTag(root_tag);
        LinearLayout ll = new LinearLayout((getActivity()));
        LinearLayout.LayoutParams lpm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics()));
        lpm.topMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        lpm.bottomMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        lpm.weight = 1;
        ll.setLayoutParams(lpm);
        ll.setBackgroundResource(lineColor);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setTag(tag);
        root_View.addView(ll);
    }

    // 동적으로 전공 별 과목을 추가해주는 함수
    public void onCreateMajorText(View rootView,String parentTag, String majorName) {
        LinearLayout parent_View = (LinearLayout)rootView.findViewWithTag(parentTag);
        TextView tv = new TextView(getActivity());
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,1f));
        tv.setText(majorName);
        parent_View.addView(tv);
    }

    // 학기 별 LinearLayout에 height를 설정해주는 함수
    public void setRootLayoutSize(View rootView, String tag) {
        LinearLayout root_View = (LinearLayout) rootView.findViewWithTag(tag);
        ViewGroup.LayoutParams pm = (ViewGroup.LayoutParams)root_View.getLayoutParams();
        pm.height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 125 * root_View.getChildCount(), getResources().getDisplayMetrics());
        root_View.setLayoutParams(pm);
    }

    // 전공 별 LinearLayout에 가중치를 변경해 height 비율을 맞춰주는 함수
    public void setChildLayoutSize(View rootView, String tag) {
        LinearLayout parent_View = (LinearLayout) rootView.findViewWithTag(tag);
        LinearLayout.LayoutParams pm = (LinearLayout.LayoutParams)parent_View.getLayoutParams();
        pm.weight = parent_View.getChildCount() * 1;
        parent_View.setLayoutParams(pm);
    }
}