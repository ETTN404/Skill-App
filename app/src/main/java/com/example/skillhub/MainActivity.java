package com.example.skillhub;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.skillhub.databinding.Activitymain1Binding;
import com.example.skillhub.databinding.Activitymain1Binding;

public class MainActivity extends AppCompatActivity {
    Activitymain1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=Activitymain1Binding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        NavController navController = ((NavHostFragment) getSupportFragmentManager()
                .findFragmentById(binding.fragmentContainer.getId()))
                .getNavController();

        NavigationUI.setupWithNavController(binding.bottomNav, navController);

    }


}