package com.edexui.android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.view.View;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Process List Activity - Shows running processes similar to 'top' command
 */
public class ProcessListActivity extends AppCompatActivity {

    private TextView processListText;
    private Handler handler;
    private Runnable updateRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_list);

        processListText = findViewById(R.id.processListText);

        // Update process list
        updateProcessList();

        // Start periodic updates
        handler = new Handler(Looper.getMainLooper());
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                updateProcessList();
                handler.postDelayed(this, 3000); // Update every 3 seconds
            }
        };
        handler.post(updateRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && updateRunnable != null) {
            handler.removeCallbacks(updateRunnable);
        }
    }

    /**
     * Update process list
     */
    private void updateProcessList() {
        StringBuilder processes = new StringBuilder();
        processes.append("═══════════════════════════════════════\n");
        processes.append("  PROCESS LIST (TOP)\n");
        processes.append("═══════════════════════════════════════\n\n");

        try {
            // Try to execute 'ps' command
            Process process = Runtime.getRuntime().exec("ps");
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
            String line;
            int count = 0;

            while ((line = reader.readLine()) != null && count < 50) {
                processes.append(line).append("\n");
                count++;
            }

            reader.close();
            process.waitFor();

            if (count == 0) {
                processes.append("No process information available\n");
                processes.append("(Limited access on non-rooted devices)\n");
            }

        } catch (Exception e) {
            processes.append("Error: Unable to retrieve process list\n");
            processes.append("Command 'ps' may not be available\n");
            processes.append("\nFallback: Application processes only\n\n");

            // Show Java process information as fallback
            processes.append("Current Process:\n");
            processes.append("PID: ").append(android.os.Process.myPid()).append("\n");
            processes.append("UID: ").append(android.os.Process.myUid()).append("\n");
            processes.append("TID: ").append(android.os.Process.myTid()).append("\n");

            // Memory info
            Runtime runtime = Runtime.getRuntime();
            processes.append("\nMemory Usage:\n");
            processes.append("Total: ").append(formatBytes(runtime.totalMemory())).append("\n");
            processes.append("Free: ").append(formatBytes(runtime.freeMemory())).append("\n");
            processes.append("Max: ").append(formatBytes(runtime.maxMemory())).append("\n");
        }

        processes.append("\n═══════════════════════════════════════\n");
        processListText.setText(processes.toString());
    }

    /**
     * Format bytes to human-readable format
     */
    private String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format(java.util.Locale.getDefault(), "%.1f %sB",
            bytes / Math.pow(1024, exp), pre);
    }
}
