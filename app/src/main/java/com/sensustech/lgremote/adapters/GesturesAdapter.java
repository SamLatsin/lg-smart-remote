package com.sensustech.lgremote.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sensustech.lgremote.R;

public class GesturesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    public static final String TAG = "GesturesAdapter";
    private final Activity activity;
    private static final int VIEW_TYPE_SECTION = 1;
    private static final int VIEW_TYPE_ITEM = 2;
    private final String[] titles;

    public GesturesAdapter(Activity activity, String[] titles) {
        this.activity = activity;
        this.titles = titles;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 8) {
            return VIEW_TYPE_SECTION;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_SECTION) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item, parent, false);
            return new SectionViewHolder(view);
        } else if (viewType == VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.gesture_item, parent, false);
            return new ItemViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_SECTION:
                ((SectionViewHolder) holder).title.setText(titles[position]);
                break;
            case VIEW_TYPE_ITEM:
                if (position == 1){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.one_finger_tap);
                    ((ItemViewHolder) holder).command.setText("CH List");
                } else if (position == 2){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.one_finger_double_tap);
                    ((ItemViewHolder) holder).command.setText("MUTE");
                } else if (position == 3){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.one_finger_hold_tap);
                    ((ItemViewHolder) holder).command.setText("SOURCE");
                } else if (position == 4){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.one_finger_swipe_up);
                    ((ItemViewHolder) holder).command.setText("VOLUME UP");
                } else if (position == 5){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.one_finger_swipe_down);
                    ((ItemViewHolder) holder).command.setText("VOLUME DOWN");
                } else if (position == 6){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.one_finger_swipe_left);
                    ((ItemViewHolder) holder).command.setText("CHANNEL DOWN");
                } else if (position == 7){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.one_finger_swipe_right);
                    ((ItemViewHolder) holder).command.setText("CHANNEL UP");
                } else if (position == 9){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.two_finger_tap);
                    ((ItemViewHolder) holder).command.setText("INFO");
                } else if (position == 10){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.two_finger_double_tap);
                    ((ItemViewHolder) holder).command.setText("Not set");
                } else if (position == 11){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.two_finger_hold_tap);
                    ((ItemViewHolder) holder).command.setText("Not set");
                } else if (position == 12){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.two_finger_swipe_up);
                    ((ItemViewHolder) holder).command.setText("A");
                } else if (position == 13){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.two_finger_swipe_down);
                    ((ItemViewHolder) holder).command.setText("B");
                } else if (position == 14){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.two_finger_swipe_left);
                    ((ItemViewHolder) holder).command.setText("C");
                } else if (position == 15){
                    ((ItemViewHolder) holder).image.setBackgroundResource(R.drawable.two_finger_swipe_right);
                    ((ItemViewHolder) holder).command.setText("D");
                }
                ((ItemViewHolder) holder).title.setText(titles[position]);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 16;
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
        private final TextView command;
        private final ImageView image;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_name);
            command = itemView.findViewById(R.id.command);
            image = itemView.findViewById(R.id.imageView5);
        }
    }
}
