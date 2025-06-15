package com.example.skillhub.View.Fragments_container.notification_page;

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
import com.example.skillhub.databinding.NotiParentBinding;

public class F_Notification extends Fragment
{
    NotiParentBinding binding;
    FragmentManager fragmentManager;
    Fragment notifications, messages;
    public F_Notification(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding=NotiParentBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentManager =getParentFragmentManager();
        notifications=fragmentManager.findFragmentByTag("notification");
        messages=fragmentManager.findFragmentByTag("messages");

        if (notifications == null) notifications = new f__notification();
        if (messages == null) messages = new f__message();

        if(savedInstanceState == null)showFragment(notifications,"notification");


        binding.messages.setOnClickListener(v->{
            showFragment(messages, "messages");
            {
                binding.messages.setBackgroundResource(R.drawable.bg_white_curve_rectangle);
                binding.notification.setBackgroundResource(R.drawable.bg_your_course_buttons_color);
                binding.messages.setTextColor( ContextCompat.getColor(getActivity(), R.color.green_blue));
                binding.notification.setTextColor( ContextCompat.getColor(getActivity(), R.color.white));}
        });
        binding.notification.setOnClickListener(v->{
            showFragment(notifications, "notification");
            {
                binding.notification.setBackgroundResource(R.drawable.bg_white_curve_rectangle);
                binding.messages.setBackgroundResource(R.drawable.bg_your_course_buttons_color);
                binding.notification.setTextColor( ContextCompat.getColor(getActivity(), R.color.green_blue));
                binding.messages.setTextColor( ContextCompat.getColor(getActivity(), R.color.white));}
        });


    }

    private void showFragment(Fragment fragmentToShow, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (messages.isAdded()) transaction.hide(messages);
        if (notifications.isAdded()) transaction.hide(notifications);

        if (!fragmentToShow.isAdded()) {
            transaction.add(binding.fFragmentContainerNotification.getId(), fragmentToShow, tag);
        } else {
            transaction.show(fragmentToShow);
        }

        transaction.commit();
    }

}










