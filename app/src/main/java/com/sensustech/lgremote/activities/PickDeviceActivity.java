package com.sensustech.lgremote.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.connectsdk.device.ConnectableDevice;
import com.connectsdk.discovery.DiscoveryManager;
import com.connectsdk.discovery.DiscoveryManagerListener;
import com.connectsdk.service.DeviceService;
import com.connectsdk.service.command.ServiceCommandError;

import com.connectsdk.service.webos.WebOSTVServiceSocketClient;
import com.sensustech.lgremote.SingletonTV;
import com.sensustech.lgremote.adapters.DevicesAdapter;
import com.sensustech.lgremote.models.DeviceModel;
import com.sensustech.lgremote.R;
import com.sensustech.lgremote.utils.AppPreferences;
import com.sensustech.lgremote.utils.ItemClickSupport;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PickDeviceActivity extends AppCompatActivity implements DiscoveryManagerListener{
    private DevicesAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private final ArrayList<DeviceModel> devices = new ArrayList<DeviceModel>();
    private final ArrayList<ConnectableDevice> found_devices = new ArrayList<ConnectableDevice>();
    DeviceModel searching = new DeviceModel();
    Timer mTimer;
    connectTask connectTask;
    private TextView search_status;
    private boolean searching_status = true;
    final DeviceModel no_devices = new DeviceModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_device);
        search_status = findViewById(R.id.search_status);
        searching.item_type = 1;
        no_devices.item_type = 2;
        devices.add(searching);
        devices.add(no_devices);
        recyclerView = findViewById(R.id.recycler_devices);
        recyclerView.setHasFixedSize(true);
        adapter = new DevicesAdapter(this, devices);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if(position != RecyclerView.NO_POSITION && position >= 0 && position < devices.size() && devices.get(position).item_type == 0) {
                    DeviceModel device = devices.get(position);
                    final SingletonTV mTV = com.sensustech.lgremote.SingletonTV.getInstance();
                    mTV.setTV(found_devices.get(position));
                    AppPreferences.getInstance(PickDeviceActivity.this).saveData("deviceID", device.deviceID);
                    AppPreferences.getInstance(PickDeviceActivity.this).saveData("deviceIp", device.deviceIp);
                    AppPreferences.getInstance(PickDeviceActivity.this).saveData("deviceName", device.name);
                    mTV.getTV().setPairingType(DeviceService.PairingType.PIN_CODE);
                    mTV.getTV().connect();
                    mTimer = new Timer();
                    connectTask = new connectTask();
                    mTimer.schedule(connectTask, 1000);
                }
            }
        });
        DiscoveryManager.init(getApplicationContext());
        startSearch();
    }

    public void backClick(View view) {
        finish();
    }

    private void startSearch() {
        DiscoveryManager.getInstance().registerDefaultDeviceTypes();
        DiscoveryManager.getInstance().setPairingLevel(DiscoveryManager.PairingLevel.ON);
        DiscoveryManager.getInstance().addListener(this);
        DiscoveryManager.getInstance().start();
    }

    public void onDeviceAdded(DiscoveryManager manager, ConnectableDevice device) {
        if (device != null && device.getConnectedServiceNames() != null && device.getModelName() != null ) {
            if (device.getModelName().toLowerCase().contains("lg") && device.getConnectedServiceNames().toLowerCase().contains("webos")) {
                if (searching_status == true) {
                    search_status.setText("We found your devices!");
                    devices.remove(0);
                    adapter.notifyItemRemoved(0);
                    devices.remove(0);
                    adapter.notifyItemRemoved(0);
                    searching_status = false;
                }
                DeviceModel dm = new DeviceModel();
                dm.name = device.getFriendlyName();
                dm.series = device.getModelName();
                dm.deviceIp = device.getIpAddress();
                dm.deviceID = device.getId();
                if (!devices.contains(dm)) {
                    devices.add(dm);
                    adapter.notifyDataSetChanged();
                    found_devices.add(device);
                }
            }
        }
    }

    @Override
    public void onDeviceUpdated(DiscoveryManager manager, ConnectableDevice device) {

    }

    @Override
    public void onDeviceRemoved(DiscoveryManager manager, ConnectableDevice device) {
        for (DeviceModel dm : devices) {
            if (dm.name != null) {
                if (dm.name.equals(device.getFriendlyName())) {
                    devices.remove(dm);
                    adapter.notifyDataSetChanged();
                    if (!devices.contains(dm)) {
                        search_status.setText("No device found");
                        devices.add(no_devices);
                        devices.add(searching);
                        searching_status = true;
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void onDiscoveryFailed(DiscoveryManager manager, ServiceCommandError error) {

    }

    class connectTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (WebOSTVServiceSocketClient.getResponseMessage().contains("registered")) {
                        Toast.makeText(getApplicationContext(), "Device already connected", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        startActivity(new Intent(PickDeviceActivity.this, AproveActivity.class));
                    }
                }
            });
        }
    }

}