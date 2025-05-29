package com.example.skillhub.Fragments_container;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.skillhub.R;
import com.example.skillhub.databinding.LYourCourseBinding;
import com.example.skillhub.databinding.LYourCourseBinding;

public class F_YourCourse extends Fragment {
    LYourCourseBinding binding;
    FragmentManager fragmentManager;
    Fragment inprogress, downloads, completed;
    public F_YourCourse(){}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding=LYourCourseBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getParentFragmentManager();

        inprogress = fragmentManager.findFragmentByTag("inprogress");
        completed = fragmentManager.findFragmentByTag("completed");
        downloads = fragmentManager.findFragmentByTag("downloads");

        if (inprogress == null) inprogress = new f_InProgress();
        if (completed == null) completed = new f_Completed();
        if (downloads == null) downloads = new f_Downloads();

        if (savedInstanceState == null) {
            showFragment(inprogress, "inprogress");
        }

            binding.inprogress.setOnClickListener(v->{
                showFragment(inprogress, "inprogress");
                        {binding.inprogress.setBackgroundResource(R.drawable.bg_green_blue_curve_rectangle);
                                binding.completed.setBackgroundResource(R.drawable.bg_your_course_buttons_color);
                                binding.downloads.setBackgroundResource(R.drawable.bg_your_course_buttons_color);
                                binding.inprogress.setTextColor( ContextCompat.getColor(getActivity(), R.color.white));
                        binding.completed.setTextColor( ContextCompat.getColor(getActivity(), R.color.black));
                        binding.downloads.setTextColor( ContextCompat.getColor(getActivity(), R.color.black));}
        });

            binding.downloads.setOnClickListener(v->{
                showFragment(downloads, "downloads");
                        { binding.downloads.setBackgroundResource(R.drawable.bg_green_blue_curve_rectangle);
                                binding.completed.setBackgroundResource(R.drawable.bg_your_course_buttons_color);
                                binding.inprogress.setBackgroundResource(R.drawable.bg_your_course_buttons_color);

                                binding.downloads.setTextColor( ContextCompat.getColor(getActivity(), R.color.white));
                        binding.completed.setTextColor( ContextCompat.getColor(getActivity(), R.color.black));
                        binding.inprogress.setTextColor( ContextCompat.getColor(getActivity(), R.color.black));}
        });

            binding.completed.setOnClickListener(v->{
                showFragment(completed, "completed");
                        { binding.completed.setBackgroundResource(R.drawable.bg_green_blue_curve_rectangle);
                                binding.inprogress.setBackgroundResource(R.drawable.bg_your_course_buttons_color);
                                binding.downloads.setBackgroundResource(R.drawable.bg_your_course_buttons_color);

                                binding.completed.setTextColor( ContextCompat.getColor(getActivity(), R.color.white));
                        binding.inprogress.setTextColor( ContextCompat.getColor(getActivity(), R.color.black));
                        binding.downloads.setTextColor( ContextCompat.getColor(getActivity(), R.color.black));}
        });




    }

    private void showFragment(Fragment fragmentToShow, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (inprogress.isAdded()) transaction.hide(inprogress);
        if (completed.isAdded()) transaction.hide(completed);
        if (downloads.isAdded()) transaction.hide(downloads);

        if (!fragmentToShow.isAdded()) {
            transaction.add(R.id.f_fragment_container, fragmentToShow, tag);
        } else {
            transaction.show(fragmentToShow);
        }

        transaction.commit();
    }
    
}



//it worked but what happened is when these three fragments are inside one fragment which is controlled by bottom navigation so when i first open the app it worked and the listview inside the inprogress fragment shown and even after i switch between those three fragments it's fine i mean i still see the lists inside the progress fragment but the problem is after i navigate to another main fragment you get me right ,the one who've been controlled by the bottom navigation after i got to one of those and came back to this parent fragment and click on the buttons to get the lists there gone



