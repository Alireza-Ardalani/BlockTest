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
        case05_UniqueWork();
        case05_UniqueWork();
        try {
            fw = new FileWriter("tag" + ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // case05: enqueueUniqueWork(name, policy, request)
    private void case05_UniqueWork() {
        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(WMCase05_UniqueWorker.class).build();
        WorkManager.getInstance(getApplicationContext())
                .enqueueUniqueWork(
                        "case05-unique-work",
                        ExistingWorkPolicy.REPLACE,
                        request
                );
    }


    public static class WMCase05_UniqueWorker extends Worker {
        public WMCase05_UniqueWorker(Context context, WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {
            IOBlockingCall("case05-unique-worker-doWork");
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
