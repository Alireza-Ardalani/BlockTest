package com.concurrencyBench.AsyncTask1;

import android.app.Activity;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import android.os.AsyncTask;

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
        AsyncTask<String, Void, Void> t = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... strings) {
                String label = (strings != null && strings.length > 0) ? strings[0] : "case09-no-param";
                IOBlockingCall("case09-string-param-" + label);
                return null;
            }
        };
        t.execute("tag09");
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