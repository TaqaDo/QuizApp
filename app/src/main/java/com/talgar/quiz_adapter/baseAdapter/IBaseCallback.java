package com.talgar.quiz_adapter.baseAdapter;

public interface IBaseCallback<T> {

    void onSuccess(T result);
    void onFailure(Exception e);

}
