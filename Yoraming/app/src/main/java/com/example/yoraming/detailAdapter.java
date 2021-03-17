package com.example.yoraming;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoraming.UI.fragment.DetailFragment;

import java.util.ArrayList;
import java.util.List;

public class detailAdapter extends RecyclerView.Adapter<detailAdapter.ViewHolder> {
    private ArrayList<DetailFragment.item> mDataset; //MainActivity에 item class를 정의해 놓았음
    private Context mcontext;
    private Activity mactivity;
//    private ImageView spec_plus;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // 사용될 항목들 선언
        public TextView mAge;
        public Spinner spinner;
        public ImageView spec_menu_plus,spec_plus;


        public ViewHolder(View v) {
            super(v);
            spinner = (Spinner) v.findViewById(R.id.spec_spinner);
//            mName = (TextView) v.findViewById(R.id.info_text);
            mAge = (TextView) v.findViewById(R.id.tv_select_spec);
//            mEmail = (TextView) v.findViewById(R.id.info_email);
            if (v.findViewById(R.id.spec_menu_plus) != null) {
                spec_menu_plus = (ImageView) v.findViewById(R.id.spec_menu_plus);
            }
            if (v.findViewById(R.id.spec_plus) != null) {
                spec_plus = (ImageView) v.findViewById(R.id.spec_plus);
            }
        }
    }

    // 생성자 - 넘어 오는 데이터파입에 유의해야 한다.
    public detailAdapter(Context context, ArrayList<DetailFragment.item> myDataset) {
        mDataset = myDataset;
        mcontext = context;
        mactivity = (Activity) context;

    }


    //뷰홀더
    // Create new views (invoked by the layout manager)
    //루트뷰 = 0 자격증 = 1 경력사항 = 2 외국어 = 3 기타 = 4

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;


        // 뷰타입 별로 나눠주는 부분
        if (viewType == 0) {
            Log.d("create_view_type", "root ");
             v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spec_root_card_view, parent, false);
        }else if (viewType == 1) {
            Log.d("create_view_type", "자격증 ");
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.certificate_spec_card_view, parent, false);
        }else if (viewType == 2) {
            Log.d("create_view_type", "경력사항 ");
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.career_spec_card_view, parent, false);
        }else if (viewType == 3) {
            Log.d("create_view_type", "외국어 ");
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.language_spec_card_view, parent, false);
        }else{
            Log.d("create_view_type", "기타 ");
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.etc_spec_card_view, parent, false);
        }
        // set the view's size, margins, paddings and layout parameters

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }
    //뷰 타입 나눠주는 부분
    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position).getItemViewType() == 0) {
            return 0;
        } else if(mDataset.get(position).getItemViewType() == 1){
            return 1;
        }else if(mDataset.get(position).getItemViewType() == 2){
            return 2;
        }else if(mDataset.get(position).getItemViewType() == 3){
            return 3;
        }else {
            return 4;
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mName.setText(mDataset.get(position).getName();
        String[] items = {"자격증","경력사항","외국어 공인 성적","기타"," "};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mcontext,android.R.layout.simple_spinner_dropdown_item,items)
        {            //스피너 디폴트값 빈값 설정을 위한 부분*리얼 중요*
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1;            // you don't display last item. It is used as hint.
            }

        };


        //뷰별로 나눠주기
        if(mDataset.get(position).getItemViewType() == 0){
            //스피너 디폴트값으로 빈값설정 *중요* 복잡했음 죽을뻔
            holder.spinner.setAdapter(spinnerAdapter);
            spinnerAdapter.setDropDownViewResource(R.layout.spec_spin_dropdown);
            System.out.println(spinnerAdapter.getCount());
            holder.spinner.setSelection(spinnerAdapter.getCount(),false);        //set the hint the default selection so it appears on launch.
            holder.spec_menu_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.spinner.performClick();
                }
            });
            //메뉴선택에서 각각의 스펙 항목 선택시
            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("click_spec", position+"");
                    Log.d("selected", "onItemSelected: "+holder.spinner.getSelectedItem());
                    List<Integer> nowRecyclerList = new ArrayList<>();
                    for(int i = 0; i < mDataset.size();i++){
                        nowRecyclerList.add(mDataset.get(i).getItemViewType());
                    }
                    System.out.println(nowRecyclerList);
                    if (position == 0) {
                        if(!nowRecyclerList.contains(position + 1)) {
                            mDataset.add(mDataset.size() - 1, new DetailFragment.item(30, position + 1));
                            if (mDataset.size() == 5){
                                mDataset.remove(mDataset.size() - 1);
                            }
                             detailAdapter.this.notifyDataSetChanged();
                        }else{
                            Toast.makeText(view.getContext(),"이미 추가된 항목입니다.",Toast.LENGTH_LONG).show();
                        }
                    }else if (position == 1) {
                        if(!nowRecyclerList.contains(position + 1)) {
                            mDataset.add(mDataset.size() - 1, new DetailFragment.item(30, position + 1));
                            if (mDataset.size() == 5){
                                mDataset.remove(mDataset.size() - 1);
                            }
                            detailAdapter.this.notifyDataSetChanged();
                        }else{
                            Toast.makeText(view.getContext(),"이미 추가된 항목입니다.",Toast.LENGTH_LONG).show();
                        }
                    }else if (position == 2) {
                        if(!nowRecyclerList.contains(position + 1)) {
                            mDataset.add(mDataset.size() - 1, new DetailFragment.item(30, position + 1));
                            if (mDataset.size() == 5){
                                mDataset.remove(mDataset.size() - 1);
                            }
                            detailAdapter.this.notifyDataSetChanged();
                        }else{
                            Toast.makeText(view.getContext(),"이미 추가된 항목입니다.",Toast.LENGTH_LONG);
                        }
                    }else if (position == 3) {
                        if(!nowRecyclerList.contains(position + 1)) {
                            mDataset.add(mDataset.size() - 1, new DetailFragment.item(30, position + 1));
                            if (mDataset.size() == 5){
                                mDataset.remove(mDataset.size() - 1);
                            }
                            detailAdapter.this.notifyDataSetChanged();
                        }else{
                            Toast.makeText(view.getContext(),"이미 추가된 항목입니다.",Toast.LENGTH_LONG);
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }




//        holder.mAge.setText(mDataset.get(position).getAge()+""); //int를 가져온다는점 유의


//        holder.mAge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("TAG",mDataset.get(position).getAge()+"" );
//            }
//        });

        if(holder.spec_plus != null) {
            holder.spec_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent = new Intent(mactivity, spec_input_popup_activity.class);
                if (mDataset.get(position).getItemViewType() ==1) {
                    intent.putExtra("spec_type", "certificate");
                }else if (mDataset.get(position).getItemViewType() ==2) {
                    intent.putExtra("spec_type", "career");
                }else if (mDataset.get(position).getItemViewType() ==3) {
                    intent.putExtra("spec_type", "language");
                }else if (mDataset.get(position).getItemViewType() ==4) {
                    intent.putExtra("spec_type", "etc");
                }
                mactivity.startActivityForResult(intent, 1);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
