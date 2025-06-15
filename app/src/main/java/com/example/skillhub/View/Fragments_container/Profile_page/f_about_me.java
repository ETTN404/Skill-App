package com.example.skillhub.View.Fragments_container.Profile_page;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skillhub.R;
import com.example.skillhub.databinding.ProAboutMeBinding;
import com.google.android.flexbox.FlexboxLayout;

public class f_about_me extends Fragment
{
    public f_about_me(){}
    String[] words = {"Short", "VeryLongWordHere", "Tiny", "Android", "Flexible", "Dynamic"};
    ProAboutMeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=ProAboutMeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add_search_item();
    }
    public void add_search_item(){
        //adds searched items i mean previously searched items or courses
        for (String word : words) {
            TextView tv = new TextView(requireContext());
            tv.setText(word);
            tv.setBackgroundResource(R.drawable.bg_stroke);
            tv.setPadding(16, 8, 16, 8);
            tv.setTextColor(Color.BLACK);

            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 8, 8, 8);
            tv.setLayoutParams(params);

            binding.flexbox.addView(tv);
        }
    }
}
