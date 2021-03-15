package com.example.yoraming;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoraming.UI.activity.MainActivity;
import com.example.yoraming.items.Yoramingitem;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Activity activity;
    private List<Yoramingitem> item;
    private MainActivity mainActivity;

    public RecyclerViewAdapter(Activity activity, List<Yoramingitem> item) {
        this.activity = activity;
        this.item = item;
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, "click" +
                            item.get(getAdapterPosition()).getSemester(), Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(activity, "remove" +
                            item.get(getAdapterPosition()).getSemester(), Toast.LENGTH_LONG).show();
                    removeItemView(getAdapterPosition());
                    return false;
                }
            });
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
        Yoramingitem data = item.get(position);
        holder.ll.setTag(data.getSemester());
        holder.ll.setBackgroundResource(R.drawable.blue_rounded);
    }

    private void removeItemView(int position) {
        item.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, item.size());
    }
}
