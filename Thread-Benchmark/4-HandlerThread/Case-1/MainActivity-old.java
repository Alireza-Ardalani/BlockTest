package com.concurrencyBench.AsyncTask1;

import android.app.Activity;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

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

        case01_FieldHandlerPostRunnable();
        case01_FieldHandlerPostRunnable();

    }
    private void IOBlockingCall(String tag) {
        try {
            fileWriter = new FileWriter(tag + ".txt");
            fileWriter.write("IOBlockingCall: " + tag + "\n");
            fileWriter.flush();
            fileWriter = null;
        } catch (IOException e) {
        }
    }


    // case01: backgroundHandler.post(Runnable) using field Handler on HandlerThread
    private void case01_FieldHandlerPostRunnable() {
        backgroundHandler.post(new Runnable() {
            @Override
            public void run() {
                IOBlockingCall("case01-field-handler-post");
            }
        });
    }

}