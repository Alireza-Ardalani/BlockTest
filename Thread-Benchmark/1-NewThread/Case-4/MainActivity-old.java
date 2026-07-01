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
            case04_NamedThreadSubclass();
        }
        // case04: new NamedThread().start()
        private void case04_NamedThreadSubclass() {
            class MyThread extends Thread {
                @Override
                public void run() {
                    IOBlockingCall("case04-named-thread-subclass");
                }
            }
            new MyThread().start();
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