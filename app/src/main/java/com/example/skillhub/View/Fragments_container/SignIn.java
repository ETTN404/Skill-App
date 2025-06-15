package com.example.skillhub.View.Fragments_container;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.skillhub.R;
import com.example.skillhub.Utils.SharedPrefs;
import com.example.skillhub.View.MainActivity;
import com.example.skillhub.ViewModel.CourseViewModel;
import com.example.skillhub.databinding.SignInBinding;

public class SignIn extends Fragment {

    public SignIn() {
        // Required empty public constructor
    }
    SignInBinding binding;
    CourseViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=SignInBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.signupButton.setOnClickListener(v->{
            String name="",email="",password="";
            name=binding.nameEditText.getText().toString()!=null?binding.nameEditText.getText().toString():"";
            email=binding.emailEditText.getText().toString()!=null?binding.emailEditText.getText().toString():"";
            password=binding.passwordEditText.toString()!=null?binding.passwordEditText.toString():"";

            viewModel = new ViewModelProvider(this).get(CourseViewModel.class);
            viewModel.registerUser(name,email ,password ).observe(getViewLifecycleOwner(), result -> {
                if(result != null) {
                    if(result == 1){
                        binding.error.setText("registration successful");
                        binding.error.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
                        requireActivity().getSupportFragmentManager().popBackStack();




                    } else {

                        binding.error.setText("username or email exist !");
                        binding.error.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));

                    }
                } else {
                    Toast.makeText(requireActivity(), "Network Error!", Toast.LENGTH_SHORT).show();
                }
            });




        });
        binding.signinText.setOnClickListener(v->{
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }
}