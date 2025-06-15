package com.example.skillhub.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.skillhub.R;

import java.util.List;

public class home_catagoryAdapter extends RecyclerView.Adapter<home_catagoryAdapter.VH> {
    Context context;
    List<String> lists;
    public home_catagoryAdapter(Context context, List<String> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home___cat_list,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull home_catagoryAdapter.VH holder, int position) {

    }


    @Override
    public int getItemCount() {
        return lists.size();
    }
    public static class VH extends RecyclerView.ViewHolder{

        public VH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
