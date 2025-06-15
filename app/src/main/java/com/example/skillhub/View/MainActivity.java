package com.example.skillhub.View;

import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.skillhub.Broad;
import com.example.skillhub.R;
import com.example.skillhub.Utils.SharedPrefs;
import com.example.skillhub.View.Fragments_container.Login;
import com.example.skillhub.View.Fragments_container.SignIn;
import com.example.skillhub.View.Fragments_container.course_page.F_YourCourse;
import com.example.skillhub.View.Fragments_container.home_page.F_Home;
import com.example.skillhub.View.Fragments_container.notification_page.F_Notification;
import com.example.skillhub.View.Fragments_container.search_page.F_Search;
import com.example.skillhub.View.Fragments_container.Profile_page.F_Profile;
import com.example.skillhub.databinding.Activitymain1Binding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private Fragment homeFragment;
    private Fragment login;
    private Fragment courseFragment;
    private Fragment searchFragment;
    private Fragment messageFragment;
    private Fragment profileFragment;

    private Fragment activeFragment;
    Activitymain1Binding binding;

    // Icon references
    private ImageView iconHome, iconFiles, iconNotifications, iconSearch, iconProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activitymain1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Fullscreen immersive UI
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.transparent));
        }

        // Initialize fragments
        homeFragment = new F_Home();
        courseFragment = new F_YourCourse();
        searchFragment = new F_Search();
        messageFragment = new F_Notification();
        profileFragment = new F_Profile();
        login = new Login();

        if (SharedPrefs.getUserId(getApplicationContext()) == 0) {
            // User not logged in
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.relativeLayout, profileFragment, "5").hide(profileFragment)
                    .add(R.id.relativeLayout, messageFragment, "4").hide(messageFragment)
                    .add(R.id.relativeLayout, searchFragment, "3").hide(searchFragment)
                    .add(R.id.relativeLayout, courseFragment, "2").hide(courseFragment)
                    .add(R.id.relativeLayout, homeFragment, "1").hide(homeFragment)
                    .add(binding.drawerLayout.getId(), login, "login")
                    .commit();
            activeFragment = login;
        } else {
            // User already logged in
            setupDrawer();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.relativeLayout, profileFragment, "5").hide(profileFragment)
                    .add(R.id.relativeLayout, messageFragment, "4").hide(messageFragment)
                    .add(R.id.relativeLayout, searchFragment, "3").hide(searchFragment)
                    .add(R.id.relativeLayout, courseFragment, "2").hide(courseFragment)
                    .add(binding.drawerLayout.getId(), login, "login").hide(login)
                    .add(R.id.relativeLayout, homeFragment, "1")
                    .commit();

            activeFragment = homeFragment;
            setupBottomNavigation();
        }
    }

    private void setupDrawer() {
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = binding.drawerLayout;

        setSupportActionBar(binding.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Log.e("Drawer", "Home clicked");
            } else if (id == R.id.nav_settings) {
                Log.e("Drawer", "Settings clicked");
            } else if (id == R.id.nav_logout) {
                Toast.makeText(this, "LogOut successful", Toast.LENGTH_SHORT).show();
                SharedPrefs.deleteUserId(this);
                switchTo(login);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void setupBottomNavigation() {
        iconHome = findViewById(R.id.icon_home);
        iconFiles = findViewById(R.id.icon_files);
        iconNotifications = findViewById(R.id.icon_notifications);
        iconSearch = findViewById(R.id.icon_search);
        iconProfile = findViewById(R.id.icon_profile);

        setActiveIcon(R.id.nav_home); // Default

        findViewById(R.id.nav_home).setOnClickListener(v -> {
            switchTo(homeFragment);
            setActiveIcon(R.id.nav_home);
        });
        findViewById(R.id.nav_files).setOnClickListener(v -> {
            switchTo(courseFragment);
            setActiveIcon(R.id.nav_files);
        });
        findViewById(R.id.nav_notifications).setOnClickListener(v -> {
            switchTo(messageFragment);
            setActiveIcon(R.id.nav_notifications);
        });
        findViewById(R.id.nav_search).setOnClickListener(v -> {
            switchTo(searchFragment);
            setActiveIcon(R.id.nav_search);
        });
        findViewById(R.id.nav_profile).setOnClickListener(v -> {
            switchTo(profileFragment);
            setActiveIcon(R.id.nav_profile);
        });
    }

    public void showMainFragments() {
        setupDrawer();
        setupBottomNavigation();

        getSupportFragmentManager().beginTransaction()
                .remove(login)
                .show(homeFragment)
                .commit();

        activeFragment = homeFragment;
        setActiveIcon(R.id.nav_home);
    }

    private void switchTo(Fragment selected) {
        if (selected != null && selected != activeFragment) {
            getSupportFragmentManager().beginTransaction()
                    .hide(activeFragment)
                    .show(selected)
                    .commit();
            activeFragment = selected;
        }
    }

    private void setActiveIcon(int selectedId) {
        iconHome.setImageResource(R.drawable.svg_home);
        iconFiles.setImageResource(R.drawable.svg_file);
        iconNotifications.setImageResource(R.drawable.svg_message);
        iconSearch.setImageResource(R.drawable.svg_search);
        iconProfile.setImageResource(R.drawable.svg_profile);

        if (selectedId == R.id.nav_home) {
            iconHome.setImageResource(R.drawable.svg_home_f);
        } else if (selectedId == R.id.nav_files) {
            iconFiles.setImageResource(R.drawable.svg_file_f);
        } else if (selectedId == R.id.nav_notifications) {
            iconNotifications.setImageResource(R.drawable.svg_message_f);
        } else if (selectedId == R.id.nav_search) {
            iconSearch.setImageResource(R.drawable.svg_search_f);
        } else if (selectedId == R.id.nav_profile) {
            iconProfile.setImageResource(R.drawable.svg_profile_f);
        }
    }

    // Broadcast receiver for connectivity
    private Broad networkChangeReceiver = new Broad();

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkChangeReceiver);
    }
}
