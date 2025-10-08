package com.edexui.android;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Theme manager for Edex-UI
 * Handles theme selection and customization
 */
public class ThemeManager {
    
    private static final String PREFS_NAME = "EdexUIPrefs";
    private static final String KEY_THEME = "selected_theme";
    
    public enum Theme {
        TRON("Tron", "#00D9FF", "#FF6700", "#000814"),
        MATRIX("Matrix", "#00FF41", "#00FF41", "#000000"),
        CYBERPUNK("Cyberpunk", "#FF00FF", "#FFFF00", "#0A0014"),
        BLADE_RUNNER("Blade Runner", "#FF6B35", "#004E89", "#000814"),
        AMBER("Amber", "#FFBF00", "#FF8C00", "#000000"),
        GREEN("Green", "#00FF00", "#00AA00", "#000000");
        
        private final String name;
        private final String primaryColor;
        private final String accentColor;
        private final String backgroundColor;
        
        Theme(String name, String primaryColor, String accentColor, String backgroundColor) {
            this.name = name;
            this.primaryColor = primaryColor;
            this.accentColor = accentColor;
            this.backgroundColor = backgroundColor;
        }
        
        public String getName() {
            return name;
        }
        
        public String getPrimaryColor() {
            return primaryColor;
        }
        
        public String getAccentColor() {
            return accentColor;
        }
        
        public String getBackgroundColor() {
            return backgroundColor;
        }
    }
    
    private SharedPreferences preferences;
    private Theme currentTheme;
    
    public ThemeManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        loadTheme();
    }
    
    /**
     * Load saved theme
     */
    private void loadTheme() {
        String themeName = preferences.getString(KEY_THEME, Theme.TRON.name());
        try {
            currentTheme = Theme.valueOf(themeName);
        } catch (IllegalArgumentException e) {
            currentTheme = Theme.TRON;
        }
    }
    
    /**
     * Save theme preference
     */
    public void saveTheme(Theme theme) {
        currentTheme = theme;
        preferences.edit().putString(KEY_THEME, theme.name()).apply();
    }
    
    /**
     * Get current theme
     */
    public Theme getCurrentTheme() {
        return currentTheme;
    }
    
    /**
     * Get all available themes
     */
    public Theme[] getAllThemes() {
        return Theme.values();
    }
    
    /**
     * Apply theme to the application (requires activity restart)
     */
    public void applyTheme(Context context, Theme theme) {
        saveTheme(theme);
        // In a real implementation, this would trigger a UI refresh
    }
}
