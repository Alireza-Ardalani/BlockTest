package com.test.anr.backgroundThread;

import android.app.Activity;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

        case10_ExecutorPassedToHelper();
        case10_ExecutorPassedToHelper();

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

    private void case10_ExecutorPassedToHelper() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                IOBlockingCall("case10-executor-passed-to-helper");
            }
        };
        useExecutor(simpleExecutor, r);
    }
    private void useExecutor(Executor executor, Runnable runnable) {
        executor.execute(runnable);
    }

}
