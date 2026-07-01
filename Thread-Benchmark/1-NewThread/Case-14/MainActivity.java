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
        case14_ThreadGroupAndRunnable();
    }

    // case14: new Thread(ThreadGroup, Runnable, String).start()

    private void case14_ThreadGroupAndRunnable() {
        Runnable r = () -> IOBlockingCall("case16-threadgroup-runnable-name");
        new Thread(threadGroup, r, "case16-name").start();
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
