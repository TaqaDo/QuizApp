package com.talgar.data.network;

import com.talgar.data.models.Question;
import com.talgar.quiz_adapter.baseAdapter.IBaseCallback;
import com.talgar.data.models.Categories;

import java.util.ArrayList;

public interface IQuizApiClient {

    void getQuestions(QuestionsApiCallback questionsApiCallback,int amount,int category,String difficulty);
    void getCategories(CategoriesApiCallback categoriesApiCallback);

    interface QuestionsApiCallback extends IBaseCallback<ArrayList<Question>> {
        @Override
        void onSuccess(ArrayList<Question> result);

        @Override
        void onFailure(Exception e);
    }

    interface CategoriesApiCallback extends IBaseCallback<ArrayList<Categories>>{
        @Override
        void onSuccess(ArrayList<Categories> result);

        @Override
        void onFailure(Exception e);
    }


}
