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
        case06_LambdaRunnableVariable();
    }
    // case06: lambda Runnable variable passed to Thread constructor
    private void case06_LambdaRunnableVariable() {
        Runnable r = () -> IOBlockingCall("case06-lambda-runnable-variable");
        new Thread(r).start();
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
