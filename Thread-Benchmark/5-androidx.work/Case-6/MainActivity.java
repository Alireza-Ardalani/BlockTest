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
        case06_EnqueueList();
        case06_EnqueueList();
        try {
            fw = new FileWriter("tag" + ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // case06: enqueue(Arrays.asList(...)) with multiple OneTimeWorkRequest
    private void case06_EnqueueList() {
        OneTimeWorkRequest r1 =
                new OneTimeWorkRequest.Builder(WMCase06_ListWorkerA.class).build();
        OneTimeWorkRequest r2 =
                new OneTimeWorkRequest.Builder(WMCase06_ListWorkerB.class).build();
        WorkManager.getInstance(getApplicationContext())
                .enqueue(Arrays.<WorkRequest>asList(r1, r2));
    }

    public static class WMCase06_ListWorkerA extends Worker {
        public WMCase06_ListWorkerA(Context context, WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {
            IOBlockingCall("case06-list-worker-A-doWork");
            return Result.success();
        }
    }

    public static class WMCase06_ListWorkerB extends Worker {
        public WMCase06_ListWorkerB(Context context, WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {
            IOBlockingCall("case06-list-worker-B-doWork");
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
