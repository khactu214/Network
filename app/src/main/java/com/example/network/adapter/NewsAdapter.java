package com.example.network.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.network.R;
import com.example.network.interfaces.NewsOnClick;
import com.example.network.model.Item;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Item> listData;
    private NewsOnClick iOnClick;

    public NewsAdapter(Activity activity, List<Item> listData) {
        this.activity = activity;
        this.listData = listData;
    }

    public void setiOnClick(NewsOnClick iOnClick) {
        this.iOnClick = iOnClick;
    }

    public void reloadData(List<Item> listData){
        this.listData = listData;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() { return listData.size(); }

    public class NewsHolder extends RecyclerView.ViewHolder{
        TextView tvDate, tvTitle, tvContent;
        ImageView ivCover;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            ivCover = itemView.findViewById(R.id.ivCover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iOnClick.onClickItem(getAdapterPosition());
                }
            });
        }
    }
}
