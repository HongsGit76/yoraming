package com.example.yoraming.UI.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dinuscxj.progressbar.CircleProgressBar;
import com.example.yoraming.CustomDialog;
import com.example.yoraming.Server.Net;
import com.example.yoraming.UI.activity.LoginActivity;
import com.example.yoraming.UI.activity.MainActivity;
import com.example.yoraming.OnBackPressedListener;
import com.example.yoraming.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment implements CircleProgressBar.ProgressFormatter, OnBackPressedListener {

    private static final String DEFAULT_PATTERN = "%d%%";
    private ImageButton add_major, imageButton1, imageButton2, imageButton3;
    private Button baseMajor;
    private Toast toast;
    private int num = 1;
    private TextView number_all, number_major, number_general;
    long backKeyPressedTime;
    ArrayList<String> majorList = new ArrayList<>();
    ArrayList<Button> buttonArrayList = new ArrayList<Button>();
    MainActivity activity;
    String majorR, majorS, univR, basicR;
    View.OnClickListener buttonlistener;
    CircleProgressBar circleProgressBar1, circleProgressBar2, circleProgressBar3;
    int i, j, k = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mainMajor", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String text = baseMajor.getText().toString();
        editor.putString("mainMajor", text);

        editor.commit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageButton1 = (ImageButton)getActivity().findViewById(R.id.imageButton1);
        imageButton2 = (ImageButton)getActivity().findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton)getActivity().findViewById(R.id.imageButton3);

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onclick", "success");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                HomeChildFragment1 homeChildFragment1 = new HomeChildFragment1();

                //프레그먼트끼리 데이터 넘기기 위한 bundle
                Bundle bundle = new Bundle();
                bundle.putString("majorR", majorR);
                bundle.putString("majorS", majorS);

                homeChildFragment1.setArguments(bundle);
                transaction.replace(R.id.spe_framelayout, homeChildFragment1);
                transaction.commit();
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("onclick", "success");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                HomeChildFragment2 homeChildFragment2 = new HomeChildFragment2();

                //프레그먼트끼리 데이터 넘기기 위한 bundle
                Bundle bundle = new Bundle();
                bundle.putString("univR", univR);
                bundle.putString("basicR", basicR);

                homeChildFragment2.setArguments(bundle);
                transaction.replace(R.id.spe_framelayout, homeChildFragment2);
                transaction.commit();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        activity = (MainActivity) getActivity();
        toast = Toast.makeText(getContext(), "'뒤로' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        add_major = (ImageButton)rootView.findViewById(R.id.add_major);
        baseMajor = (Button)rootView.findViewById(R.id.select_major_base);

        SharedPreferences SP_user = getActivity().getSharedPreferences("user", MODE_PRIVATE);
        String user = SP_user.getString("user_id","");

        CreatePieGraph(rootView,50,30,80);


        Call<JsonObject> res1 = Net.getInstance().getyoramFactory().getYoram(user);
        res1.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        JsonObject success = response.body();
                        Log.d("homeFragment 통신", success.get("success").toString());
                        Log.d("homeFragment 통신", success.get("yoram").toString());
                        if (success.get("success").toString().equals("true")) {
                            JsonArray jsonArray = success.getAsJsonArray("yoram");
                            JsonElement jsonElement = jsonArray.get(0);

                            String yoram_major = jsonElement.getAsJsonObject().get("yoram_major").getAsString();
                            String yoram_total = jsonElement.getAsJsonObject().get("yoram_total").getAsString();
                            String yoram_majorR = jsonElement.getAsJsonObject().get("yoram_majorR").getAsString();
                            String yoram_majorS = jsonElement.getAsJsonObject().get("yoram_majorS").getAsString();
                            String yoram_univR = jsonElement.getAsJsonObject().get("yoram_univR").getAsString();
                            String yoram_basicR = jsonElement.getAsJsonObject().get("yoram_basicR").getAsString();

                            Log.d("homeFragment 통신", yoram_major);
                            baseMajor.setText(yoram_major);
                            majorList.add(baseMajor.getText().toString());
                            baseMajor.setPadding(15,0,15,0);
                            number_all = (TextView) getActivity().findViewById(R.id.number_all);
                            number_major = (TextView) getActivity().findViewById(R.id.number_major);
                            number_general = (TextView) getActivity().findViewById(R.id.number_general);

                            int int_total = Integer.parseInt(yoram_total);
                            majorR = yoram_majorR;
                            majorS = yoram_majorS;
                            univR = yoram_univR;
                            basicR = yoram_basicR;
                            int int_major = Integer.parseInt(yoram_majorR) + Integer.parseInt(yoram_majorS);
                            int int_notMajor = Integer.parseInt(yoram_univR) + Integer.parseInt(yoram_basicR);
                            String str_major = Integer.toString(int_major);
                            String str_notMajor = Integer.toString(int_notMajor);

                            number_all.append(yoram_total);
                            number_major.append(str_major);
                            number_general.append(str_notMajor);
                        }else{
                            Toast.makeText(getActivity().getApplicationContext(),"다시 시도해주세요",Toast.LENGTH_SHORT);
                        }
                    }else{
                        Log.e("MainMajor 통신", "실패 1 response 내용이 없음");
                    }
                }else{
                    Log.e("MainMajor 통신", "실패 2 서버 에러");
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("MainMajor 통신", "실패 3 통신 에러 "+t.getLocalizedMessage());
            }
        });
        add_major.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialog = new CustomDialog();
                dialog.show(getFragmentManager(), "customDialog");
                dialog.setDialogListener(new CustomDialog.CustomDialogListener() {
                    @Override
                    public void onPositiveClicked(String add_auto_major, String add_totalR, String add_majorR,
                                                  String add_majorS, String add_univR, String add_basicR) {
                        if (majorList.contains(add_auto_major)) {
                            Toast.makeText(activity, "중복된 학과입니다.", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            majorList.add(add_auto_major);
                            onCreateButton(rootView, add_auto_major, R.drawable.not_selected_major);
                            num++;
                        }
                    }
                    @Override
                    public void onNegativeClicked() {
                    }
                });
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
        mButton.setId(num);
        mButton.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        buttonArrayList.add(mButton);

        Log.d("buttonArrayList", Integer.toString(buttonArrayList.size()));
        Log.d("num", Integer.toString(num));
        LinearLayout.LayoutParams pm = new LinearLayout.LayoutParams((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics()),
                (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()));
        pm.weight = 1;
        mButton.setText(text);
        mButton.setPadding(15,0,15,0);
        mButton.setSingleLine(true);
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(mButton, 9, 11, 1, TypedValue.COMPLEX_UNIT_SP);
        mButton.setBackgroundResource(imageId);
        mButton.setLayoutParams(pm);
        mButton.setOnClickListener(buttonlistener);
        LinearLayout mView = (LinearLayout) rootView.findViewById(R.id.major_button_group);
        mView.addView(mButton);

        switch(num) {
            case 0:
                baseMajor.setSelected(true);
                break;
            case 1:
                buttonArrayList.get(0).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonArrayList.get(0).setSelected(true);
                        baseMajor.setSelected(false);
                        buttonArrayList.get(0).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_major));
                        baseMajor.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        Log.d("전공 2개일 때", "클릭");
                    }
                });
                baseMajor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baseMajor.setSelected(true);
                        buttonArrayList.get(0).setSelected(false);
                        baseMajor.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_major));
                        buttonArrayList.get(0).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));

                    }
                });
                break;
            case 2:
                buttonArrayList.get(0).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonArrayList.get(0).setSelected(true);
                        buttonArrayList.get(1).setSelected(false);
                        baseMajor.setSelected(false);
                        buttonArrayList.get(0).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_major));
                        buttonArrayList.get(1).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        baseMajor.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        Log.d("전공 3개일때 ", "클릭");
                    }
                });
                buttonArrayList.get(1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonArrayList.get(0).setSelected(false);
                        buttonArrayList.get(1).setSelected(true);
                        baseMajor.setSelected(false);
                        buttonArrayList.get(0).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        buttonArrayList.get(1).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_major));
                        baseMajor.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        Log.d("전공 3개일때 ", "클릭");
                    }
                });
                baseMajor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonArrayList.get(0).setSelected(false);
                        buttonArrayList.get(1).setSelected(false);
                        baseMajor.setSelected(true);
                        baseMajor.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_major));
                        buttonArrayList.get(0).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        buttonArrayList.get(1).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                    }
                });
                break;

            case 3:
                buttonArrayList.get(0).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonArrayList.get(0).setSelected(true);
                        buttonArrayList.get(1).setSelected(false);
                        buttonArrayList.get(2).setSelected(false);
                        baseMajor.setSelected(false);
                        buttonArrayList.get(0).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_major));
                        buttonArrayList.get(1).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        buttonArrayList.get(2).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        baseMajor.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        Log.d("전공 3개일때 ", "클릭");
                    }
                });
                buttonArrayList.get(1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonArrayList.get(0).setSelected(false);
                        buttonArrayList.get(1).setSelected(true);
                        buttonArrayList.get(2).setSelected(false);
                        baseMajor.setSelected(false);
                        buttonArrayList.get(0).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        buttonArrayList.get(1).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_major));
                        buttonArrayList.get(2).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        baseMajor.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        Log.d("전공 3개일때 ", "클릭");
                    }
                });
                buttonArrayList.get(2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonArrayList.get(0).setSelected(false);
                        buttonArrayList.get(1).setSelected(false);
                        buttonArrayList.get(2).setSelected(true);
                        baseMajor.setSelected(false);
                        buttonArrayList.get(0).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        buttonArrayList.get(1).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        buttonArrayList.get(2).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_major));
                        baseMajor.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        Log.d("전공 3개일때 ", "클릭");
                    }
                });
                baseMajor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonArrayList.get(0).setSelected(false);
                        buttonArrayList.get(1).setSelected(false);
                        buttonArrayList.get(2).setSelected(false);
                        baseMajor.setSelected(true);
                        baseMajor.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.selected_major));
                        buttonArrayList.get(0).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        buttonArrayList.get(1).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                        buttonArrayList.get(2).setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.not_selected_major));
                    }
                });
                break;


        }
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
            add_major.setVisibility(View.INVISIBLE);
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