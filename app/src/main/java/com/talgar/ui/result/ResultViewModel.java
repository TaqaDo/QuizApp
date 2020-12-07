package com.talgar.ui.result;

import android.content.Intent;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.talgar.App;
import com.talgar.data.models.Question;
import com.talgar.data.models.QuizResult;
import com.talgar.ui.question.QuestionActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ResultViewModel extends ViewModel {

    public ObservableField<String> percentField = new ObservableField<>();
    private ArrayList<Question> questions = new ArrayList<>();
    private int percentage = 0;
    MutableLiveData<ArrayList<Question>> mutableLiveDataQuestions = new MutableLiveData<>();

    public void getPercent(int questionsAmount,int correctAnswers){
        percentage = correctAnswers  * 100 / questionsAmount;
        percentField.set(percentage + " %");
    }

    void getData(Intent intent){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Question>>() {}.getType();
        questions = gson.fromJson(intent.getStringExtra(QuestionActivity.QUESTIONS),type);
        mutableLiveDataQuestions.setValue(questions);
    }

    public void saveResultToDataBase(QuizResult quizResult){
        App.quizDatabase.quizDao().insert(quizResult);
    }

}
