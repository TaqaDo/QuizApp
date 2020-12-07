package com.talgar.ui.settingsFragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talgar.App;
import com.talgar.R;
import com.talgar.data.local.sharedPrefs.SharedPrefs;
import com.talgar.databinding.SettingsFragmentBinding;
import com.talgar.ui.SplashActivity;

public class SettingsFragment extends Fragment {

    private SettingsViewModel mViewModel;
    SettingsFragmentBinding binding;
    private SharedPrefs sharedPrefs;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.settings_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding.clearHistory.setOnClickListener(view -> {
            App.quizDatabase.quizDao().deleteAll();
        });
        sharedPrefs = new SharedPrefs(getActivity().getApplicationContext());
        binding.lightMode.setOnClickListener(view -> {
            sharedPrefs.setTheme(0);
            getActivity().startActivity(new Intent(getActivity(), SplashActivity.class));
            getActivity().finish();
        });
        binding.darkMode.setOnClickListener(view -> {
            sharedPrefs.setTheme(1);
            getActivity().startActivity(new Intent(getActivity(), SplashActivity.class));
            getActivity().finish();
        });
    }

}