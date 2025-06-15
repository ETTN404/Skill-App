package com.example.skillhub.View;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.skillhub.R;
import com.example.skillhub.databinding.ActivityStreamPageBinding;

public class Stream_Page extends AppCompatActivity {
    ActivityStreamPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStreamPageBinding.inflate(getLayoutInflater());

        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

    }

    private void setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            // You can handle padding adjustments here
            return WindowInsetsCompat.CONSUMED;
        });
    }

    private void playVideo() {
        String videoUrl = "https://www.html5rocks.com/en/tutorials/video/basics/devstories.webm"; // your video URL
        Uri uri = Uri.parse(videoUrl);
        binding.videoView.setVideoURI(uri);
        binding.videoView.setOnPreparedListener(mp -> binding.videoView.start());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binding.videoView.isPlaying()) {
            binding.videoView.stopPlayback();
        }
    }
}