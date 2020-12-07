package com.talgar.ui.quizFragment;

import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.talgar.App;
import com.talgar.data.models.Categories;
import com.talgar.data.network.IQuizApiClient;

import java.util.ArrayList;

public class QuizViewModel extends ViewModel {

    public  MutableLiveData<ArrayList<Categories>> data = new MutableLiveData<>();
    public String[] names;
    public ObservableField<Boolean> isLoading = new ObservableField<>();

    public void getCategories(){
        isLoading.set(true);
        App.quizRepository.getCategories(new IQuizApiClient.CategoriesApiCallback() {
            @Override
            public void onSuccess(ArrayList<Categories> result) {
                names = new String[result.size()];
                for (int i = 0; i < result.size(); i++) {
                    names[i] = result.get(i).getName();
                }
                data.setValue(result);
                isLoading.set(false);
            }

            @Override
            public void onFailure(Exception e) {
                isLoading.set(false);
            }
        });
    }
}