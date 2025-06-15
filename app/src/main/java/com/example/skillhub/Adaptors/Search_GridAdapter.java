package com.example.skillhub.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.skillhub.R;
import com.example.skillhub.databinding.SearchListBinding;

public class Search_GridAdapter extends BaseAdapter {
    String[] lists;
    public Search_GridAdapter(Context context, String[] lists){
        this.lists=lists;
    }
    @Override
    public int getCount() {
        return lists.length;
    }

    @Override
    public Object getItem(int position) {
        return lists[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        SearchListBinding binding;
        if(convertView==null) {
            binding = SearchListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
            convertView=binding.getRoot();
            convertView.setTag(binding);
        }
        else{
            binding=(SearchListBinding) convertView.getTag();
        }
        binding.textView.setText(lists[position]);
        return convertView;
    }
}
