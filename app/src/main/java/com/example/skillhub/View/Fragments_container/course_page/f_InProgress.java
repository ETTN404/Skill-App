package com.example.skillhub.View.Fragments_container.course_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.skillhub.Adaptors.Course_listAdapter;
import com.example.skillhub.Model.models.MyLearning;
import com.example.skillhub.View.Detail_Page;
import com.example.skillhub.ViewModel.CourseViewModel;
import com.example.skillhub.databinding.CourseInprogressBinding;

import java.util.List;

public class f_InProgress extends Fragment {
    public f_InProgress(){}
    CourseInprogressBinding binding;
    String[] strings={"the","quick","brown","fox","jumps","over","the","lazy","dogs"};
    Course_listAdapter adapterYourCourse;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= CourseInprogressBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    CourseViewModel viewModel;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getContext(), "onViewCreated: InProgress", Toast.LENGTH_SHORT).show();

        // Safely get ViewModel from activity scope
        viewModel = new ViewModelProvider(requireActivity()).get(CourseViewModel.class);

        // Make sure LiveData is not null
        LiveData<List<MyLearning>> liveData = viewModel.getMyCourse(requireContext());

        if (liveData != null) {
            liveData.observe(getViewLifecycleOwner(), myLearnings -> {
                if (myLearnings != null)
                {
                    adapterYourCourse = new Course_listAdapter(requireContext(), myLearnings);
                    binding.listItem.setAdapter(adapterYourCourse);

                    binding.listItem.setOnItemClickListener((parent, itemView, position, id) ->{
                            Toast.makeText(getContext(), "Clicked: " + position, Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(requireContext(), Detail_Page.class);
                            intent.putExtra("id",myLearnings.get(position).lecture_id);
                            startActivity(intent);
                    });

                }
            });
        } else {
            Toast.makeText(getContext(), "LiveData was null!", Toast.LENGTH_SHORT).show();
        }
    }


//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
//    {
//        super.onViewCreated(view, savedInstanceState);
//        Toast.makeText(getContext(), "on view Created progress", Toast.LENGTH_LONG).show();
//        viewModel=new ViewModelProvider(requireActivity()).get(CourseViewModel.class);
//        viewModel.getMyCourse(requireContext()).observe(getViewLifecycleOwner(), new Observer<List<MyLearning>>() {
//            @Override
//            public void onChanged(List<MyLearning> myLearnings) {
//                adapterYourCourse=new Course_listAdapter(requireContext(),myLearnings);
//                binding.listItem.setAdapter(adapterYourCourse);
//
//                binding.listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//    }


    @Override
    public void onResume()
    {
        super.onResume();
        Toast.makeText(getContext(), "on Resume progress", Toast.LENGTH_LONG).show();
    }
}
