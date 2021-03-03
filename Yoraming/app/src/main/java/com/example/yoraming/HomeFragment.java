package com.example.yoraming;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.dinuscxj.progressbar.CircleProgressBar;

public class HomeFragment extends Fragment implements CircleProgressBar.ProgressFormatter, OnBackPressedListener{

    private static final String DEFAULT_PATTERN = "%d%%";
    private ImageButton add_major;
    private Button baseMajor;
    private Toast toast;
    long backKeyPressedTime;
    MainActivity activity;
    View.OnClickListener buttonlistener;
    CircleProgressBar circleProgressBar1, circleProgressBar2, circleProgressBar3;
    public UserDataHandler userData = UserDataHandler.getInstance();
    public Subject currentSubject= null;
    private String majorName;
    private int major;
    private int basic;
    static int cnt = 1;
    int i, j, k = 0;

    public HomeFragment() {
        Subject subject = new Subject();
        subject.setMajorName("디지털미디어");
        subject.setMajor(50);
        subject.setBasic(50);
        userData.subjectList.add(subject);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        activity = (MainActivity) getActivity();
        toast = Toast.makeText(getContext(), "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        add_major = (ImageButton)rootView.findViewById(R.id.add_major);
        baseMajor = (Button)rootView.findViewById(R.id.select_major_base);
        currentSubject = userData.subjectList.get(0);
        baseMajor.setText(currentSubject.getMajorName());
        baseMajor.setTextSize(10.0f);
        CreatePieGraph(rootView,currentSubject.getTotal(),currentSubject.getBasic(),currentSubject.getMajor());

        add_major.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSubject = userData.subjectList.get(cnt);
                onCreateButton(rootView,currentSubject.getMajorName(),R.drawable.not_selected_major);
                cnt = cnt + 1;
            }
        });
        return rootView;
    }

    public void CreatePieGraph(View rootView,int per_1, int per_2, int per_3) {
        circleProgressBar1 = (CircleProgressBar) rootView.findViewById(R.id.cpb_circlebar1);
        circleProgressBar1.setProgressFormatter(null);
        circleProgressBar2 = (CircleProgressBar) rootView.findViewById(R.id.cpb_circlebar2);
        circleProgressBar2.setProgressFormatter(null);
        circleProgressBar3 = (CircleProgressBar) rootView.findViewById(R.id.cpb_circlebar3);
        circleProgressBar3.setProgressFormatter(null);

        final Handler handler1 = new Handler();
        final Handler handler2 = new Handler();
        final Handler handler3 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i <= per_1) {
                    circleProgressBar1.setProgress(i);
                    i++;
                    handler1.postDelayed(this, 10);
                } else {
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

    }

    @Override
    public CharSequence format(int progress, int max) {
        return String.format(DEFAULT_PATTERN,  (int) ((float) progress / (float) max * 100));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void onCreateButton(View rootView, String text, int imageId) {
        setLayout(rootView);
        setAddButton(rootView);
        Button mButton = new Button(getActivity());
        LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 23, getResources().getDisplayMetrics()));
        pm.weight = 1;
        mButton.setText(text);
        mButton.setTextSize(10.0f);
        mButton.setBackgroundResource(imageId);
        mButton.setLayoutParams(pm);
        mButton.setOnClickListener(buttonlistener);
        LinearLayout mView = (LinearLayout) rootView.findViewById(R.id.major_button_group);
        mView.addView(mButton);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setLayout(View rootView) {
        LinearLayout linearLayout = (LinearLayout)rootView.findViewById(R.id.major_button_group);

        ConstraintLayout.LayoutParams pm_layout = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();

        if(linearLayout.getPaddingRight() < (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 71, getResources().getDisplayMetrics())) {
            linearLayout.setPadding(linearLayout.getPaddingLeft(),linearLayout.getPaddingTop(),(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics()),
                    linearLayout.getPaddingBottom());
        } else {
            linearLayout.setPadding(linearLayout.getPaddingLeft(),linearLayout.getPaddingTop(),linearLayout.getPaddingRight() - (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()),
                    linearLayout.getPaddingBottom());
        }
        linearLayout.setLayoutParams(pm_layout);
    }

    public void setAddButton(View rootView) {
        ImageButton mButton = (ImageButton)rootView.findViewById(R.id.add_major);

        ConstraintLayout.LayoutParams pm_button = (ConstraintLayout.LayoutParams) mButton.getLayoutParams();

        if(pm_button.getMarginEnd() < (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 46, getResources().getDisplayMetrics())) {
            pm_button.setMarginEnd((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics()));
        } else {
            pm_button.setMarginEnd(pm_button.getMarginEnd() - (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()));
        }

        mButton.setLayoutParams(pm_button);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("key", 0);

        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast.show();
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            getActivity().finishAffinity();
            toast.cancel();
        }
    }
}