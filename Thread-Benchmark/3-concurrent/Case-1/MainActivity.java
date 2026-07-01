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
    private ExecutorService singleExecutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        singleExecutor = Executors.newSingleThreadExecutor();
        object = null;

        IOBlockingCall("MainThread");

        case01_SingleExecutorExecute();
        case01_SingleExecutorExecute();

    }


    private void IOBlockingCall(String tag) {
        try {
            object.toString();
            fileWriter = new FileWriter(tag + ".txt");
            fileWriter.write("IOBlockingCall: " + tag + "\n");
            fileWriter.flush();
            fileWriter = null;
        } catch (IOException e) { }
    }

    // case01: Executors.newSingleThreadExecutor().execute(Runnable) using field singleExecutor
    private void case01_SingleExecutorExecute() {
        singleExecutor.execute(new Runnable() {
            @Override
            public void run() {
                IOBlockingCall("case01-single-executor-execute");
            }
        });
    }
}
