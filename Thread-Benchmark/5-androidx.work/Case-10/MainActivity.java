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
        case10_BeginUniqueWorkChain();
        case10_BeginUniqueWorkChain();
        try {
            fw = new FileWriter("tag" + ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void case10_BeginUniqueWorkChain() {
        OneTimeWorkRequest first =
                new OneTimeWorkRequest.Builder(WMCase10_ChainFirstWorker.class)
                        .addTag("case10-first-tag")
                        .build();
        OneTimeWorkRequest second =
                new OneTimeWorkRequest.Builder(WMCase10_ChainSecondWorker.class)
                        .addTag("case10-second-tag")
                        .build();
        WorkManager.getInstance(getApplicationContext())
                .beginUniqueWork(
                        "case10-unique-chain",
                        ExistingWorkPolicy.KEEP,
                        first
                )
                .then(second)
                .enqueue();
    }

    public static class WMCase10_ChainFirstWorker extends Worker {
        public WMCase10_ChainFirstWorker(Context context, WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {
            IOBlockingCall("case10-chain-first-worker-doWork");
            return Result.success();
        }
    }

    public static class WMCase10_ChainSecondWorker extends Worker {
        public WMCase10_ChainSecondWorker(Context context, WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {
            IOBlockingCall("case10-chain-second-worker-doWork");
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
