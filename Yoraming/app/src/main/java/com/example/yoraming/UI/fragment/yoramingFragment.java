package com.example.yoraming.UI.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoraming.DividerItemDecorator;
import com.example.yoraming.OnBackPressedListener;
import com.example.yoraming.R;
import com.example.yoraming.UI.activity.LoginActivity;
import com.example.yoraming.UI.activity.MainActivity;
import com.example.yoraming.items.scheduleitem;
import com.example.yoraming.scheduleViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class yoramingFragment extends Fragment implements OnBackPressedListener {

    View rootView;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    scheduleViewAdapter recyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_yoraming, container, false);

        //recyclerView, linearLayoutManager 할당
        recyclerView = rootView.findViewById(R.id.recyleView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //따로 커스텀한 ItemDecoration 할당(구분선 제거해주는 버전)
        RecyclerView.ItemDecoration dividerItemDecoration =
                new DividerItemDecorator(ContextCompat.getDrawable(this.getActivity(),R.drawable.divider));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerViewAdapter = new scheduleViewAdapter(this.getActivity(),getItem());
        recyclerView.setAdapter(recyclerViewAdapter);

        return rootView;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.putExtra("key", 0);
        ((MainActivity)getActivity()).replaceFragment(new HomeFragment());
    }

    //ViewHolder에 담길 시간표 item들 추가
    public List<scheduleitem> getItem() {
        List<scheduleitem> item = new ArrayList<>();
        item.add(new scheduleitem("G11"));
        item.add(new scheduleitem("G12"));
        item.add(new scheduleitem("G21"));
        item.add(new scheduleitem("G22"));
        return item;
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