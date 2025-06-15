package com.example.skillhub.View.Fragments_container.course_page;

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
import com.example.skillhub.databinding.CourseParentBinding;

public class F_YourCourse extends Fragment {

    private CourseParentBinding binding;
    private FragmentManager fragmentManager;

    private static final String TAG_INPROGRESS = "inprogress";
    private static final String TAG_COMPLETED = "completed";
    private static final String TAG_DOWNLOADS = "downloads";

    private String currentTag = TAG_INPROGRESS;

    public F_YourCourse() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CourseParentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager = getParentFragmentManager();

        // Restore selected fragment tag
        if (savedInstanceState != null) {
            currentTag = savedInstanceState.getString("selected_fragment", TAG_INPROGRESS);
        }

        showFragmentByTag(currentTag);

        binding.inprogress.setOnClickListener(v -> {
            currentTag = TAG_INPROGRESS;
            showFragmentByTag(currentTag);
            updateButtonStyles(currentTag);
        });

        binding.completed.setOnClickListener(v -> {
            currentTag = TAG_COMPLETED;
            showFragmentByTag(currentTag);
            updateButtonStyles(currentTag);
        });

        binding.downloads.setOnClickListener(v -> {
            currentTag = TAG_DOWNLOADS;
            showFragmentByTag(currentTag);
            updateButtonStyles(currentTag);
        });

        updateButtonStyles(currentTag);
    }

    private void showFragmentByTag(String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Fragment inprogress = fragmentManager.findFragmentByTag(TAG_INPROGRESS);
        Fragment completed = fragmentManager.findFragmentByTag(TAG_COMPLETED);
        Fragment downloads = fragmentManager.findFragmentByTag(TAG_DOWNLOADS);

        if (inprogress != null) transaction.hide(inprogress);
        if (completed != null) transaction.hide(completed);
        if (downloads != null) transaction.hide(downloads);

        Fragment fragmentToShow = fragmentManager.findFragmentByTag(tag);

        if (fragmentToShow == null) {
            switch (tag) {
                case TAG_INPROGRESS:
                    fragmentToShow = new f_InProgress();
                    break;
                case TAG_COMPLETED:
                    fragmentToShow = new f_Completed();
                    break;
                case TAG_DOWNLOADS:
                    fragmentToShow = new f_Downloads();
                    break;
            }
            if (fragmentToShow != null) {
                transaction.add(R.id.f_fragment_container, fragmentToShow, tag);
            }
        } else {
            transaction.show(fragmentToShow);
        }

        transaction.commit();
    }

    private void updateButtonStyles(String selectedTag) {
        int selectedColor = ContextCompat.getColor(requireContext(), R.color.white);
        int unselectedColor = ContextCompat.getColor(requireContext(), R.color.black);

        binding.inprogress.setBackgroundResource(
                selectedTag.equals(TAG_INPROGRESS) ? R.drawable.bg_green_blue_curve_rectangle : R.drawable.bg_your_course_buttons_color
        );
        binding.inprogress.setTextColor(selectedTag.equals(TAG_INPROGRESS) ? selectedColor : unselectedColor);

        binding.completed.setBackgroundResource(
                selectedTag.equals(TAG_COMPLETED) ? R.drawable.bg_green_blue_curve_rectangle : R.drawable.bg_your_course_buttons_color
        );
        binding.completed.setTextColor(selectedTag.equals(TAG_COMPLETED) ? selectedColor : unselectedColor);

        binding.downloads.setBackgroundResource(
                selectedTag.equals(TAG_DOWNLOADS) ? R.drawable.bg_green_blue_curve_rectangle : R.drawable.bg_your_course_buttons_color
        );
        binding.downloads.setTextColor(selectedTag.equals(TAG_DOWNLOADS) ? selectedColor : unselectedColor);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("selected_fragment", currentTag);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Clean up to prevent memory leaks
    }
}
