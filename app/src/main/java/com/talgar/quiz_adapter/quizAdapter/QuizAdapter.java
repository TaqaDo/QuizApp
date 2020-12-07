package com.talgar.quiz_adapter.quizAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.talgar.data.models.Question;
import com.talgar.databinding.QuestionHolderBinding;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private ArrayList<Question> questions = new ArrayList<>();
    private QuizViewHolder holder1;
    OnBtnClickListener listener;
    QuestionHolderBinding binding;


    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }
    public void setListener(OnBtnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = QuestionHolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new QuizViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder1 = holder;
        Question question = this.questions.get(position);
        holder.questionHolderBinding.setListener(listener);
        holder.questionHolderBinding.setPosition(position);
        holder.questionHolderBinding.setHolder(holder);
        holder.questionHolderBinding.setQuestion(question);
    }

    public QuizViewHolder getHolder1() {
        return holder1;
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {

        public QuestionHolderBinding questionHolderBinding;

        public QuizViewHolder(@NonNull View view) {
            super(view);
            questionHolderBinding = DataBindingUtil.bind(view);
        }
    }
}
