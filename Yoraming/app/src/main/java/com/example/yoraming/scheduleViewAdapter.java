package com.example.yoraming;

import android.app.Activity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoraming.UI.activity.MainActivity;
import com.example.yoraming.items.scheduleitem;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public class scheduleViewAdapter extends RecyclerView.Adapter<scheduleViewAdapter.ViewHolder> {

    private Activity activity;
    private List<scheduleitem> item;
    private MainActivity mainActivity;
    //임시 데이터를 관리해주는 TreeMap
    TreeMap<String, ArrayList<TempData>> ht = new TreeMap<String, ArrayList<TempData>>();
    //데이터를 담는 각 arrayList
    ArrayList<TempData> one = new ArrayList<TempData>();
    ArrayList<TempData> two = new ArrayList<TempData>();
    ArrayList<TempData> three = new ArrayList<TempData>();
    ArrayList<TempData> four = new ArrayList<TempData>();

    public scheduleViewAdapter(Activity activity, List<scheduleitem> item) {
        this.activity = activity;
        this.item = item;
        //임시 데이터부분 초기화
        addOneList(one);
        addtwoList(two);
        addthreeList(three);
        addfourList(four);
        addHashTable();
    }
    @Override
    public int getItemCount() {
        return item.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.semesterLinearLayout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        scheduleitem data = item.get(position);
        holder.ll.setTag(data.getSemester());
        CreateCategory(holder.ll.getRootView(),data.getSemester());
        CreateSubject(holder.ll.getRootView(), data);
        setRootLayoutSize(holder.ll.getRootView(), position);
        setChildLayoutSize(holder.ll.getRootView(), data.getSemester());
    }

    private void removeItemView(int position) {
        item.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, item.size());
    }

        // 동적으로 학기 별 과목 구분 LinearLayout을 생성해주는 함수
    public void onCreateChildLayout(@NotNull View rootView, String root_tag, String tag, int lineColor) {
        LinearLayout root_View = (LinearLayout)rootView.findViewWithTag(root_tag);
        LinearLayout ll = new LinearLayout((activity));
        LinearLayout.LayoutParams lpm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, activity.getResources().getDisplayMetrics()));
        lpm.topMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, activity.getResources().getDisplayMetrics());
        lpm.bottomMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, activity.getResources().getDisplayMetrics());
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
        TextView tv = new TextView(activity);
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,1f));
        tv.setText(majorName);
        parent_View.addView(tv);
    }

    // 학기 별 LinearLayout에 height를 설정해주는 함수
    public void setRootLayoutSize(View rootView, int position) {
        int childSum = 0;
        LinearLayout root_View = (LinearLayout) rootView.findViewWithTag(item.get(position).getSemester());
        ViewGroup.LayoutParams pm = (ViewGroup.LayoutParams)root_View.getLayoutParams();
        for(int i = 0; i < root_View.getChildCount(); i++) {
            LinearLayout leaf_View = (LinearLayout)root_View.getChildAt(i);
            childSum = childSum + leaf_View.getChildCount();
        }
        pm.height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100 * childSum, activity.getResources().getDisplayMetrics());
        if(pm.height >= (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 500, activity.getResources().getDisplayMetrics())) {
            pm.height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 500, activity.getResources().getDisplayMetrics());
        }
        root_View.setLayoutParams(pm);
    }

    // 전공 별 LinearLayout에 가중치를 변경해 height 비율을 맞춰주는 함수
    public void setChildLayoutSize(View rootView, String tag) {
        LinearLayout parent_View = (LinearLayout) rootView.findViewWithTag(tag);

        for(int i = 0; i < parent_View.getChildCount(); i++) {
            LinearLayout child_View = (LinearLayout)parent_View.getChildAt(i);
            LinearLayout.LayoutParams pm = (LinearLayout.LayoutParams)child_View.getLayoutParams();
            pm.weight = child_View.getChildCount() * 1f;
            child_View.setLayoutParams(pm);
        }
    }

    // 임시 데이터들
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
        ht.put(item.get(0).getSemester(),one);
        ht.put(item.get(1).getSemester(),two);
        ht.put(item.get(2).getSemester(),three);
        ht.put(item.get(3).getSemester(),four);
    }

    // 데이터 별로 과목 카테고리 생성
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

    // 데이터 별로 과목 생성
    public void CreateSubject(View rootView, scheduleitem item) {
        ArrayList<TempData> temp;
        temp  = ht.get(item.getSemester());

        for(int j = 0; j < temp.size(); j ++) {
            onCreateMajorText(rootView,item.getSemester()+temp.get(j).getCategory(),temp.get(j).getName());
        }
    }
}
