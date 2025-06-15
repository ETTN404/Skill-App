package com.example.skillhub.View.Fragments_container;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skillhub.R;
import com.example.skillhub.Utils.SharedPrefs;
import com.example.skillhub.View.MainActivity;
import com.example.skillhub.ViewModel.CourseViewModel;
import com.example.skillhub.databinding.LoginBinding;

public class Login extends Fragment {

    private CourseViewModel viewModel;
    private LoginBinding binding;

    public Login() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = LoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        binding.signupText.setOnClickListener(v -> {
            Fragment signUpFragment = new SignIn();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(binding.loginMain.getId(), signUpFragment)
                    .addToBackStack(null)
                    .commit();
        });

        binding.loginButton.setOnClickListener(v -> {
            String email = binding.emailEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();

            if (email.isEmpty() && password.isEmpty()) {
                binding.error.setText("Please enter both email and password");
                binding.error.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                return;
            }

            viewModel.Login(email, password).observe(getViewLifecycleOwner(), result -> {
                if (result != null && result.status) {
                    SharedPrefs.setUserId(result.id, requireContext());
                    ((MainActivity) requireActivity()).showMainFragments();
                    requireActivity().recreate();
                } else {
                    binding.error.setText("Username or password incorrect!");
                    binding.error.setTextColor(ContextCompat.getColor(requireContext(), R.color.red));
                }
            });
        });
    }
}
