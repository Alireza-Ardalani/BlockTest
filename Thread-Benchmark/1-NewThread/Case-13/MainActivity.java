package com.test.anr.backgroundThread;

import android.app.Activity;
import android.os.Bundle;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {

    private FileWriter fileWriter;
    Object object = new Object();
    private final ThreadGroup threadGroup = new ThreadGroup("test-group");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileWriter = null;
        object = null;
        IOBlockingCall("fromUIThread");
        case13_ThreadGroupAndRunnable();
    }

    // case13: new Thread(ThreadGroup, Runnable).start()

    private void case13_ThreadGroupAndRunnable() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                IOBlockingCall("case15-threadgroup-and-runnable");
            }
        };
        new Thread(threadGroup, r).start();
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
