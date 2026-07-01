package com.concurrencyBench.AsyncTask1;

import android.app.Activity;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import android.os.AsyncTask;

public class MainActivity extends Activity {
    FileWriter fw;

    @SuppressWarnings("unchecked")
    AsyncTask<String, Void, Void>[] taskArray = new AsyncTask[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            fw = new FileWriter("s.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        runCase();
        runCase();
    }

    private void runCase() {
        taskArray[0] = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                String p = (params != null && params.length > 0) ? params[0] : "case05-default";
                IOBlockingCall("case05-array-stored-" + p);
                return null;
            }
        };
        taskArray[0].execute("param05");
    }

    private void IOBlockingCall(String tag) {
        try {
            if (fw != null) {
                fw.write("IOBlockingCall: " + tag + "\n");
                fw.flush();
            }
        } catch (IOException e) {
        }
    }

}