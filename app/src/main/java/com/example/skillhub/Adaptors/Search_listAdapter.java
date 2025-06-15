package com.example.skillhub.Adaptors;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Search_listAdapter extends RecyclerView.Adapter<Search_listAdapter.IVH>{


    @NonNull
    @Override
    public IVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull IVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class IVH extends RecyclerView.ViewHolder {
        public  IVH(View view)
            {
                super(view);
            }

    }
}
