package com.edexui.android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Dedicated Terminal Activity with interactive command execution
 */
public class TerminalActivity extends AppCompatActivity {

    private TextView terminalOutputText;
    private EditText terminalInput;
    private Button terminalSendButton;
    private ScrollView terminalScrollView;
    private TerminalEmulator terminal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terminal_tab);

        // Initialize terminal emulator
        terminal = new TerminalEmulator();

        // Initialize views
        terminalOutputText = findViewById(R.id.terminalOutputText);
        terminalInput = findViewById(R.id.terminalInput);
        terminalSendButton = findViewById(R.id.terminalSendButton);
        terminalScrollView = findViewById(R.id.terminalScrollView);

        // Display initial output
        updateOutput();

        // Set up send button click listener
        terminalSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeCommand();
            }
        });

        // Set up enter key listener on input
        terminalInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    executeCommand();
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * Execute the command from input field
     */
    private void executeCommand() {
        String command = terminalInput.getText().toString().trim();
        
        if (!command.isEmpty()) {
            // Execute command
            terminal.executeCommand(command);
            
            // Update output
            updateOutput();
            
            // Clear input
            terminalInput.setText("");
            
            // Scroll to bottom
            terminalScrollView.post(new Runnable() {
                @Override
                public void run() {
                    terminalScrollView.fullScroll(View.FOCUS_DOWN);
                }
            });
        }
    }

    /**
     * Update terminal output display
     */
    private void updateOutput() {
        terminalOutputText.setText(terminal.getOutput());
    }

    @Override
    public void onBackPressed() {
        // Return to main activity
        super.onBackPressed();
    }
}
