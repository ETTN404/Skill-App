package com.example.skillhub.View.Fragments_container.Profile_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.skillhub.Model.models.Courses;
import com.example.skillhub.Model.models.User;
import com.example.skillhub.R;
import com.example.skillhub.Utils.Text_Format;
import com.example.skillhub.Utils.pictureFormat;
import com.example.skillhub.ViewModel.CourseViewModel;
import com.example.skillhub.databinding.ProParentBinding;

public class F_Profile extends Fragment {
    public F_Profile(){}
    FragmentManager fragmentManager;
    Fragment aboutme,certification;
    CourseViewModel viewModel;
    ProParentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=ProParentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager=getParentFragmentManager();
        aboutme=fragmentManager.findFragmentByTag("aboutme");
        certification=fragmentManager.findFragmentByTag("certification");
        if(aboutme==null)aboutme=new f_about_me();
        if (certification==null)certification=new f_certifications();


        viewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        viewModel.userData(requireContext()).observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (getContext() != null && user != null && user.profile != null && !user.profile.trim().isEmpty()) {
                    String fullImageUrl = pictureFormat.getCorrect_path(user.profile);
                    Glide.with(requireContext())
                            .load(fullImageUrl)
                            .into(binding.profilePic);
                    Toast.makeText(requireContext(), ""+user.profile, Toast.LENGTH_SHORT).show();
                } else {
                    // Optionally show a default image or hide the ImageView
                    binding.profilePic.setImageResource(R.drawable.img);
                }

                if (user != null) {
                    binding.username.setText(user.username != null ? user.username : "N/A");
                    binding.proffesion.setText(user.profession != null ? user.profession : "N/A");
                    binding.status.setText(user.user_id + "");
                } else {
                    binding.username.setText("N/A");
                    binding.proffesion.setText("N/A");
                    binding.status.setText("N/A");
                }



            }
        });



        if(savedInstanceState==null) showFragment(aboutme,"aboutme");
        binding.aboutme.setOnClickListener(v->{
            showFragment(aboutme,"aboutme");
            {
                binding.aboutme.setBackgroundResource(R.drawable.bg_white_curve_rectangle);
                binding.certification.setBackgroundResource(R.drawable.bg_your_course_buttons_color);
                binding.aboutme.setTextColor( ContextCompat.getColor(getActivity(), R.color.green_blue));
                binding.certification.setTextColor( ContextCompat.getColor(getActivity(), R.color.white));}
        });
        binding.certification.setOnClickListener(v->{
            showFragment(certification,"certification");
            {
                binding.certification.setBackgroundResource(R.drawable.bg_white_curve_rectangle);
                binding.aboutme.setBackgroundResource(R.drawable.bg_your_course_buttons_color);
                binding.certification.setTextColor( ContextCompat.getColor(getActivity(), R.color.green_blue));
                binding.aboutme.setTextColor( ContextCompat.getColor(getActivity(), R.color.white));}
        });
    }

    private void showFragment(Fragment fragment, String tag)
    {

        FragmentTransaction transaction=fragmentManager.beginTransaction();
        if(aboutme.isAdded())transaction.hide(aboutme);
        if(certification.isAdded())transaction.hide(certification);
        if(!fragment.isAdded()){ transaction.add(binding.profileFragmentContainer.getId(),fragment,tag);}
        else{
            transaction.show(fragment);
        }
        binding.editprofile.setOnClickListener(v->{
            showCustomDialog();
        });
        transaction.commit();

    }











    public void showCustomDialog() {
        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialogu_box, null);

        // Reference views from custom layout
        TextView title = dialogView.findViewById(R.id.dialog_title);
        title.setText("Enter Info");

        EditText inputPhone = dialogView.findViewById(R.id.input_phone);
        EditText inputEmail = dialogView.findViewById(R.id.input_email);
        EditText inputDob = dialogView.findViewById(R.id.input_dob);
        Spinner inputGender = dialogView.findViewById(R.id.input_gender);
        EditText inputProfession = dialogView.findViewById(R.id.input_profession);
        EditText inputProfilePic = dialogView.findViewById(R.id.input_profile_pic);
        Button btnSubmit = dialogView.findViewById(R.id.btnSubmit);

        // Setup spinner data (example gender options)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.gender_array,  // Define this in your strings.xml
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputGender.setAdapter(adapter);

        // Optional: setup Date of Birth click to show a date picker dialog
        inputDob.setOnClickListener(v -> {
            // Implement DatePickerDialog and set selected date to inputDob
        });

        // Build the dialog without default buttons because you have a custom submit button
        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .create();

       btnSubmit.setOnClickListener(v -> {
            // Collect input values on submit
            String phone = inputPhone.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String dob = inputDob.getText().toString().trim();
            String gender = (String) inputGender.getSelectedItem();
            String profession = inputProfession.getText().toString().trim();
            String profilePicUrl = inputProfilePic.getText().toString();
           Toast.makeText(requireContext(), ""+profilePicUrl, Toast.LENGTH_SHORT).show();
            String username="";

           viewModel = new ViewModelProvider(this).get(CourseViewModel.class);
           viewModel.editProfile(username,phone,email,dob,gender,profession,profilePicUrl,requireContext()).observe(getViewLifecycleOwner(), new Observer<Integer>() {
                       @Override
                       public void onChanged(Integer integer) {
                           Toast.makeText(getContext(), "succussfully updated", Toast.LENGTH_SHORT).show();
                       }
                   });

                   dialog.dismiss();
        });

        dialog.show();
    }

}
