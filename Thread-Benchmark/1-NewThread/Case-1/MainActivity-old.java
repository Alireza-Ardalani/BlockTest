package com.concurrencyBench.AsyncTask1;

import android.app.Activity;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {

    private FileWriter fileWriter;
    Object object = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileWriter = null;
        object = null;
        IOBlockingCall("fromUIThread");
        case01_AnonymousRunnable();
    }

    // case01: new Thread(new Runnable(){...}).start()
    private void case01_AnonymousRunnable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                IOBlockingCall("case01-anonymous-runnable");
            }
        }).start();
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