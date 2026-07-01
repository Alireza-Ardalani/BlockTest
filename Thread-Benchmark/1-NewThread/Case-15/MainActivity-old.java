package com.concurrencyBench.AsyncTask1;

import android.app.Activity;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {

    private FileWriter fileWriter;
    static Object object = new Object();
    private static Thread staticInitThread;
    static {
        staticInitThread = new Thread(new Runnable() {
            @Override
            public void run() {
                IOBlockingCallStatic("case18-static-init-thread");
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileWriter = null;
        object = null;
        IOBlockingCall("fromUIThread");
        case15_StartStaticInitThread();
    }

    // case15: start thread created in static initializer
    private void case15_StartStaticInitThread() {
        if (staticInitThread != null) {
            staticInitThread.start();
        }
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

    private static void IOBlockingCallStatic(String tag) {
        object.toString();
        FileWriter fw = null;
        try {
            fw = new FileWriter(tag + ".txt");
            fw.write("IOBlockingCallStatic: " + tag + "\n");
            fw.flush();
        } catch (IOException e) {
            // ignore for static analysis
        } finally {
            try {
                if (fw != null) fw.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

}