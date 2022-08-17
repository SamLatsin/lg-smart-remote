package com.sensustech.lgremote.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sensustech.lgremote.activities.PickDeviceActivity;
import com.sensustech.lgremote.activities.PremiumActivity;
import com.sensustech.lgremote.adapters.SettingsAdapter;
import com.sensustech.lgremote.R;
import com.sensustech.lgremote.utils.AppPreferences;
import com.sensustech.lgremote.utils.ItemClickSupport;

public class SettingsFragment extends Fragment {
    private SettingsAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;

    public SettingsFragment() {
    }
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        recyclerView = root.findViewById(R.id.rv_settings);
        recyclerView.setHasFixedSize(true);
        adapter = new SettingsAdapter(getActivity());
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if (position == 0) {
                    startActivity(new Intent(SettingsFragment.this.getActivity(), PremiumActivity.class));
                }
                else if (position == 2) {
                    startActivity(new Intent(SettingsFragment.this.getActivity(), PickDeviceActivity.class));
                }
                else if (position == 3) {
                    Switch sw = v.findViewById(R.id.switch1);
                    sw.setChecked(!sw.isChecked());
                    AppPreferences.getInstance(SettingsFragment.this.getActivity()).saveData("hapticFeedback", sw.isChecked());
                }
                else if (position == 4) {
                    Switch sw = v.findViewById(R.id.switch1);
                    sw.setChecked(!sw.isChecked());
                    AppPreferences.getInstance(SettingsFragment.this.getActivity()).saveData("hapticFeedbackAppleWatch", sw.isChecked());
                }
                else if (position == 6) {
                    openURL("https://play.google.com/store/apps/details?id=com.sensustech.lgremote");
                }
                else if (position == 8) {
                    openURL("mailto:support@sensustech.com");
                }
                else if (position == 9) {
                    shareAction();
                }
            }
        });
        return root;
    }

    private void openURL(String url) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }catch (ActivityNotFoundException e) {
            Toast.makeText(this.getContext(), "You don't have an app installed to open this URL.", Toast.LENGTH_LONG).show();
        }
    }

    private void shareAction() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "LG Remote allows you to control your LG from Android device. https://play.google.com/store/apps/details?id=com.sensustech.lgremote");
        Intent intent = Intent.createChooser(shareIntent, "Share");
        startActivity(intent);
    }
}
