package com.example.skillhub.View.Fragments_container.home_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.skillhub.Adaptors.home_courseAdapter;
import com.example.skillhub.Network.Api.CoursesApi;
import com.example.skillhub.Model.models.Courses;
import com.example.skillhub.View.Detail_Page;
import com.example.skillhub.ViewModel.CourseViewModel;
import com.example.skillhub.databinding.HomeCoursesContainerParentBinding;

import java.util.ArrayList;
import java.util.List;

public class f_course extends Fragment {
    public f_course() {}
    CoursesApi api;
    CourseViewModel viewmodels;

    private HomeCoursesContainerParentBinding binding;
    private home_courseAdapter adapter1, adapter2;
    private List<String> courseList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeCoursesContainerParentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewmodels = new ViewModelProvider(this).get(CourseViewModel.class);
        viewmodels.getCewCoursesList().observe(getViewLifecycleOwner(), new Observer<List<Courses>>() {
            @Override
            public void onChanged(List<Courses> courses) {
                adapter1 = new home_courseAdapter(requireContext(), courses,v->{
                    Courses singleCourse=courses.get(v);
//                        Toast.makeText(requireContext(), "Title :-"+singleCourse.description, Toast.LENGTH_LONG).show();
                    viewmodels = new ViewModelProvider(requireActivity()).get(CourseViewModel.class);

                    Intent intent=new Intent(requireContext(), Detail_Page.class);
                    intent.putExtra("id",singleCourse.lecture_id);
                    startActivity(intent);
                });

                binding.recyclerViewProductDesign.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

                binding.recyclerViewRecommended.setAdapter(adapter1);
            }
        });
        viewmodels.getCoursesList().observe(getViewLifecycleOwner(), new Observer<List<Courses>>() {
            @Override
            public void onChanged(List<Courses> courses) {
                if(courses!=null){

                    adapter2 = new home_courseAdapter(requireContext(), courses,v->{

                    });

                    binding.recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                    binding.recyclerViewProductDesign.setAdapter(adapter2);



                }
                else{
                    Toast.makeText(getContext(), "Failed to load Courses", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

