package com.example.multithreading.returningvaluefromthread;

public interface ResultListener<T> {

    void notifyResult(T result);
}
