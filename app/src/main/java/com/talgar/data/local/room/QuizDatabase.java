package com.talgar.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.talgar.data.models.QuizResult;

@Database(entities = {QuizResult.class},version = 1,exportSchema = false)
public abstract class QuizDatabase extends RoomDatabase{
    public abstract QuizDao quizDao();
}
