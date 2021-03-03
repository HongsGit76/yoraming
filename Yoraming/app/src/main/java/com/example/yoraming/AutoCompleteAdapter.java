package com.example.yoraming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.yoraming.items.MajorItem;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter extends ArrayAdapter<MajorItem> {
    //데이터를 넣을 리스트
    private List<MajorItem> majorListFull;

    public AutoCompleteAdapter(@NonNull Context context, @NonNull List<MajorItem> majorList) {
        super(context, 0, majorList);
        majorListFull = new ArrayList<>(majorList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.major_autocomplete_row, parent, false
            );
        }

        TextView textView = convertView.findViewById(R.id.text_view_name);

        //getItem(position) 코드로 자동완성 될 아이템을 가져온다
        MajorItem majorItem = getItem(position);

        if (majorItem != null) {
            textView.setText(majorItem.getMajorName());
        }
        return convertView;
    }

    //-------------------------- 이 아래는 자동완성을 위한 검색어를 찾아주는 코드이다 --------------------------
    @NonNull
    @Override
    public Filter getFilter() {
        return majorFilter;
    }

    private Filter majorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            List<MajorItem> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(majorListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (MajorItem item : majorListFull) {
                    if (item.getMajorName().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((MajorItem) resultValue).getMajorName();
        }
    };

}
