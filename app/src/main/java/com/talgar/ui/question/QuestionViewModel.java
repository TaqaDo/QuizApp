package com.talgar.ui.question;

import android.util.Log;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.talgar.App;
import com.talgar.data.models.Question;
import com.talgar.data.network.IQuizApiClient;

import java.util.ArrayList;

public class QuestionViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Question>> questionLiveData = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPos = new MutableLiveData<>();
    MutableLiveData<Integer> isLast = new MutableLiveData<>();
    public ObservableField<Boolean> isLoading = new ObservableField<>();
    public ArrayList<Question> mQuestions;
    ArrayList<String> categories = new ArrayList<>();
    int correctAnswers;

    public void getQuestions(int amount,int id,String difficulty){
        isLoading.set(true);
        App.quizRepository.getQuestions(new IQuizApiClient.QuestionsApiCallback() {
            @Override
            public void onSuccess(ArrayList<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    categories.add(result.get(i).getCategory());
                }
                mQuestions = result;
                Log.d("response",String.valueOf(mQuestions.get(0).getIncorrectAnswers().size()) + "got");
                questionLiveData.setValue(mQuestions);
                isLoading.set(false);
                currentQuestionPos.setValue(0);
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("onFailure",e.getMessage());
                isLoading.set(false);
            }
        },amount,id,difficulty);
    }

    void finish(){
        isLast.setValue(correctAnswers);
    }


    public void onAnswerClick(int questionPosition,int answerPosition){
        if (currentQuestionPos.getValue() == null || mQuestions == null){
            return;
        }
        Question question = mQuestions.get(questionPosition);
        question.setSelectQuestionPosition(answerPosition);
        question.setClicked(true);

        if (question.getIncorrectAnswers().get(answerPosition).equals(question.getCorrectAnswer())) {
            correctAnswers++;
            question.setAnswerTrue(true);
        }else{
            question.setAnswerTrue(false);
        }
        mQuestions.set(questionPosition, question);
        questionLiveData.setValue(mQuestions);
    }
    public void moveToFinish(int position){
        if (position == mQuestions.size() - 1){
            finish();
        }else {
            currentQuestionPos.setValue(position);
        }
    }

    void skip(int questionPos){
        if (questionPos == mQuestions.size() - 1){
            return;
        }else{
            mQuestions.get(questionPos).setClicked(true);
            questionLiveData.setValue(mQuestions);
        }
    }
}
