package com.test.anr.backgroundThread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private FileWriter fileWriter;

    private HandlerThread handlerThreadField;
    private Handler backgroundHandler;
    private HandlerThread crossThread;
    private Handler crossHandler;
    private Handler[] handlerArray = new Handler[2];
    private Map<String, Handler> handlerMap = new HashMap<>();
    private static HandlerThread staticHandlerThread;
    private static Handler staticHandler;
    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handlerThreadField = new HandlerThread("field-bg-handler-thread");
        handlerThreadField.start();
        backgroundHandler = new Handler(handlerThreadField.getLooper());
        try {
            fileWriter = new FileWriter("tag" + ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        case07_HandlerCreatedByHelperPost();
        case07_HandlerCreatedByHelperPost();

    }
    private void IOBlockingCall(String tag) {
        try {
            fileWriter.write("IOBlockingCall: " + tag + "\n");
            fileWriter.flush();
            fileWriter = null;
        } catch (IOException e) {
        }
    }

    private void case07_HandlerCreatedByHelperPost() {
        Handler h = createBackgroundHandlerHelper("case07-helper-thread");
        h.post(new Runnable() {
            @Override
            public void run() {
                IOBlockingCall("case07-helper-created-handler-post");
            }
        });
    }

    private Handler createBackgroundHandlerHelper(String threadName) {
        HandlerThread ht = new HandlerThread(threadName);
        ht.start();
        return new Handler(ht.getLooper());
    }
}
