package com.edexui.android;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * System monitoring utility class for Edex-UI
 * Provides system information, CPU, RAM, and network statistics
 */
public class SystemMonitor {
    
    /**
     * Get device and OS information
     */
    public static String getSystemInfo() {
        StringBuilder info = new StringBuilder();
        
        info.append("HOSTNAME: ").append(android.os.Build.DEVICE).append("\n");
        info.append("OS: Android ").append(android.os.Build.VERSION.RELEASE).append("\n");
        info.append("API LEVEL: ").append(android.os.Build.VERSION.SDK_INT).append("\n");
        info.append("MANUFACTURER: ").append(android.os.Build.MANUFACTURER).append("\n");
        info.append("MODEL: ").append(android.os.Build.MODEL).append("\n");
        info.append("BOARD: ").append(android.os.Build.BOARD).append("\n");
        info.append("ARCHITECTURE: ").append(System.getProperty("os.arch")).append("\n");
        
        return info.toString();
    }
    
    /**
     * Get CPU information and current usage
     */
    public static String getCpuInfo() {
        StringBuilder info = new StringBuilder();
        
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream("/proc/cpuinfo")));
            String line;
            int coreCount = 0;
            String cpuModel = "Unknown";
            
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("processor")) {
                    coreCount++;
                } else if (line.startsWith("Hardware") || line.startsWith("model name")) {
                    String[] parts = line.split(":");
                    if (parts.length > 1) {
                        cpuModel = parts[1].trim();
                    }
                }
            }
            reader.close();
            
            info.append("CPU: ").append(cpuModel).append("\n");
            info.append("CORES: ").append(coreCount).append("\n");
            info.append("ARCHITECTURE: ").append(System.getProperty("os.arch")).append("\n");
            
        } catch (Exception e) {
            info.append("CPU: Information unavailable\n");
        }
        
        return info.toString();
    }
    
    /**
     * Get RAM usage information
     */
    public static String getRamInfo() {
        StringBuilder info = new StringBuilder();
        
        try {
            Runtime runtime = Runtime.getRuntime();
            long maxMemory = runtime.maxMemory();
            long totalMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            long usedMemory = totalMemory - freeMemory;
            
            info.append("TOTAL: ").append(formatBytes(maxMemory)).append("\n");
            info.append("USED: ").append(formatBytes(usedMemory)).append("\n");
            info.append("FREE: ").append(formatBytes(maxMemory - usedMemory)).append("\n");
            
            int percentage = (int) ((usedMemory * 100) / maxMemory);
            info.append("USAGE: ").append(percentage).append("%\n");
            
            // Visual progress bar
            int barLength = 20;
            int filled = (percentage * barLength) / 100;
            info.append("[");
            for (int i = 0; i < barLength; i++) {
                info.append(i < filled ? "█" : "░");
            }
            info.append("]");
            
        } catch (Exception e) {
            info.append("RAM: Information unavailable");
        }
        
        return info.toString();
    }
    
    /**
     * Get network information
     */
    public static String getNetworkInfo() {
        StringBuilder info = new StringBuilder();
        
        try {
            java.util.Enumeration<java.net.NetworkInterface> interfaces = 
                java.net.NetworkInterface.getNetworkInterfaces();
            
            boolean hasConnection = false;
            
            while (interfaces.hasMoreElements()) {
                java.net.NetworkInterface networkInterface = interfaces.nextElement();
                
                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                    java.util.Enumeration<java.net.InetAddress> addresses = 
                        networkInterface.getInetAddresses();
                    
                    while (addresses.hasMoreElements()) {
                        java.net.InetAddress address = addresses.nextElement();
                        if (address instanceof java.net.Inet4Address) {
                            info.append("INTERFACE: ").append(networkInterface.getName()).append("\n");
                            info.append("LOCAL IP: ").append(address.getHostAddress()).append("\n");
                            info.append("STATUS: CONNECTED\n");
                            hasConnection = true;
                        }
                    }
                }
            }
            
            if (!hasConnection) {
                info.append("STATUS: DISCONNECTED\n");
                info.append("No active network connections");
            }
            
        } catch (Exception e) {
            info.append("NETWORK: Information unavailable");
        }
        
        return info.toString();
    }
    
    /**
     * Format bytes to human-readable format
     */
    private static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format(Locale.getDefault(), "%.1f %sB", 
            bytes / Math.pow(1024, exp), pre);
    }
}
