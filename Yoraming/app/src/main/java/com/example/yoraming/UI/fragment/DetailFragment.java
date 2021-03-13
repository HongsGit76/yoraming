package com.example.yoraming.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.example.yoraming.UI.activity.LoginActivity;
import com.example.yoraming.UI.activity.MainActivity;
import com.example.yoraming.OnBackPressedListener;
import com.example.yoraming.R;
import com.example.yoraming.detailAdapter;
import com.firebase.ui.auth.ErrorCodes;

import java.util.ArrayList;


public class DetailFragment extends Fragment implements CircleProgressBar.ProgressFormatter, OnBackPressedListener {

    private static final String DEFAULT_PATTERN = "%d%%";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    MainActivity activity;
    CircleProgressBar circleProgressBar1, circleProgressBar2, circleProgressBar3, circleProgressBar4;
    int i, j, k ,l= 0;
    private static ArrayList<item> itemArrayList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        activity = (MainActivity) getActivity();

        CreatePieGraph(rootView,50,40,30,20);

        //리사이클러뷰에 넣어줄 데이터
        itemArrayList = new ArrayList<>();
        itemArrayList.add(new item( 10,  0));
//        itemArrayList.add(new item(20,2));
//        itemArrayList.add(new item( 10,  1));
//        itemArrayList.add(new item(20,2));

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.spec_recycle_view);
        mRecyclerView.setHasFixedSize(true);//옵션
        //Linear layout manager 사용
        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //어답터 세팅
        mAdapter = new detailAdapter(this.activity,itemArrayList); //스트링 배열 데이터 인자로
        mRecyclerView.setAdapter(mAdapter);

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }

    public void CreatePieGraph(View rootView,int per_1, int per_2, int per_3, int per_4) {
        circleProgressBar1 = (CircleProgressBar) rootView.findViewById(R.id.cpb_detail_circlebar1);
        circleProgressBar1.setProgressFormatter(null);
        circleProgressBar2 = (CircleProgressBar) rootView.findViewById(R.id.cpb_detail_circlebar2);
        circleProgressBar2.setProgressFormatter(null);
        circleProgressBar3 = (CircleProgressBar) rootView.findViewById(R.id.cpb_detail_circlebar3);
        circleProgressBar3.setProgressFormatter(null);
        circleProgressBar4 = (CircleProgressBar) rootView.findViewById(R.id.cpb_detail_circlebar4);
        circleProgressBar4.setProgressFormatter(null);

        final Handler handler1 = new Handler();
        final Handler handler2 = new Handler();
        final Handler handler3 = new Handler();
        final Handler handler4 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i <= per_1) {
                    circleProgressBar1.setProgress(i);
                    i++;
                    handler1.postDelayed(this, 10);
                } else {
                    Log.d("handler1", "endrun");
                    handler1.removeCallbacks(this);
                }
            }
        }, 10);
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (j <= per_2) {
                    circleProgressBar2.setProgress(j);
                    j++;
                    handler2.postDelayed(this, 10);
                } else {
                    handler2.removeCallbacks(this);
                }
            }
        }, 10);

        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (k <= per_3) {
                    circleProgressBar3.setProgress(k);
                    k++;
                    handler3.postDelayed(this, 10);
                } else {
                    handler3.removeCallbacks(this);
                }
            }
        }, 10);

        handler4.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (l <= per_4) {
                    circleProgressBar4.setProgress(l);
                    l++;
                    handler4.postDelayed(this, 10);
                } else {
                    handler4.removeCallbacks(this);
                }
            }
        }, 10);

    }

    @Override
    public CharSequence format(int progress, int max) {

        return String.format(DEFAULT_PATTERN,  (int) ((float) progress / (float) max * 100));
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("key", 0);
        ((MainActivity)getActivity()).replaceFragment(new HomeFragment());
    }
    public static class item {
        int age;
        int itemViewType;

        public item( int age,int itemViewType) {
            this.age = age;
            this.itemViewType = itemViewType;
        }


        public int getAge() {
            return age;
        }
        public int getItemViewType() {
            return itemViewType;
        }
    }
}