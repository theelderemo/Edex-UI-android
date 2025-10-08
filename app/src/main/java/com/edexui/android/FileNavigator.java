package com.edexui.android;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * File system navigator for Edex-UI
 * Provides directory listing and file information
 */
public class FileNavigator {

    private File currentDirectory;
    private boolean showHidden = false;

    public FileNavigator() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            this.currentDirectory = Environment.getExternalStorageDirectory();
        } else {
            this.currentDirectory = Environment.getDataDirectory();
        }
    }

    public FileNavigator(String path) {
        File requestedPath = new File(path);
        if (requestedPath.exists() && requestedPath.isDirectory()) {
            this.currentDirectory = requestedPath;
        } else {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                this.currentDirectory = Environment.getExternalStorageDirectory();
            } else {
                this.currentDirectory = Environment.getDataDirectory();
            }
        }
    }

    /**
     * Get current directory path
     */
    public String getCurrentPath() {
        return currentDirectory.getAbsolutePath();
    }

    /**
     * Navigate to a specific path
     */
    public boolean navigateTo(String path) {
        File newDir = new File(path);
        if (newDir.exists() && newDir.isDirectory() && newDir.canRead()) {
            currentDirectory = newDir;
            return true;
        }
        return false;
    }

    /**
     * Navigate to parent directory
     */
    public boolean navigateUp() {
        File parent = currentDirectory.getParentFile();
        if (parent != null && parent.canRead()) {
            currentDirectory = parent;
            return true;
        }
        return false;
    }

    /**
     * Navigate into a subdirectory
     */
    public boolean navigateInto(String dirName) {
        File newDir = new File(currentDirectory, dirName);
        if (newDir.exists() && newDir.isDirectory() && newDir.canRead()) {
            currentDirectory = newDir;
            return true;
        }
        return false;
    }

    /**
     * Get directory listing as formatted string
     */
    public String getDirectoryListing() {
        StringBuilder content = new StringBuilder();

        try {
            File[] files = currentDirectory.listFiles();

            if (files == null) {
                return "Unable to list files (permission denied)";
            }

            // Sort files: directories first, then by name
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File f1, File f2) {
                    if (f1.isDirectory() && !f2.isDirectory()) {
                        return -1;
                    } else if (!f1.isDirectory() && f2.isDirectory()) {
                        return 1;
                    } else {
                        return f1.getName().compareToIgnoreCase(f2.getName());
                    }
                }
            });

            // Add parent directory option if not at root
            if (currentDirectory.getParent() != null) {
                content.append("[DIR]  ..\n");
            }

            for (File file : files) {
                // Skip hidden files if option is disabled
                if (!showHidden && file.getName().startsWith(".")) {
                    continue;
                }

                if (file.canRead()) {
                    String type = file.isDirectory() ? "[DIR] " : "[FILE]";
                    String size = file.isDirectory() ? "" : " (" + formatFileSize(file.length()) + ")";
                    content.append(type).append(" ").append(file.getName()).append(size).append("\n");
                }
            }

            if (content.length() == 0) {
                content.append("Directory is empty or no readable files");
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

        return content.toString();
    }

    /**
     * Get detailed file information
     */
    public String getFileDetails(String fileName) {
        File file = new File(currentDirectory, fileName);

        if (!file.exists()) {
            return "File not found";
        }

        StringBuilder details = new StringBuilder();
        details.append("Name: ").append(file.getName()).append("\n");
        details.append("Type: ").append(file.isDirectory() ? "Directory" : "File").append("\n");
        details.append("Size: ").append(formatFileSize(file.length())).append("\n");

        // Permissions
        details.append("Permissions: ");
        details.append(file.canRead() ? "r" : "-");
        details.append(file.canWrite() ? "w" : "-");
        details.append(file.canExecute() ? "x" : "-");
        details.append("\n");

        // Last modified
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        details.append("Modified: ").append(sdf.format(new Date(file.lastModified()))).append("\n");

        return details.toString();
    }

    /**
     * Toggle showing hidden files
     */
    public void toggleShowHidden() {
        showHidden = !showHidden;
    }

    /**
     * Get hidden files visibility status
     */
    public boolean isShowingHidden() {
        return showHidden;
    }

    /**
     * Format file size to human-readable format
     */
    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format(Locale.getDefault(), "%.1f %sB",
            bytes / Math.pow(1024, exp), pre);
    }
}
