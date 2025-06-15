package com.example.skillhub.View.Fragments_container.search_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skillhub.Adaptors.Search_GridAdapter;
import com.example.skillhub.databinding.SearchGridBinding;

public class f___search extends Fragment {
    public f___search(){}
    String[] category={"Development","Business","Finance & Accounting","Marketing","Lifestyle","Photography & Video","Health & Fitness","Music"};
    SearchGridBinding binding;
    Search_GridAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=SearchGridBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new Search_GridAdapter(requireContext(),category);
        binding.courseGrid.setAdapter(adapter);
        binding.courseGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(requireContext(), category[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
