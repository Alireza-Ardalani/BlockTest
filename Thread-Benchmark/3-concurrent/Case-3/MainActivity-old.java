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

        case03_CachedExecutorSubmitRunnable();
        case03_CachedExecutorSubmitRunnable();

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

    // case03: Executors.newCachedThreadPool().submit(Runnable) local executor
    private void case03_CachedExecutorSubmitRunnable() {
        ExecutorService cached = Executors.newCachedThreadPool();
        cached.submit(new Runnable() {
            @Override
            public void run() {
                IOBlockingCall("case03-cached-submit-runnable");
            }
        });
    }
}