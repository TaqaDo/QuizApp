package com.talgar.data.local;

import com.talgar.data.models.QuizResult;

import java.util.List;

public interface IHistoryStorage {

    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    List<QuizResult> getAll();

    void delete(int id);

    void deleteAll();

}
