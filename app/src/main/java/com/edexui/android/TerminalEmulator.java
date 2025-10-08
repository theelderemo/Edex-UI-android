package com.edexui.android;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Basic terminal emulator for Edex-UI
 * Provides command execution and output handling
 */
public class TerminalEmulator {
    
    private List<String> commandHistory;
    private StringBuilder outputBuffer;
    private String currentDirectory;
    
    public TerminalEmulator() {
        this.commandHistory = new ArrayList<>();
        this.outputBuffer = new StringBuilder();
        this.currentDirectory = "/";
        
        // Welcome message
        appendOutput("╔════════════════════════════════════════╗\n");
        appendOutput("║   EDEX-UI ANDROID TERMINAL v1.0       ║\n");
        appendOutput("║   Futuristic Terminal Interface        ║\n");
        appendOutput("╚════════════════════════════════════════╝\n");
        appendOutput("\n$ Welcome to Edex UI Terminal\n");
        appendOutput("$ Type 'help' for available commands\n\n");
    }
    
    /**
     * Execute a command
     */
    public String executeCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return "";
        }
        
        command = command.trim();
        commandHistory.add(command);
        
        String output = "";
        
        // Handle built-in commands
        if (command.equals("help")) {
            output = getHelpText();
        } else if (command.equals("clear")) {
            outputBuffer = new StringBuilder();
            return "Terminal cleared";
        } else if (command.startsWith("echo ")) {
            output = command.substring(5);
        } else if (command.equals("date")) {
            output = new java.util.Date().toString();
        } else if (command.equals("whoami")) {
            output = "android-user";
        } else if (command.equals("pwd")) {
            output = currentDirectory;
        } else if (command.equals("uname") || command.equals("uname -a")) {
            output = "Android " + android.os.Build.VERSION.RELEASE + 
                     " " + android.os.Build.DEVICE + 
                     " " + System.getProperty("os.arch");
        } else if (command.startsWith("cd ")) {
            String path = command.substring(3).trim();
            if (path.isEmpty() || path.equals("~")) {
                currentDirectory = "/";
                output = "Changed to " + currentDirectory;
            } else {
                output = "Directory change not fully supported in this demo";
            }
        } else if (command.equals("ls") || command.equals("ls -la")) {
            output = "Use the File Navigator module for file listing";
        } else {
            // Try to execute as system command (limited on Android)
            try {
                Process process = Runtime.getRuntime().exec(command);
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
                
                int exitCode = process.waitFor();
                
                if (exitCode == 0 && result.length() > 0) {
                    output = result.toString();
                } else {
                    BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));
                    StringBuilder errorResult = new StringBuilder();
                    
                    while ((line = errorReader.readLine()) != null) {
                        errorResult.append(line).append("\n");
                    }
                    
                    if (errorResult.length() > 0) {
                        output = "Error: " + errorResult.toString();
                    } else {
                        output = "Command executed (exit code: " + exitCode + ")";
                    }
                }
                
                reader.close();
            } catch (Exception e) {
                output = "Error: Command not found or not supported\n" +
                        "Use 'help' to see available commands";
            }
        }
        
        // Append to output buffer
        appendOutput("$ " + command + "\n");
        if (!output.isEmpty()) {
            appendOutput(output + "\n");
        }
        appendOutput("\n");
        
        return output;
    }
    
    /**
     * Get help text
     */
    private String getHelpText() {
        StringBuilder help = new StringBuilder();
        help.append("═══════════════════════════════════════\n");
        help.append("  EDEX-UI TERMINAL - AVAILABLE COMMANDS\n");
        help.append("═══════════════════════════════════════\n\n");
        help.append("Built-in Commands:\n");
        help.append("  help      - Show this help message\n");
        help.append("  clear     - Clear terminal output\n");
        help.append("  echo      - Display a line of text\n");
        help.append("  date      - Display current date/time\n");
        help.append("  whoami    - Display current user\n");
        help.append("  pwd       - Print working directory\n");
        help.append("  uname     - Display system information\n");
        help.append("  cd        - Change directory (limited)\n");
        help.append("  ls        - List files (use File Navigator)\n\n");
        help.append("System Commands:\n");
        help.append("  getprop   - Get system properties\n");
        help.append("  ps        - Show running processes\n");
        help.append("  top       - Display system resources\n");
        help.append("  df        - Display disk usage\n");
        help.append("  free      - Display memory usage\n");
        help.append("  uptime    - Show system uptime\n\n");
        help.append("Note: Some commands may require root access\n");
        help.append("═══════════════════════════════════════\n");
        
        return help.toString();
    }
    
    /**
     * Append text to output buffer
     */
    private void appendOutput(String text) {
        outputBuffer.append(text);
        
        // Limit buffer size to prevent memory issues
        if (outputBuffer.length() > 50000) {
            outputBuffer.delete(0, outputBuffer.length() - 40000);
        }
    }
    
    /**
     * Get full output buffer
     */
    public String getOutput() {
        return outputBuffer.toString();
    }
    
    /**
     * Get command history
     */
    public List<String> getCommandHistory() {
        return new ArrayList<>(commandHistory);
    }
    
    /**
     * Clear output buffer
     */
    public void clearOutput() {
        outputBuffer = new StringBuilder();
        appendOutput("Terminal cleared\n\n");
    }
    
    /**
     * Get current directory
     */
    public String getCurrentDirectory() {
        return currentDirectory;
    }
}
