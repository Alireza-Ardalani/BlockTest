package com.test.anr.backgroundThread;

import android.app.Activity;
import android.os.Bundle;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {

    private FileWriter fileWriter;
    Object object = new Object();
    private Thread threadRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileWriter = null;
        object = null;
        IOBlockingCall("fromUIThread");
        case10_CreateThenStartInAnotherMethod();
    }

    // case10: create in one method, start in another via field
    private void case10_CreateThenStartInAnotherMethod() {
        createThreadAcrossMethods();
        startThreadAcrossMethods();
    }

    private void createThreadAcrossMethods() {
        threadRef = new Thread(() -> IOBlockingCall("case10-cross-method-create"));
    }

    private void startThreadAcrossMethods() {
        if (threadRef != null) {
            threadRef.start();
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
}
