package com.talgar;

import android.app.Application;

import androidx.room.Room;

import com.talgar.data.network.QuizRepository;
import com.talgar.data.local.HistoryStorage;
import com.talgar.data.local.IHistoryStorage;
import com.talgar.data.local.room.QuizDatabase;
import com.talgar.data.network.QuizApiClient;

public class App extends Application {

    public static QuizApiClient apiClient;
    public static QuizRepository quizRepository;
    public static QuizDatabase quizDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        apiClient = new QuizApiClient();

        IHistoryStorage iHistoryStorage = new HistoryStorage();

        quizRepository = new QuizRepository(apiClient ,iHistoryStorage);

        quizDatabase = Room.databaseBuilder(
                this,
                QuizDatabase.class,
                "QuizDatabase"
        ).allowMainThreadQueries()
                .build();
    }
}
