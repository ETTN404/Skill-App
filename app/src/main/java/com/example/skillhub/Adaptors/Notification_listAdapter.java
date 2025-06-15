package com.example.skillhub.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.skillhub.Utils.Text_Format;
import com.example.skillhub.databinding.NotiListBinding;

public class Notification_listAdapter extends ArrayAdapter<String>
{
    public Notification_listAdapter(Context context,String[] strings)
    {
        super(context,0,strings);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NotiListBinding binding;
        if(convertView==null){binding=NotiListBinding
                .inflate(LayoutInflater
                        .from(getContext()),parent,false);
        convertView=binding.getRoot(); convertView.setTag(binding);}
        else{
            binding=(NotiListBinding)convertView.getTag();
        }
        String string=binding.detail.getText().toString();
        binding.detail.setText(Text_Format.change(string));
        return convertView;
    }
}
