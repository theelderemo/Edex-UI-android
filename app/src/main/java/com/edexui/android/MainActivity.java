package com.edexui.android;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.view.View;
import android.view.WindowManager;
import androidx.cardview.widget.CardView;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView systemInfoText;
    private TextView cpuInfoText;
    private TextView ramUsageText;
    private TextView networkInfoText;
    private TextView clockDisplay;
    private TextView fileNavigatorPath;
    private TextView fileNavigatorContent;
    private CardView terminalCard;
    
    private Handler handler;
    private Runnable updateRunnable;
    private FileNavigator fileNavigator;
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Enable fullscreen immersive mode
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        
        // Keep screen on
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        setContentView(R.layout.activity_main);
        
        // Initialize utilities
        fileNavigator = new FileNavigator();
        soundManager = new SoundManager(this);
        
        // Initialize views
        systemInfoText = findViewById(R.id.systemInfoText);
        cpuInfoText = findViewById(R.id.cpuInfoText);
        ramUsageText = findViewById(R.id.ramUsageText);
        networkInfoText = findViewById(R.id.networkInfoText);
        clockDisplay = findViewById(R.id.clockDisplay);
        fileNavigatorPath = findViewById(R.id.fileNavigatorPath);
        fileNavigatorContent = findViewById(R.id.fileNavigatorContent);
        terminalCard = findViewById(R.id.terminalCard);
        
        // Set up terminal card click listener
        if (terminalCard != null) {
            terminalCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    soundManager.playClick();
                    Intent intent = new Intent(MainActivity.this, TerminalActivity.class);
                    startActivity(intent);
                }
            });
        }
        
        // Initialize system info
        updateSystemInfo();
        updateFileNavigator();
        
        // Start periodic updates
        handler = new Handler(Looper.getMainLooper());
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                updateClock();
                updateCpuInfo();
                updateRamUsage();
                updateNetworkInfo();
                handler.postDelayed(this, 2000); // Update every 2 seconds
            }
        };
        handler.post(updateRunnable);
        
        // Apply entrance animations
        applyEntranceAnimations();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null && updateRunnable != null) {
            handler.removeCallbacks(updateRunnable);
        }
        if (soundManager != null) {
            soundManager.release();
        }
    }
    
    private void applyEntranceAnimations() {
        // Animate cards appearing one by one
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                View sysInfoCard = findViewById(R.id.systemInfoCard);
                if (sysInfoCard != null) AnimationUtil.fadeIn(sysInfoCard, 500);
            }
        }, 100);
        
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                View cpuCard = findViewById(R.id.cpuInfoCard);
                if (cpuCard != null) AnimationUtil.fadeIn(cpuCard, 500);
            }
        }, 200);
        
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                View ramCard = findViewById(R.id.ramUsageCard);
                if (ramCard != null) AnimationUtil.fadeIn(ramCard, 500);
            }
        }, 300);
        
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                View netCard = findViewById(R.id.networkInfoCard);
                if (netCard != null) AnimationUtil.fadeIn(netCard, 500);
            }
        }, 400);
    }
    
    private void updateClock() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        clockDisplay.setText(sdf.format(new Date()));
    }
    
    private void updateSystemInfo() {
        systemInfoText.setText(SystemMonitor.getSystemInfo());
    }
    
    private void updateCpuInfo() {
        cpuInfoText.setText(SystemMonitor.getCpuInfo());
    }
    
    private void updateRamUsage() {
        ramUsageText.setText(SystemMonitor.getRamInfo());
    }
    
    private void updateNetworkInfo() {
        networkInfoText.setText(SystemMonitor.getNetworkInfo());
    }
    
    private void updateFileNavigator() {
        fileNavigatorPath.setText(fileNavigator.getCurrentPath());
        fileNavigatorContent.setText(fileNavigator.getDirectoryListing());
    }
}
