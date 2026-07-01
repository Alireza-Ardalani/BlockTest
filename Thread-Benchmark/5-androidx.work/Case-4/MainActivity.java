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
        case04_BeginWithThen();
        case04_BeginWithThen();
        try {
            fw = new FileWriter("tag" + ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // case04: WorkManager.beginWith(one).then(second).enqueue() chain
    private void case04_BeginWithThen() {
        OneTimeWorkRequest first =
                new OneTimeWorkRequest.Builder(WMCase04_FirstWorker.class).build();
        OneTimeWorkRequest second =
                new OneTimeWorkRequest.Builder(WMCase04_SecondWorker.class).build();
        WorkContinuation continuation =
                WorkManager.getInstance(getApplicationContext()).beginWith(first);
        continuation.then(second).enqueue();
    }


    public static class WMCase04_FirstWorker extends Worker {
        public WMCase04_FirstWorker(Context context, WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {
            IOBlockingCall("case04-first-worker-doWork");
            return Result.success();
        }
    }

    public static class WMCase04_SecondWorker extends Worker {
        public WMCase04_SecondWorker(Context context, WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {
            IOBlockingCall("case04-second-worker-doWork");
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
