package com.talgar.data.network;

import android.util.Log;

import androidx.room.TypeConverters;

import com.talgar.data.local.IHistoryStorage;
import com.talgar.data.local.room.QuestionsConverter;
import com.talgar.data.models.Categories;
import com.talgar.data.models.Question;
import com.talgar.data.models.QuizResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IQuizApiClient, IHistoryStorage {

    private IQuizApiClient quizApiClient;
    private IHistoryStorage iHistoryStorage;
    @TypeConverters(QuestionsConverter.class)
    Question question = new Question();

    public QuizRepository(QuizApiClient quizApiClient, IHistoryStorage iHistoryStorage) {
        this.quizApiClient = quizApiClient;
        this.iHistoryStorage = iHistoryStorage;
    }

    @Override
    public void getCategories(IQuizApiClient.CategoriesApiCallback categoriesApiCallback) {
        quizApiClient.getCategories(new CategoriesApiCallback() {
            @Override
            public void onSuccess(ArrayList<Categories> result) {
                categoriesApiCallback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                categoriesApiCallback.onFailure(e);
            }
        });
    }

    @Override
    public void getQuestions(QuestionsApiCallback apiCallback, int amount, int category, String difficulty) {
        quizApiClient.getQuestions(new QuestionsApiCallback() {
            @Override
            public void onSuccess(ArrayList<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    Question question = result.get(i);
                    ArrayList<String> answers = new ArrayList<>(question.getIncorrectAnswers());
                    answers.add(question.getCorrectAnswer());
                    Collections.shuffle(answers);
                    result.get(i).setIncorrectAnswers(answers);
                    Log.d("response", result.get(i).getIncorrectAnswers().size() + " repository");
                }
                question = null;
                apiCallback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                question = null;
                apiCallback.onFailure(e);
            }
        }, amount, category, difficulty);
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public List<QuizResult> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }
}
