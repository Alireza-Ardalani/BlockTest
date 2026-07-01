package com.concurrencyBench.AsyncTask1;

import android.app.Activity;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {


    private FileWriter fileWriter;
    Object object = new Object();
    private final Thread delayedThread = new Thread(new Runnable() {
        @Override
        public void run() {
            IOBlockingCall("case11-delayed-thread");
        }
    });    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileWriter = null;
        object = null;
        IOBlockingCall("fromUIThread");
        case12_ThreadNameOnlyOverrideRun();
    }

    private void case12_ThreadNameOnlyOverrideRun() {
        new Thread("case12-named-thread") {
            @Override
            public void run() {
                IOBlockingCall("case12-name-only-override-run");
            }
        }.start();
    }

    private void IOBlockingCall(String tag) {
        try {
            object.toString();
            fileWriter = new FileWriter(tag + ".txt");
            fileWriter.write("IOBlockingCall: " + tag + "\n");
            fileWriter.flush();
        } catch (IOException e) {
        }
    }

}