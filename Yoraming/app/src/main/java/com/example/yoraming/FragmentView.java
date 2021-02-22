package com.example.yoraming;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import com.dinuscxj.progressbar.CircleProgressBar;

public class FragmentView extends Fragment implements CircleProgressBar.ProgressFormatter {

    private static final String DEFAULT_PATTERN = "%d%%";
    View.OnClickListener buttonlistener;
    View rootView;

    CircleProgressBar circleProgressBar1, circleProgressBar2, circleProgressBar3;
    int i, j, k = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_view, container, false);

        CreatePieGraph(rootView, 70, 50, 30);


        return rootView;
    }

    public void CreatePieGraph(View rootView, int per_1, int per_2, int per_3) {
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

    public void onCreateButton(String text, int imageId) {
        Button mButton = new Button(getActivity());
        ViewGroup.LayoutParams pm = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mButton.setText(text);
        mButton.setBackgroundResource(imageId);
        mButton.setLayoutParams(pm);
        mButton.setOnClickListener(buttonlistener);
        FrameLayout mView = (FrameLayout)rootView.findViewById(R.id.home_container);
        mView.addView(mButton);
    }
}