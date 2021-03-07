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
    final int rootID = 10000;
    final int childID = 20000;

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

        onCreateRootLayout(rootView,1);
        onCreateRootLayout(rootView, 2);
        onCreateRootLayout(rootView, 3);
        onCreateRootLayout(rootView, 4);
        onCreateRootLayout(rootView, 5);

        onCreateChildLayout(rootView,10001, 1,R.drawable.blue_rounded);
        onCreateChildLayout(rootView,10001, 2,R.drawable.yellow_rounded);
        onCreateChildLayout(rootView,10001, 3,R.drawable.orange_rounded);
        onCreateChildLayout(rootView,10001, 4,R.drawable.green_rounded);

        onCreateChildLayout(rootView,10002, 6,R.drawable.yellow_rounded);
        onCreateChildLayout(rootView,10002, 7,R.drawable.orange_rounded);
        onCreateChildLayout(rootView,10002, 8,R.drawable.green_rounded);

        onCreateChildLayout(rootView,10003, 6,R.drawable.yellow_rounded);
        onCreateChildLayout(rootView,10003, 7,R.drawable.orange_rounded);
        onCreateChildLayout(rootView,10003, 8,R.drawable.green_rounded);

        onCreateChildLayout(rootView,10004, 6,R.drawable.yellow_rounded);
        onCreateChildLayout(rootView,10004, 7,R.drawable.orange_rounded);
        onCreateChildLayout(rootView,10004, 8,R.drawable.green_rounded);

        onCreateChildLayout(rootView,10005, 6,R.drawable.yellow_rounded);
        onCreateChildLayout(rootView,10005, 7,R.drawable.orange_rounded);
        onCreateChildLayout(rootView,10005, 8,R.drawable.green_rounded);


        onCreateMajorText(rootView,20001, "전공");
        onCreateMajorText(rootView,20001, "전공");
        onCreateMajorText(rootView,20002, "전공");
        onCreateMajorText(rootView,20002, "전공");
        onCreateMajorText(rootView,20003, "전공");
        onCreateMajorText(rootView,20003, "전공");

        return rootView;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("key", 0);
        ((MainActivity)getActivity()).replaceFragment(new HomeFragment());
    }

    // 동적으로 학기 별 LinearLayout을 생성해주는 함수
    public void onCreateRootLayout(View rootView, int i) {
        FrameLayout frame_View = (FrameLayout)rootView.findViewById(R.id.frame_yoraming);
        LinearLayout ll = new LinearLayout(getActivity());
        LinearLayout.LayoutParams lpm = new LinearLayout.LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 95, getResources().getDisplayMetrics()),
                ViewGroup.LayoutParams.MATCH_PARENT);
        lpm.setMarginStart((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15 + ((i-1) * 110), getResources().getDisplayMetrics()));
        ll.setLayoutParams(lpm);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setId(rootID + i);
        frame_View.addView(ll);
    }

    // 동적으로 학기 별 과목 구분 LinearLayout을 생성해주는 함수
    public void onCreateChildLayout(@NotNull View rootView, int root_ID, int i, int lineColor) {
        LinearLayout root_View = (LinearLayout)rootView.findViewById(root_ID);
        LinearLayout ll = new LinearLayout((getActivity()));
        LinearLayout.LayoutParams lpm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics()));
        lpm.topMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        lpm.bottomMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        lpm.weight = 1;
        ll.setLayoutParams(lpm);
        ll.setBackgroundResource(lineColor);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setId(childID + i);
        root_View.addView(ll);
    }

    // 동적으로 전공 별 과목을 추가해주는 함수
    public void onCreateMajorText(View rootView, int parentID, String majorName) {
        LinearLayout parent_View = (LinearLayout)rootView.findViewById(parentID);
        TextView tv = new TextView(getActivity());
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,1f));
        tv.setText(majorName);
        parent_View.addView(tv);
    }

    // 학기 별 LinearLayout에 size를 설정해주는 함수
    public void setRootLayoutSize(View rootView) {
        FrameLayout root_View = (FrameLayout)rootView.findViewById(R.id.frame_yoraming);
        for(int i = 0; i < root_View.getChildCount(); i++) {
            LinearLayout child = (LinearLayout)root_View.getChildAt(i);
            LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 95, getResources().getDisplayMetrics()),
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, child.getChildCount() * 60, getResources().getDisplayMetrics()));
            child.setLayoutParams(pm);
        }
    }
}