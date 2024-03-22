package com.muratcangzm.blacktooth.viewmodels;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<BluetoothDevice>> discoveredDevicesLiveData = new MutableLiveData<>();
    private final BluetoothAdapter bluetoothAdapter;
    @SuppressLint("StaticFieldLeak")
    private final Context context;


    @Inject
    public HomeViewModel(BluetoothAdapter _bluetoothAdapter, Context _context) {

        this.bluetoothAdapter = _bluetoothAdapter;
        this.context = _context;

        startBluetoothScanning();

    }

    private final BroadcastReceiver bluetoothDiscoveryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device != null) {
                    ArrayList<BluetoothDevice> discoveredDevices = discoveredDevicesLiveData.getValue();
                    if (discoveredDevices == null) {
                        discoveredDevices = new ArrayList<>();
                    }
                    discoveredDevices.add(device);
                    discoveredDevicesLiveData.setValue(discoveredDevices);

                }
            }
        }
    };

    public LiveData<ArrayList<BluetoothDevice>> getDiscoveredDevicesLiveData() {
        return discoveredDevicesLiveData;
    }

    private void startBluetoothScanning() {

        if (context.checkSelfPermission(Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
            context.registerReceiver(bluetoothDiscoveryReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            bluetoothAdapter.startDiscovery();
        } else
            Log.e("HomeViewModel", "Location permission is not granted");


    }


    @Override
    protected void onCleared() {
        super.onCleared();
        context.unregisterReceiver(bluetoothDiscoveryReceiver);
    }
}
