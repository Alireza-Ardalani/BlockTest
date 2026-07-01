package com.test.anr.backgroundThread;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MainActivity extends Activity {

    private static FileWriter fw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        case01_OneTimeFrom();
        try {
            fw = new FileWriter("tag" + ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // case01: OneTimeWorkRequest.from(...) enqueued directly
    private void case01_OneTimeFrom() {
        OneTimeWorkRequest request = OneTimeWorkRequest.from(WMCase01_SimpleWorker.class);
        WorkManager.getInstance(getApplicationContext()).enqueue(request);

        OneTimeWorkRequest request1 = OneTimeWorkRequest.from(WMCase01_SimpleWorker.class);
        WorkManager.getInstance(getApplicationContext()).enqueue(request1);
    }


    public static class WMCase01_SimpleWorker extends Worker {
        public WMCase01_SimpleWorker(Context context, WorkerParameters params) {
            super(context, params);
        }
        @Override
        public Result doWork() {
            IOBlockingCall("case01-simple-worker-doWork");
            return Result.success();
        }
    }
    static void IOBlockingCall(String tag) {
        try {
            fw.write("IOBlockingCall: " + tag + "\n");
            fw.flush();
            fw = null;
        } catch (IOException e) {
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                }
            }
        }
    }
}

// Utility class providing the blocking I/O call used by all workers
class BlockingIOUtil {

}
