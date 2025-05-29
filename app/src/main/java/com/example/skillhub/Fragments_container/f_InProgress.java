package com.example.skillhub.Fragments_container;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skillhub.Adaptors.Class_Adapter_Your_Course;
import com.example.skillhub.databinding.FinprogressBinding;

public class f_InProgress extends Fragment {
    public f_InProgress(){}
    FinprogressBinding binding;
    String[] strings={"the","quick","brown","fox","jumps","over","the","lazy","dogs"};
    Class_Adapter_Your_Course adapterYourCourse;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FinprogressBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapterYourCourse=new Class_Adapter_Your_Course(requireActivity(),strings);
        binding.listItem.setAdapter(adapterYourCourse);
        binding.listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
