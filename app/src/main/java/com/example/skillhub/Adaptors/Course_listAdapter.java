package com.example.skillhub.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.skillhub.Model.models.MyLearning;
import com.example.skillhub.Utils.Text_Format;
import com.example.skillhub.Utils.pictureFormat;
import com.example.skillhub.databinding.CourseListBinding;

import java.util.List;

public class Course_listAdapter extends ArrayAdapter<MyLearning> {
    List<MyLearning> course;

    public Course_listAdapter(@NonNull Context context, List<MyLearning> resource) {
        super(context,0, resource);
        this.course=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        CourseListBinding binding;
        if (convertView == null) {binding=CourseListBinding
                .inflate(LayoutInflater
                        .from(getContext()),parent,false);
        convertView = binding.getRoot();
        convertView.setTag(binding);
    } else {
        binding = (CourseListBinding) convertView.getTag();
    }
        MyLearning single=course.get(position);
        binding.progressBar.setMax(100);
        binding.progressBar.setProgress((10-position)*10);
        binding.title.setText(Text_Format.change(single.title));
        String fullImageUrl= pictureFormat.getCorrect_path(single.thumbnail);
        Glide.with(getContext())
                .load(fullImageUrl)
                .into(binding.imageView);


        return convertView;
    }
}
