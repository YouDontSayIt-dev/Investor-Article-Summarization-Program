package com.example.app;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class rViewAdapter extends RecyclerView.Adapter<rViewAdapter.rViewHolder> {

    Context context;
    ArrayList<Articles> list;

    public rViewAdapter(Context context, ArrayList<Articles> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public rViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).
                inflate(R.layout.article_item,parent,false);
        return new rViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull rViewHolder holder, int position) {
        Articles article = list.get(position);
        holder.articleName.setText(article.getArticle_Name());
        holder.percentage.setText(article.getPosPercent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class rViewHolder extends RecyclerView.ViewHolder{

        TextView articleName,percentage;

        rViewHolder(@NonNull View itemView){
            super(itemView);

            articleName = itemView.findViewById(R.id.home_art_name);
            percentage = itemView.findViewById(R.id.home_percent);
        }
    }
}
