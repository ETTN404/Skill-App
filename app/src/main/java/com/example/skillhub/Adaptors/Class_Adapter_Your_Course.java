package com.example.skillhub.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.skillhub.databinding.YourCourseListBinding;

public class Class_Adapter_Your_Course extends ArrayAdapter<String> {

    public Class_Adapter_Your_Course(@NonNull Context context, String[] resource) {
        super(context,0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        YourCourseListBinding binding;
        if (convertView == null) {binding=YourCourseListBinding.inflate(LayoutInflater.from(getContext()),parent,false);
        convertView = binding.getRoot();
        convertView.setTag(binding);
    } else {
        binding = (YourCourseListBinding) convertView.getTag();
    }
        return convertView;
    }
}
