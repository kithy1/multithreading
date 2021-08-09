package com.example.multithreading.returningvalueusingexecutors;

import com.example.multithreading.returningvaluefromthread.ValueReturningTaskA;

import java.util.concurrent.*;

public class ReturningValueUsingExecutors_secondTechnique {
    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[" + currentThreadName + "] Main Thread starts here...");

        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadsFactory());

        CompletionService<TaskResult<String, Integer>> completionService = new ExecutorCompletionService<>(executorService);

        completionService.submit(new CalculationTaskB(2,3 , 2000));
        completionService.submit(new CalculationTaskB(3,4 , 1000));
        completionService.submit(new CalculationTaskB(4,5 , 500));

        completionService.submit(new ValueReturningTaskA(2, 4, 200), new TaskResult<>("task4", 999));

        executorService.shutdown();

        // non blocking wyniki pojawiają się w miarę wykonania

        for (int i = 0; i < 4; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[" + currentThreadName + "] Main Thread ends here...");
    }
}
