package com.concurrencyBench.AsyncTask1;

import android.app.Activity;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity {


    private FileWriter fileWriter;
    Object object = new Object();
    private ExecutorService singleExecutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        singleExecutor = Executors.newSingleThreadExecutor();

        case01_SingleExecutorExecute();
        case01_SingleExecutorExecute();

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