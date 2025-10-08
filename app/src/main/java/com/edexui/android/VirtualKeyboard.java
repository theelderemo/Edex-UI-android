package com.edexui.android;

import android.content.Context;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Virtual Keyboard manager for Edex-UI
 * Handles on-screen keyboard display and input
 */
public class VirtualKeyboard {
    
    private Context context;
    private View keyboardView;
    private EditText targetInput;
    private SoundManager soundManager;
    
    // Modifier key states
    private boolean shiftPressed = false;
    private boolean ctrlPressed = false;
    private boolean altPressed = false;
    
    // Keyboard visibility listener
    private KeyboardVisibilityListener visibilityListener;
    
    public interface KeyboardVisibilityListener {
        void onKeyboardHidden();
    }
    
    public VirtualKeyboard(Context context, SoundManager soundManager) {
        this.context = context;
        this.soundManager = soundManager;
    }
    
    /**
     * Initialize the keyboard view
     */
    public View createKeyboardView(LayoutInflater inflater) {
        keyboardView = inflater.inflate(R.layout.keyboard_layout, null);
        setupKeyListeners();
        return keyboardView;
    }
    
    /**
     * Set the target EditText for keyboard input
     */
    public void setTargetInput(EditText input) {
        this.targetInput = input;
    }
    
    /**
     * Set keyboard visibility listener
     */
    public void setVisibilityListener(KeyboardVisibilityListener listener) {
        this.visibilityListener = listener;
    }
    
    /**
     * Setup click listeners for all keyboard keys
     */
    private void setupKeyListeners() {
        if (keyboardView == null) return;
        
        // Row 1 - Numbers and ESC
        setupKey(R.id.key_esc, KeyEvent.ESC);
        setupKey(R.id.key_1, "1");
        setupKey(R.id.key_2, "2");
        setupKey(R.id.key_3, "3");
        setupKey(R.id.key_4, "4");
        setupKey(R.id.key_5, "5");
        setupKey(R.id.key_6, "6");
        setupKey(R.id.key_7, "7");
        setupKey(R.id.key_8, "8");
        setupKey(R.id.key_9, "9");
        setupKey(R.id.key_0, "0");
        setupKey(R.id.key_backspace, KeyEvent.BACKSPACE);
        
        // Row 2 - QWERTY top row
        setupKey(R.id.key_tab, KeyEvent.TAB);
        setupKey(R.id.key_q, "q");
        setupKey(R.id.key_w, "w");
        setupKey(R.id.key_e, "e");
        setupKey(R.id.key_r, "r");
        setupKey(R.id.key_t, "t");
        setupKey(R.id.key_y, "y");
        setupKey(R.id.key_u, "u");
        setupKey(R.id.key_i, "i");
        setupKey(R.id.key_o, "o");
        setupKey(R.id.key_p, "p");
        
        // Row 3 - QWERTY middle row
        setupModifierKey(R.id.key_ctrl, ModifierKey.CTRL);
        setupKey(R.id.key_a, "a");
        setupKey(R.id.key_s, "s");
        setupKey(R.id.key_d, "d");
        setupKey(R.id.key_f, "f");
        setupKey(R.id.key_g, "g");
        setupKey(R.id.key_h, "h");
        setupKey(R.id.key_j, "j");
        setupKey(R.id.key_k, "k");
        setupKey(R.id.key_l, "l");
        setupKey(R.id.key_enter, KeyEvent.ENTER);
        
        // Row 4 - QWERTY bottom row
        setupModifierKey(R.id.key_shift, ModifierKey.SHIFT);
        setupKey(R.id.key_z, "z");
        setupKey(R.id.key_x, "x");
        setupKey(R.id.key_c, "c");
        setupKey(R.id.key_v, "v");
        setupKey(R.id.key_b, "b");
        setupKey(R.id.key_n, "n");
        setupKey(R.id.key_m, "m");
        setupKey(R.id.key_comma, ",");
        setupKey(R.id.key_period, ".");
        setupKey(R.id.key_slash, "/");
        
        // Row 5 - Space bar and controls
        setupModifierKey(R.id.key_alt, ModifierKey.ALT);
        setupKey(R.id.key_space, " ");
        setupKey(R.id.key_minus, "-");
        setupKey(R.id.key_equals, "=");
        setupKey(R.id.key_hide, KeyEvent.HIDE);
    }
    
    /**
     * Setup a regular key listener
     */
    private void setupKey(int buttonId, final String text) {
        Button button = keyboardView.findViewById(buttonId);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleKeyPress(v, text);
                }
            });
        }
    }
    
    /**
     * Setup a special key listener
     */
    private void setupKey(int buttonId, final KeyEvent keyEvent) {
        Button button = keyboardView.findViewById(buttonId);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleSpecialKey(v, keyEvent);
                }
            });
        }
    }
    
    /**
     * Setup a modifier key listener (Shift, Ctrl, Alt)
     */
    private void setupModifierKey(int buttonId, final ModifierKey modifier) {
        Button button = keyboardView.findViewById(buttonId);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleModifierKey(v, modifier);
                }
            });
        }
    }
    
    /**
     * Handle regular key press
     */
    private void handleKeyPress(View view, String text) {
        if (targetInput == null) return;
        
        // Provide feedback
        provideFeedback(view);
        
        // Apply shift modifier
        String inputText = text;
        if (shiftPressed) {
            inputText = text.toUpperCase();
            // Reset shift after use
            shiftPressed = false;
            updateModifierButtonState(R.id.key_shift, false);
        }
        
        // Insert text at cursor position
        int start = targetInput.getSelectionStart();
        int end = targetInput.getSelectionEnd();
        targetInput.getText().replace(Math.min(start, end), Math.max(start, end), inputText);
    }
    
    /**
     * Handle special keys (Enter, Backspace, Tab, etc.)
     */
    private void handleSpecialKey(View view, KeyEvent keyEvent) {
        if (targetInput == null) return;
        
        // Provide feedback
        provideFeedback(view);
        
        switch (keyEvent) {
            case ENTER:
                // Trigger the action done on the EditText
                targetInput.onEditorAction(android.view.inputmethod.EditorInfo.IME_ACTION_DONE);
                break;
                
            case BACKSPACE:
                int start = targetInput.getSelectionStart();
                int end = targetInput.getSelectionEnd();
                if (start > 0 || end > 0) {
                    if (start == end) {
                        // Delete one character before cursor
                        targetInput.getText().delete(start - 1, start);
                    } else {
                        // Delete selection
                        targetInput.getText().delete(Math.min(start, end), Math.max(start, end));
                    }
                }
                break;
                
            case TAB:
                // Insert tab character
                int tabStart = targetInput.getSelectionStart();
                int tabEnd = targetInput.getSelectionEnd();
                targetInput.getText().replace(Math.min(tabStart, tabEnd), Math.max(tabStart, tabEnd), "\t");
                break;
                
            case ESC:
                // Clear the input field
                targetInput.setText("");
                break;
                
            case HIDE:
                // Hide the keyboard
                hideKeyboard();
                break;
        }
    }
    
    /**
     * Handle modifier key press
     */
    private void handleModifierKey(View view, ModifierKey modifier) {
        // Provide feedback
        provideFeedback(view);
        
        switch (modifier) {
            case SHIFT:
                shiftPressed = !shiftPressed;
                updateModifierButtonState(R.id.key_shift, shiftPressed);
                break;
                
            case CTRL:
                ctrlPressed = !ctrlPressed;
                updateModifierButtonState(R.id.key_ctrl, ctrlPressed);
                break;
                
            case ALT:
                altPressed = !altPressed;
                updateModifierButtonState(R.id.key_alt, altPressed);
                break;
        }
    }
    
    /**
     * Update the visual state of a modifier button
     */
    private void updateModifierButtonState(int buttonId, boolean pressed) {
        Button button = keyboardView.findViewById(buttonId);
        if (button != null) {
            if (pressed) {
                button.setAlpha(1.0f);
                button.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
                    context.getResources().getColor(R.color.edex_primary)));
            } else {
                button.setAlpha(0.7f);
                button.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
                    context.getResources().getColor(R.color.edex_surface)));
            }
        }
    }
    
    /**
     * Provide haptic and audio feedback
     */
    private void provideFeedback(View view) {
        // Haptic feedback
        view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
        
        // Sound feedback
        if (soundManager != null) {
            soundManager.playBeep();
        }
    }
    
    /**
     * Show the keyboard
     */
    public void showKeyboard() {
        if (keyboardView != null) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }
    
    /**
     * Hide the keyboard
     */
    public void hideKeyboard() {
        if (keyboardView != null) {
            keyboardView.setVisibility(View.GONE);
        }
        if (visibilityListener != null) {
            visibilityListener.onKeyboardHidden();
        }
    }
    
    /**
     * Toggle keyboard visibility
     */
    public void toggleKeyboard() {
        if (keyboardView != null) {
            if (keyboardView.getVisibility() == View.VISIBLE) {
                hideKeyboard();
            } else {
                showKeyboard();
            }
        }
    }
    
    /**
     * Check if keyboard is visible
     */
    public boolean isVisible() {
        return keyboardView != null && keyboardView.getVisibility() == View.VISIBLE;
    }
    
    /**
     * Get the keyboard view
     */
    public View getKeyboardView() {
        return keyboardView;
    }
    
    /**
     * Special key events
     */
    private enum KeyEvent {
        ENTER, BACKSPACE, TAB, ESC, HIDE
    }
    
    /**
     * Modifier keys
     */
    private enum ModifierKey {
        SHIFT, CTRL, ALT
    }
}
