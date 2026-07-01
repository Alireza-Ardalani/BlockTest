package com.test.anr.backgroundThread;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    FileWriter fw;
    List<AsyncTask<Void, Void, Void>> taskList = new ArrayList<>();

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
        AsyncTask<Void, Void, Void> t = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                IOBlockingCall("case06-list-stored-task");
                return null;
            }
        };
        taskList.add(t);
        AsyncTask<Void, Void, Void> first = taskList.get(0);
        first.execute();
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
