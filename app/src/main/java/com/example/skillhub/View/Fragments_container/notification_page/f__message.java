package com.example.skillhub.View.Fragments_container.notification_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.skillhub.Adaptors.Notification_listAdapter;
import com.example.skillhub.databinding.NotiMessageBinding;

public class f__message extends Fragment {
    public f__message(){}
 String[] strings={"m","e","s"};
    Notification_listAdapter adapter;
    NotiMessageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=NotiMessageBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new Notification_listAdapter(requireContext(),strings);
        binding.listItem.setAdapter(adapter);
    }
}
