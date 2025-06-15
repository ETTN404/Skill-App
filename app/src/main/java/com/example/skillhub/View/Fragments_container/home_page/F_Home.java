package com.example.skillhub.View.Fragments_container.home_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.skillhub.Adaptors.home_catagoryAdapter;
import com.example.skillhub.databinding.HomeParentBinding;

import java.util.ArrayList;
import java.util.List;

public class F_Home extends Fragment {
   public F_Home(){}
    List<String> stringList = new ArrayList<>();
    boolean isLinear = true;

    HomeParentBinding binding;
    home_catagoryAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=HomeParentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        { stringList.add("hello");
        stringList.add("hello");
        stringList.add("hello");
        stringList.add("hello");
        stringList.add("hello");
        stringList.add("hello");}
        adapter = new home_catagoryAdapter(requireContext(),stringList);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(requireContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);




        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        binding.seeCat.setOnClickListener(v -> {
            if (isLinear) {
                binding.recyclerview.setLayoutManager(new GridLayoutManager(requireContext(), 4));
            } else {
                binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            }
            isLinear = !isLinear;
        });
        binding.recyclerview.setAdapter(adapter);

        if(savedInstanceState==null){
            Fragment fragment=new f_course();
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(binding.SearchFragmentContainer.getId(),fragment)
                    .commit();
        }


    }
}
