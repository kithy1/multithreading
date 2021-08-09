package com.example.multithreading.returningvaluefromthread;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskB implements Runnable{

    private int a;
    private int b;
    private long sleepTime;
    private int sum;

    private static int count = 0;
    private int instanceNumber;
    private String taskId;

    private ResultListener<Integer> resultProcessor;

    public ValueReturningTaskB(int a, int b, long sleepTime, ResultListener<Integer> resultProcessor) {
        this.a = a;
        this.b = b;
        this.sleepTime = sleepTime;

        this.instanceNumber = ++count;
        this.taskId = "ValueReturningTaskA-" + instanceNumber;
        this.resultProcessor = resultProcessor;
    }

    @Override
    public void run() {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("###[" + currentThreadName + "] <" + taskId + "> STARTING ###");

        try {
            TimeUnit.MICROSECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sum = a+b;

        System.out.println("***[" + currentThreadName + "] <" + taskId + "> CALCULATION COMPLETED ***");

        resultProcessor.notifyResult(sum);

    }

}
