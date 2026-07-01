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
        case09_InputData();
        case09_InputData();
        try {
            fw = new FileWriter("tag" + ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // case09: OneTimeWorkRequest with input Data
    private void case09_InputData() {
        Data input =
                new Data.Builder()
                        .putString("label", "case09-input-data-worker")
                        .build();
        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(WMCase09_InputDataWorker.class)
                        .setInputData(input)
                        .build();
        WorkManager.getInstance(getApplicationContext()).enqueue(request);
    }
    public static class WMCase09_InputDataWorker extends Worker {
        public WMCase09_InputDataWorker(Context context, WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {
            String label = getInputData().getString("label");
            if (label == null) {
                label = "case09-input-data-worker-doWork";
            }
            IOBlockingCall(label);
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
