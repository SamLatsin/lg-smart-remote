package com.sensustech.lgremote.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.sensustech.lgremote.R;
import com.sensustech.lgremote.utils.AppPreferences;

public class SettingsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    public static final String TAG = "SettingsAdapter";
    private final Activity activity;
    private static final int VIEW_TYPE_SECTION = 1;
    private static final int VIEW_TYPE_ITEM = 2;
    private static final int VIEW_TYPE_PREMIUM = 3;
    private static final int VIEW_TYPE_SWITCH = 4;

    public SettingsAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_PREMIUM;
        } else if (position == 1 || position == 5) {
            return VIEW_TYPE_SECTION;
        } else if (position == 3 || position == 4) {
            return VIEW_TYPE_SWITCH;
        }
        else {
            return VIEW_TYPE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_PREMIUM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.premium_upgrade, parent, false);
            return new PremiumViewHolder(view);
        } else if (viewType == VIEW_TYPE_SECTION) {
            view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.section_item, parent, false);
            return new SectionViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.settings_item_transition, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == VIEW_TYPE_SWITCH) {
            view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.settings_item_switch, parent, false);
            return new SwitchViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_SECTION:
                if (position == 1) {
                    ((SectionViewHolder) holder).title.setText("CONNECT");
                } else if (position == 5) {
                    ((SectionViewHolder) holder).title.setText("CUSTOMER SUPPORT");
                }
                break;
            case VIEW_TYPE_SWITCH:
                if (position == 3) {
                    ((SwitchViewHolder) holder).title.setText("Haptic Feedback");
                    ((SwitchViewHolder) holder).sw.setChecked(AppPreferences.getInstance(activity).getBoolean("hapticFeedback"));
                } else if (position == 4) {
                    ((SwitchViewHolder) holder).title.setText("Haptic Feedback for APPLE Watch");
                    ((SwitchViewHolder) holder).sw.setChecked(AppPreferences.getInstance(activity).getBoolean("hapticFeedbackAppleWatch"));
                }
                break;
            case VIEW_TYPE_ITEM:
                if (position == 2){
                    ((ItemViewHolder) holder).title.setText("Connect the device");
                } else if (position == 6){
                    ((ItemViewHolder) holder).title.setText("Rate Us");
                } else if (position == 7){
                    ((ItemViewHolder) holder).title.setText("Help Center");
                } else if (position == 8){
                    ((ItemViewHolder) holder).title.setText("Contact us");
                } else if (position == 9){
                    ((ItemViewHolder) holder).title.setText("Share App");
                } else if (position == 10){
                    ((ItemViewHolder) holder).title.setText("Follow us on Facebook");
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class SectionViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_name);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "ItemViewHolder";
        private final TextView title;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.setting_name);
        }
    }

    public static class SwitchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "ItemViewHolder";
        private final TextView title;
        private final Switch sw;
        private final ConstraintLayout sw_constr;
        public SwitchViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.setting_name);
            sw = itemView.findViewById(R.id.switch1);
            sw_constr = itemView.findViewById(R.id.switchConstraint);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }

    public static class PremiumViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "PremiumViewHolder";
        public PremiumViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
