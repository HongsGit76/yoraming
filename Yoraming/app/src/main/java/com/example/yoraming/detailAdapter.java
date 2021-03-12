package com.example.yoraming;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoraming.UI.fragment.DetailFragment;

import java.util.ArrayList;

public class detailAdapter extends RecyclerView.Adapter<detailAdapter.ViewHolder> {
    private ArrayList<DetailFragment.item> mDataset; //MainActivity에 item class를 정의해 놓았음
    private Context mcontext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // 사용될 항목들 선언
        public TextView mAge;
        public Spinner spinner;
        public ImageView plus;


        public ViewHolder(View v) {
            super(v);
            spinner = (Spinner) v.findViewById(R.id.spinner);
//            mName = (TextView) v.findViewById(R.id.info_text);
            mAge = (TextView) v.findViewById(R.id.tv_select_spec);
//            mEmail = (TextView) v.findViewById(R.id.info_email);
            plus =  (ImageView) v.findViewById(R.id.plus);
        }
    }

    // 생성자 - 넘어 오는 데이터파입에 유의해야 한다.
    public detailAdapter(Context context,ArrayList<DetailFragment.item> myDataset) {
        mDataset = myDataset;
        mcontext = context;
    }


    //뷰홀더
    // Create new views (invoked by the layout manager)

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        Log.d("view", viewType+"");

        // 뷰타입 별로 나눠주는 부분
        if (viewType == 1) {
            Log.d("view", "23 ");
             v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spec_root_card_view, parent, false);
        }else {
            Log.d("view", "22 ");
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spec_card_view, parent, false);
        }
        // set the view's size, margins, paddings and layout parameters

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }
    //뷰 타입 나눠주는 부분
    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position).getItemViewType() == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.mName.setText(mDataset.get(position).getName());
        String[] items = {"자격증","인턴","경력사항","외국어 공인 성적"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(mcontext,android.R.layout.simple_spinner_dropdown_item,items);


        //뷰별로 나눠주기
        if(mDataset.get(position).getItemViewType() == 1){
            holder.spinner.setAdapter(spinnerAdapter);
            spinnerAdapter.setDropDownViewResource(R.layout.spec_spin_dropdown);
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.spinner.performClick();
                }
            });
            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mDataset.add(mDataset.size()-1,new DetailFragment.item(30,2));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }




//        holder.mAge.setText(mDataset.get(position).getAge()+""); //int를 가져온다는점 유의


        holder.mAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG",mDataset.get(position).getAge()+"" );
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
