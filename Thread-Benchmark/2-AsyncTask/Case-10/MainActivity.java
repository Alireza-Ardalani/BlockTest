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
        AsyncTask<Void, Integer, Void> t = new AsyncTask<Void, Integer, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                IOBlockingCall("case10-progress-task-doInBackground");
                publishProgress(1, 2, 3);
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }
        };
        t.execute();
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
