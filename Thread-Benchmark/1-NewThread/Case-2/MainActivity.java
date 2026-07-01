package com.test.anr.backgroundThread;

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
        case02_LambdaRunnable();
    }
    // case02: new Thread(() -> ...).start()
    private void case02_LambdaRunnable() {
        new Thread(() -> IOBlockingCall("case02-lambda-runnable")).start();
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
