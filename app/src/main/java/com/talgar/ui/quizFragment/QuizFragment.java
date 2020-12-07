package com.talgar.ui.quizFragment;

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
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import com.talgar.R;
import com.talgar.databinding.QuizFragmentBinding;
import com.talgar.ui.question.QuestionActivity;
import com.talgar.custom_classes.SimpleSeekBarChangeListener;

public class QuizFragment extends Fragment {

    private QuizViewModel mViewModel;
    public QuizFragmentBinding binding;
    public final static String ID = "1";
    public final static String AMOUNT = "2";
    public final static String DIFFICULTY = "3";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_fragment, container, false);
        binding.setLifecycleOwner(this);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        binding.btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), QuestionActivity.class);
            intent.putExtra(QuizFragment.AMOUNT, binding.seekbar.getProgress());
            intent.putExtra(QuizFragment.ID,mViewModel.data.getValue().get(binding.spinner1.getSelectedItemPosition()).getId());
            intent.putExtra(QuizFragment.DIFFICULTY, binding.spinner2.getSelectedItem().toString().toLowerCase());
            getContext().startActivity(intent);
        });
        mViewModel.getCategories();
        mViewModel.data.observe(getViewLifecycleOwner(), categories -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,mViewModel.names);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinner1.setAdapter(adapter);
        });

        binding.seekbar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.numbOfAmount.setText(String.valueOf(i));
            }
        });
    }


}