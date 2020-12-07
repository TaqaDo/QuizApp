package com.talgar.ui.historyFragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.talgar.App;
import com.talgar.R;
import com.talgar.quiz_adapter.quizAdapter.HistoryAdapter;
import com.talgar.data.models.QuizResult;
import com.talgar.databinding.HistoryFragmentBinding;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    HistoryFragmentBinding binding;
    private HistoryViewModel mViewModel;
    private HistoryAdapter historyAdapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.history_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        historyAdapter.setQuizResults((ArrayList<QuizResult>) App.quizDatabase.quizDao().getAll());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        historyAdapter = new HistoryAdapter();
        binding.recyclerHistory.setAdapter(historyAdapter);
        historyAdapter.setQuizResults((ArrayList<QuizResult>) App.quizDatabase.quizDao().getAll());
    }

}