package com.talgar.ui.result;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.talgar.R;
import com.talgar.data.local.sharedPrefs.SharedPrefs;
import com.talgar.data.models.Question;
import com.talgar.data.models.QuizResult;
import com.talgar.databinding.ActivityResultBinding;
import com.talgar.ui.question.QuestionActivity;

import java.util.ArrayList;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {

    private ResultViewModel resultViewModel;
    ActivityResultBinding binding;
    private QuizResult quizResult;
    private int correctAnswers;
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
        binding = DataBindingUtil.setContentView(this,R.layout.activity_result);
        resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
        binding.setViewModel(resultViewModel);
        correctAnswers = getIntent().getIntExtra(QuestionActivity.CORRECT_ANSWERS,0);
        resultViewModel.getData(getIntent());
        resultViewModel.mutableLiveDataQuestions.observe(this, new Observer<ArrayList<Question>>() {
            @Override
            public void onChanged(ArrayList<Question> questions) {
                if (questions != null && questions.size()>0){
                    quizResult = new QuizResult(questions.get(0).getCategory()
                            ,questions.get(0).getDifficulty()
                            ,correctAnswers
                            ,new Date(System.currentTimeMillis())
                            ,questions.size()
                            ,questions);
                    resultViewModel.getPercent(questions.size(),correctAnswers);
                    binding.setResult(quizResult);
                }
            }
        });

        binding.btnFinish.setOnClickListener(view -> {
            resultViewModel.saveResultToDataBase(quizResult);
            finish();
        });

    }


    @Override
    public void onBackPressed() {
        finish();
    }
}