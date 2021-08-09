package com.example.multithreading.returningvalueusingexecutors;

import com.example.multithreading.returningvaluefromthread.ValueReturningTaskA;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReturningValuesUsingExecutors_FirstTechnique {
    public static void main(String[] args) {
        String currentThreadName = Thread.currentThread().getName();

        System.out.println("[" + currentThreadName + "] Main Thread starts here...");

        ExecutorService executorService = Executors.newCachedThreadPool(new NamedThreadsFactory());

        Future<Integer> result1 = executorService.submit(new CalculationTaskA(2,3 , 2000));
        Future<Integer> result2 = executorService.submit(new CalculationTaskA(3,4 , 1000));
        Future<Integer> result3 = executorService.submit(new CalculationTaskA(4,5 , 500));

        // jeśli używamy obiektu Runnable, którego metoda run() nie zwraca żadnej wartości używamy <?>, natomiast metoda get() obiektu Future zwróci null
        Future<?> result4 = executorService.submit(new ValueReturningTaskA(2, 4, 200));
        // możemy natomiast dostarczyć wartość domyślną
        Future<Double> result5 = executorService.submit(new ValueReturningTaskA(2, 4, 200), 999.888);

        try {
            System.out.println("Result-1 :" + result1.get());
            System.out.println("Result-2 :" + result2.get());
            System.out.println("Result-3 :" + result3.get());
            System.out.println("Result-4 :" + result4.get());
            System.out.println("Result-5 :" + result5.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // wywołanie metody get jest blokujące, dlatego output ma kolejność wywołań

        executorService.shutdown();

        System.out.println("[" + currentThreadName + "] Main Thread ends here...");
    }
}
