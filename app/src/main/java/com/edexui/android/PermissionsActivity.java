package com.edexui.android;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionsActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_STORAGE = 1;
    private static final int REQUEST_CODE_INTERNET = 2;
    private static final int MANAGE_EXTERNAL_STORAGE_REQUEST_CODE = 3;

    private Button btnStorage, btnInternet, btnContinue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        btnStorage = findViewById(R.id.btn_storage);
        btnInternet = findViewById(R.id.btn_internet);
        btnContinue = findViewById(R.id.btn_continue);

        btnStorage.setOnClickListener(v -> requestStoragePermission());
        btnInternet.setOnClickListener(v -> requestInternetPermission());
        btnContinue.setOnClickListener(v -> continueToApp());

        updateButtonStates();
    }

    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivityForResult(intent, MANAGE_EXTERNAL_STORAGE_REQUEST_CODE);
            } else {
                updateButtonStates();
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
            } else {
                updateButtonStates();
            }
        }
    }

    private void requestInternetPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE}, REQUEST_CODE_INTERNET);
        } else {
            updateButtonStates();
        }
    }

    private void updateButtonStates() {
        if (isStoragePermissionGranted()) {
            btnStorage.setEnabled(false);
            btnStorage.setText("Storage Granted");
        }

        if (isInternetPermissionGranted()) {
            btnInternet.setEnabled(false);
            btnInternet.setText("Internet Granted");
        }

        if (areAllPermissionsGranted()) {
            btnContinue.setVisibility(View.VISIBLE);
        }
    }

    private boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                   ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    private boolean isInternetPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED &&
               ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean areAllPermissionsGranted() {
        return isStoragePermissionGranted() && isInternetPermissionGranted();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        updateButtonStates();
        if (requestCode == REQUEST_CODE_STORAGE) {
            if (isStoragePermissionGranted()) {
                Toast.makeText(this, "Storage permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Storage permission denied", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CODE_INTERNET) {
            if (isInternetPermissionGranted()) {
                Toast.makeText(this, "Internet permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Internet permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MANAGE_EXTERNAL_STORAGE_REQUEST_CODE) {
            updateButtonStates();
        }
    }

    private void continueToApp() {
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("first_launch", false);
        editor.apply();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
