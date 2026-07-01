package com.test.anr.backgroundThread;

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
        AsyncTask<Void, Void, Void> t = createTaskForLater("case15-cross-method-task");
        executeLater(t);
    }

    private AsyncTask<Void, Void, Void> createTaskForLater(final String tag) {
        return new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                IOBlockingCall(tag);
                return null;
            }
        };
    }

    private void executeLater(AsyncTask<Void, Void, Void> task) {
        task.execute();
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
