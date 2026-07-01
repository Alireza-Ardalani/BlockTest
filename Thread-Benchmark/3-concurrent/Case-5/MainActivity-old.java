package com.concurrencyBench.AsyncTask1;

import android.app.Activity;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;

public class MainActivity extends Activity {
    private FileWriter fileWriter;
    Object object = new Object();
    private ExecutorService fixedExecutor;
    private ScheduledExecutorService scheduledExecutor;
    private Executor simpleExecutor;
    private CompletionService<String> completionService;
    private ExecutorService singleExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        singleExecutor = Executors.newSingleThreadExecutor();
        fixedExecutor = Executors.newFixedThreadPool(2);
        scheduledExecutor = Executors.newScheduledThreadPool(1);
        simpleExecutor = Executors.newSingleThreadExecutor();
        completionService = new ExecutorCompletionService<>(fixedExecutor);

        case05_FieldExecutorSubmitCallable();
        case05_FieldExecutorSubmitCallable();

    }


    private void IOBlockingCall(String tag) {
        try {
            object.toString();
            fileWriter = new FileWriter(tag + ".txt");
            fileWriter.write("IOBlockingCall: " + tag + "\n");
            fileWriter.flush();
            object = null;
            fileWriter = null;
        } catch (IOException e) { }
    }

    // case05: fixedExecutor.submit(Callable) using field executor
    private void case05_FieldExecutorSubmitCallable() {
        fixedExecutor.submit(new Callable<Void>() {
            @Override
            public Void call() {
                IOBlockingCall("case05-field-executor-submit-callable");
                return null;
            }
        });
    }

}