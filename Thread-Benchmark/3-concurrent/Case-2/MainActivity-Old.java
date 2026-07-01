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

        case02_FixedExecutorExecute();
        case02_FixedExecutorExecute();

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

    // case02: Executors.newFixedThreadPool(...).execute(Runnable) using field fixedExecutor
    private void case02_FixedExecutorExecute() {
        fixedExecutor.execute(new Runnable() {
            @Override
            public void run() {
                IOBlockingCall("case02-fixed-executor-execute");
            }
        });
    }
}