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
            case03_NamedRunnableClass();
        }

        // case03: new Thread(new NamedRunnable()).start()
        private void case03_NamedRunnableClass() {
            class MyRunnable implements Runnable {
                @Override
                public void run() {
                    IOBlockingCall("case03-named-runnable-class");
                }
            }
            new Thread(new MyRunnable()).start();
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