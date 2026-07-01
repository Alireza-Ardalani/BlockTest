package com.test.anr.backgroundThread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {
    FileWriter fw;

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
        AsyncTask<Void, Void, Void> outer = new AsyncTask<Void, Void, Void>() {
            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                IOBlockingCall("case13-outer-task");
                AsyncTask<Void, Void, Void> inner = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... v) {
                        IOBlockingCall("case13-inner-task");
                        return null;
                    }
                };
                inner.execute();
                return null;
            }
        };
        outer.execute();
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
