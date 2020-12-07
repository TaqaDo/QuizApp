package com.talgar.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.talgar.R;
import com.talgar.data.local.sharedPrefs.SharedPrefs;
import com.talgar.databinding.ActivityMainBinding;
import com.talgar.quiz_adapter.fargmentsAdapter.FragmentsAdapter;
import com.talgar.ui.historyFragment.HistoryFragment;
import com.talgar.ui.quizFragment.QuizFragment;
import com.talgar.ui.settingsFragment.SettingsFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Fragment> fragments;
    ActivityMainBinding binding;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs = new SharedPrefs(getApplicationContext());
        if (sharedPrefs.getTheme() == 0) {
            setTheme(R.style.LightTheme);
        }else {
            setTheme(R.style.DarkTheme);
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        fiilFragment();
        binding.mainViewPager.setAdapter(new FragmentsAdapter(fragments, getSupportFragmentManager()));
        bottomListener();
    }

    private void fiilFragment() {
        fragments = new ArrayList<>();
        fragments.add(new QuizFragment());
        fragments.add(new HistoryFragment());
        fragments.add(new SettingsFragment());
    }

    private void bottomListener() {
        binding.mainBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.quiz_icon) {
                    binding.mainViewPager.setCurrentItem(0);
                } else if (item.getItemId() == R.id.history_icon) {
                    binding.mainViewPager.setCurrentItem(1);
                } else if (item.getItemId() == R.id.settings_icon) {
                    binding.mainViewPager.setCurrentItem(2);
                }
                return true;
            }
        });
    }


}