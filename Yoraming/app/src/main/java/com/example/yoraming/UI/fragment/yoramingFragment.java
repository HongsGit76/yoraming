package com.example.yoraming.UI.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoraming.OnBackPressedListener;
import com.example.yoraming.R;
import com.example.yoraming.RecyclerViewAdapter;
import com.example.yoraming.TempData;
import com.example.yoraming.UI.activity.LoginActivity;
import com.example.yoraming.UI.activity.MainActivity;
import com.example.yoraming.items.Yoramingitem;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public class yoramingFragment extends Fragment implements OnBackPressedListener {

    View rootView;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerViewAdapter recyclerViewAdapter;

    TreeMap<String, ArrayList<TempData>> ht = new TreeMap<String, ArrayList<TempData>>();
    String[] semester = {"G11","G12","G21","G22"};
    ArrayList<String> tagList = new ArrayList<String>();
    ArrayList<TempData> one = new ArrayList<TempData>();
    ArrayList<TempData> two = new ArrayList<TempData>();
    ArrayList<TempData> three = new ArrayList<TempData>();
    ArrayList<TempData> four = new ArrayList<TempData>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_yoraming, container, false);

        recyclerView = rootView.findViewById(R.id.recyleView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(),linearLayoutManager.getOrientation()));
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),getItem());
        recyclerView.setAdapter(recyclerViewAdapter);

//        addOneList(one);
//        addtwoList(two);
//        addthreeList(three);
//        addfourList(four);
//
//        addHashTable();
//        for(int i = 0; i < semester.length; i++) {
//            CreateCategory(recyclerView,semester[i]);
//        }
//        CreateSubject(recyclerView,semester);
//
//        for(int i = 0; i < semester.length; i++) {
//            setRootLayoutSize(recyclerView, semester[0]);
//        }
//        for(int i =0; i < tagList.size(); i++ ) {
//            setChildLayoutSize(recyclerView,tagList.get(i));
//        }

        return rootView;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("key", 0);
        ((MainActivity)getActivity()).replaceFragment(new HomeFragment());
    }

    public List<Yoramingitem> getItem() {
        List<Yoramingitem> item = new ArrayList<>();
        item.add(new Yoramingitem("G11"));
        item.add(new Yoramingitem("G12"));
        item.add(new Yoramingitem("G21"));
        item.add(new Yoramingitem("G22"));
        return item;
    }

    // 동적으로 학기 별 과목 구분 LinearLayout을 생성해주는 함수
    public void onCreateChildLayout(@NotNull View rootView, String root_tag, String tag, int lineColor) {
        LinearLayout root_View = (LinearLayout)rootView.findViewWithTag(root_tag);
        LinearLayout ll = new LinearLayout((getActivity()));
        LinearLayout.LayoutParams lpm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics()));
        lpm.topMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        lpm.bottomMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        lpm.weight = 1;
        ll.setLayoutParams(lpm);
        ll.setBackgroundResource(lineColor);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setTag(tag);
        root_View.addView(ll);

        tagList.add(ll.getTag().toString());
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

    public void addOneList(ArrayList<TempData> temp) {
        TempData one = new TempData();
        one.setCategory("majorR");
        one.setName("디지털미디어");
        temp.add(one);
        TempData two = new TempData();
        two.setName("디자인기초");
        two.setCategory("majorR");
        temp.add(two);
        TempData three = new TempData();
        three.setName("아주희망");
        three.setCategory("univR");
        temp.add(three);
        TempData four = new TempData();
        four.setName("수학1");
        four.setCategory("univR");
        temp.add(four);
        TempData five = new TempData();
        five.setName("영어2");
        five.setCategory("univR");
        temp.add(five);
        TempData Six = new TempData();
        Six.setName("영상문학기행");
        Six.setCategory("univS");
        temp.add(Six);
        TempData seven = new TempData();
        seven.setName("소셜미디어");
        seven.setCategory("majorS");
        temp.add(seven);
    }

    public void addtwoList(ArrayList<TempData> temp) {
        TempData one = new TempData();
        one.setCategory("majorR");
        one.setName("컴퓨터프로그래밍");
        temp.add(one);
        TempData two = new TempData();
        two.setName("컴퓨터프로그래밍설계");
        two.setCategory("majorS");
        temp.add(two);
        TempData three = new TempData();
        three.setName("글쓰기");
        three.setCategory("univR");
        temp.add(three);
        TempData four = new TempData();
        four.setName("확률과통계");
        four.setCategory("univR");
        temp.add(four);
        TempData five = new TempData();
        five.setName("영어1");
        five.setCategory("univR");
        temp.add(five);
        TempData Six = new TempData();
        Six.setName("아주인성");
        Six.setCategory("univR");
        temp.add(Six);
    }

    public void addthreeList(ArrayList<TempData> temp) {
        TempData one = new TempData();
        one.setCategory("majorR");
        one.setName("객체지향프로그래밍");
        temp.add(one);
        TempData two = new TempData();
        two.setName("자료구조");
        two.setCategory("majorS");
        temp.add(two);
        TempData three = new TempData();
        three.setName("이산수학");
        three.setCategory("majorR");
        temp.add(three);
        TempData four = new TempData();
        four.setName("그래픽디자인");
        four.setCategory("majorS");
        temp.add(four);
        TempData five = new TempData();
        five.setName("생명과학");
        five.setCategory("univR");
        temp.add(five);
        TempData Six = new TempData();
        Six.setName("물리학");
        Six.setCategory("univR");
        temp.add(Six);
    }

    public void addfourList(ArrayList<TempData> temp) {
        TempData one = new TempData();
        one.setCategory("majorR");
        one.setName("컴퓨터구조");
        temp.add(one);
        TempData two = new TempData();
        two.setName("도메인분석및SW설계");
        two.setCategory("majorS");
        temp.add(two);
        TempData three = new TempData();
        three.setName("선형대수");
        three.setCategory("univR");
        temp.add(three);
        TempData four = new TempData();
        four.setName("수학2");
        four.setCategory("univR");
        temp.add(four);
        TempData five = new TempData();
        five.setName("알고리즘");
        five.setCategory("majorS");
        temp.add(five);
        TempData Six = new TempData();
        Six.setName("창의소프트웨어입문");
        Six.setCategory("majorR");
        temp.add(Six);
    }

    public void addHashTable() {
        ht.put(semester[0],one);
        ht.put(semester[1],two);
        ht.put(semester[2],three);
        ht.put(semester[3],four);
    }

    public void CreateCategory(View rootView,String semester) {
        ArrayList<TempData> info = ht.get(semester);
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i < info.size(); i++) {
            temp.add(info.get(i).getCategory());
        }

        HashSet<String> hs = new HashSet<String>(temp);
        ArrayList<String> category = new ArrayList<String>(hs);

        for(int i = 0; i < category.size(); i++) {
            switch (category.get(i)) {
                case "majorR":
                    onCreateChildLayout(rootView,semester,semester+category.get(i),R.drawable.blue_rounded);
                    break;
                case "majorS":
                    onCreateChildLayout(rootView,semester,semester+category.get(i),R.drawable.yellow_rounded);
                    break;
                case "univR":
                    onCreateChildLayout(rootView,semester,semester+category.get(i),R.drawable.orange_rounded);
                    break;
                case "univS":
                    onCreateChildLayout(rootView,semester,semester+category.get(i),R.drawable.green_rounded);
                    break;
                default:
                    return;
            }
        }
    }

    public void CreateSubject(View rootView,String[] semester) {
        ArrayList<TempData> temp;
        for(int i = 0; i < ht.size(); i++){
            temp = ht.get(semester[i]);
            for(int j = 0; j < temp.size(); j ++) {
                onCreateMajorText(rootView,semester[i]+temp.get(j).getCategory(),temp.get(j).getName());
            }
        }
    }



    // json파일을 String으로 반환해주는 함수
//    private String getJsonString() {
//        String json = "";
//
//        try {
//            InputStream is = getActivity().getAssets().open("test.json");
//            int fileSize = is.available();
//
//            byte[] buffer = new byte[fileSize];
//            is.read(buffer);
//            is.close();
//
//            json = new String(buffer,"UTF-8");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return json;
//    }

    // String 형식의 json데이터를 ArrayList에 넣어주는 함수
//    private void jsonParsing(String json,String semester) {
//        try {
//            JSONObject jsonObject = new JSONObject(json);
//
//            JSONArray jsonArray = jsonObject.getJSONArray(semester);
//
//            ArrayList<UserYoramingParsing> yoramingList = new ArrayList<UserYoramingParsing>();
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject testObj = jsonArray.getJSONObject(i);
//                UserYoramingParsing yoraming = new UserYoramingParsing();
//                yoraming.setName(testObj.getString("comSbjt_name"));
//                JSONObject tempObj = testObj.getJSONArray("recognitionSbjt").getJSONObject(0);
//                yoraming.setCategory(tempObj.getString("recSbjt_recCategory"));
//                yoramingList.add(yoraming);
//            }
//
//            ht.put(semester,yoramingList);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    // json에서 받은 데이터로 학기와 전공 종류 틀을 만들어주는 함수
//    public void onCreateTemplate(View rootView, ArrayList<UserYoramingParsing> list) {
//        ArrayList<String> temp = new ArrayList<String>();
//        Map<String,HashSet<String>> ht = new HashMap<String, HashSet<String>>();
//
//        for(int i = 0; i < list.size(); i++) {
//            temp.add(list.get(i).getDate());
//        }
//        HashSet<String> hs = new HashSet<String>(temp);
//        ArrayList<String> dateList = new ArrayList<String>(hs);
//        Collections.sort(dateList);
//
//        HashSet<String> tempSet = new HashSet<String>();
//
//        for(int i = 0; i < dateList.size(); i++) {
//            onCreateRootLayout(rootView, dateList.get(i),i+1);
//            tempSet = new HashSet<String>();
//            for(int j = 0; j < list.size(); j++) {
//                if(list.get(j).getDate() == dateList.get(i)) {
//                    System.out.println(list.get(j).getCategory());
//                    tempSet.add(list.get(j).getCategory());
//                }
//            }
//            ht.put(dateList.get(i),tempSet);
//        }
//        System.out.println(ht);
//    }
}