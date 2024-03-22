package com.muratcangzm.blacktooth.ui.fragments;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.muratcangzm.blacktooth.databinding.HomeFragmentLayoutBinding;
import com.muratcangzm.blacktooth.utils.PermissionManager;
import com.muratcangzm.blacktooth.viewmodels.HomeViewModel;

import java.util.ArrayList;


import dagger.hilt.android.AndroidEntryPoint;

@RequiresApi(api = Build.VERSION_CODES.S)
@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private HomeFragmentLayoutBinding binding = null;

    public HomeViewModel homeViewModel;
    private final String TAG = "HomeFragment";
    private final String[] permissions = new String[]{Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = HomeFragmentLayoutBinding.inflate(inflater, container, false);

        PermissionManager permissionManager = new PermissionManager(requireActivity());

        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        permissionManager.requestPermissions(() ->
                        homeViewModel.getDiscoveredDevicesLiveData().observe(getViewLifecycleOwner(), this::updateDeviceList)
                , permissions);


        return binding.getRoot();
    }

    private void updateDeviceList(ArrayList<BluetoothDevice> devices) {

        devices.forEach(device -> {

            String address = device.getAddress();
            Log.d(TAG, "updateDeviceList: " + address);
        });

        //TODO: Create a recyclerView
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
