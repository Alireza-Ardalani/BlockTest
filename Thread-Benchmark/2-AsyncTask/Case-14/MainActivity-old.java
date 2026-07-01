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
        StaticInnerTask t = new StaticInnerTask(this, "case14-static-inner-task");
        t.execute();
    }

    public static class StaticInnerTask extends AsyncTask<Void, Void, Void> {
        private final MainActivity activity;
        private final String tag;

        public StaticInnerTask(MainActivity activity, String tag) {
            this.activity = activity;
            this.tag = tag;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (activity != null) {
                activity.IOBlockingCall(tag);
            }
            return null;
        }
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