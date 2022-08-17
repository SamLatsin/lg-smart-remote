package com.sensustech.lgremote.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sensustech.lgremote.models.DeviceModel;
import com.sensustech.lgremote.R;

import java.util.ArrayList;

public class DevicesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "DevicesAdapter";
    private final Activity activity;
    private final ArrayList<DeviceModel> devices;
    private static final int VIEW_TYPE_DEVICES = 1;
    private static final int VIEW_TYPE_PROGRESS_BAR = 2;
    private static final int VIEW_TYPE_NO_DEVICE_FOUND = 3;

    public DevicesAdapter(Activity activity, ArrayList<DeviceModel> devices) {
        this.activity = activity;
        this.devices = devices;
    }

    @Override
    public int getItemViewType(int position) {
        if (devices.get(position).getViewType() == 0) {
            return VIEW_TYPE_DEVICES;
        } else if (devices.get(position).getViewType() == 1) {
            return VIEW_TYPE_PROGRESS_BAR;
        } else if (devices.get(position).getViewType() == 2) {
            return VIEW_TYPE_NO_DEVICE_FOUND;
        }
        else {
            return VIEW_TYPE_NO_DEVICE_FOUND;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_DEVICES) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_item, parent, false);
            return new DevicesViewHolder(view);
        }
        else if (viewType == VIEW_TYPE_PROGRESS_BAR) {
            view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.searching_device_item, parent, false);
            return new ProgressViewHolder(view);
        } else if (viewType == VIEW_TYPE_NO_DEVICE_FOUND) {
            view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.no_device_found_item, parent, false);
            return new NoDevicesViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_DEVICES:
                ((DevicesViewHolder) holder).tv_name.setText(devices.get(position).name);
        }

    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public static class DevicesViewHolder extends RecyclerView.ViewHolder {
        private final TextView tv_name;
        public DevicesViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class NoDevicesViewHolder extends RecyclerView.ViewHolder {
        public NoDevicesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
