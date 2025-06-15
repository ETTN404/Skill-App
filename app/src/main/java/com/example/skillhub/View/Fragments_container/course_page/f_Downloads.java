package com.example.skillhub.View.Fragments_container.course_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.skillhub.Adaptors.Course_listAdapter;
import com.example.skillhub.Model.models.MyLearning;
import com.example.skillhub.ViewModel.CourseViewModel;
import com.example.skillhub.databinding.CourseDownloadsBinding;

import java.util.List;

public class f_Downloads extends Fragment {

    String[] strings={"the","quick","brown","fox"};
    Course_listAdapter courseListAdapter;
    CourseViewModel viewModel;
    CourseDownloadsBinding binding;
    public f_Downloads(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding=CourseDownloadsBinding.inflate(inflater,container,false);
      return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       viewModel = new ViewModelProvider(requireActivity()).get(CourseViewModel.class);
        viewModel.getMyCourse(requireContext()).observe(getViewLifecycleOwner(), new Observer<List<MyLearning>>() {
            @Override
            public void onChanged(List<MyLearning> myLearnings) {
                courseListAdapter=new Course_listAdapter(requireContext(),myLearnings);
                binding.listItem.setAdapter(courseListAdapter);

                binding.listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
