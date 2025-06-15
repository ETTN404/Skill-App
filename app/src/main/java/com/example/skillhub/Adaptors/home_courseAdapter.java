package com.example.skillhub.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.skillhub.Utils.ClickListenersAD;
import com.example.skillhub.Model.models.Courses;
import com.example.skillhub.Network.RetrofitDB;
import com.example.skillhub.databinding.HomeListBinding;

import java.util.List;

public class home_courseAdapter  extends RecyclerView.Adapter<home_courseAdapter.viewH>
{
    Context context;
    List<Courses> lists;
    ClickListenersAD listenersAD;
    public home_courseAdapter(Context context, List<Courses> string, ClickListenersAD listenersAD){
        this.context=context;
        this.lists=string;
        this.listenersAD=listenersAD;
    }

    @NonNull
    @Override
    public viewH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeListBinding binding=HomeListBinding.inflate(LayoutInflater.from(context),parent,false);
       return new viewH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewH holder, int position) {
        Courses course=lists.get(position);
        holder.binding.coursePrice.setText(""+course.price);
        holder.binding.courseTitle.setText(course.title);
        String baseUrl = RetrofitDB.baseUrl;
        String imagePath = course.thumbnail;
        String cleanedPath = imagePath.replace("../", "final/SkillHUB/");
        String fullImageUrl = baseUrl + cleanedPath;
        Glide.with(context)
                .load(fullImageUrl)
                .into(holder.binding.courseImage);

        holder.itemView.setOnClickListener(v -> {
            if (listenersAD != null) {
                listenersAD.OnItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class viewH extends RecyclerView.ViewHolder
    {
        HomeListBinding binding;
        public viewH(@NonNull HomeListBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
