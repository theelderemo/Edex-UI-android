# Virtual Keyboard Testing Guide

## Visual Representation

### Terminal Screen with Keyboard

```
╔════════════════════════════════════════════════════════════════╗
║ TERMINAL OUTPUT                                                 ║
║                                                                 ║
║ $ help                                                          ║
║ ═══════════════════════════════════════════                    ║
║   EDEX-UI TERMINAL - AVAILABLE COMMANDS                        ║
║ ═══════════════════════════════════════                        ║
║                                                                 ║
║ Built-in Commands:                                             ║
║   help    - Show this help message                             ║
║   clear   - Clear terminal output                              ║
║   echo    - Display a line of text                             ║
║   date    - Display current date/time                          ║
║                                                                 ║
║ $ ▊                                                            ║
╠════════════════════════════════════════════════════════════════╣
║ $ [Input Field...........................] [Send] [⌨]         ║
╠════════════════════════════════════════════════════════════════╣
║ ┌────────────────────────────────────────────────────────┐    ║
║ │ ┌───┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌───┐             │    ║
║ │ │ESC││1││2││3││4││5││6││7││8││9││0││ ⌫ │             │    ║
║ │ └───┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└───┘             │    ║
║ │ ┌───┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐                 │    ║
║ │ │TAB││Q││W││E││R││T││Y││U││I││O││P│                 │    ║
║ │ └───┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘                 │    ║
║ │ ┌────┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌───┐              │    ║
║ │ │CTRL││A││S││D││F││G││H││J││K││L││ ↵ │              │    ║
║ │ └────┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└───┘              │    ║
║ │ ┌─────┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐┌─┐               │    ║
║ │ │SHIFT││Z││X││C││V││B││N││M││,││.││/│               │    ║
║ │ └─────┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘└─┘               │    ║
║ │ ┌───┐┌─────────────────────┐┌─┐┌─┐┌────┐            │    ║
║ │ │ALT││      SPACE          ││-││=││HIDE│            │    ║
║ │ └───┘└─────────────────────┘└─┘└─┘└────┘            │    ║
║ └────────────────────────────────────────────────────────┘    ║
╚════════════════════════════════════════════════════════════════╝
```

## Testing Scenarios

### 1. Basic Text Input
**Test:** Type "hello world"
- Click: h, e, l, l, o, SPACE, w, o, r, l, d
- **Expected:** "hello world" appears in input field
- **Feedback:** Each key press should produce haptic vibration + beep sound

### 2. Uppercase Input (Shift)
**Test:** Type "Hello"
- Click: SHIFT (should light up)
- Click: h (should become "H", SHIFT should dim)
- Click: e, l, l, o
- **Expected:** "Hello" appears in input field
- **Feedback:** SHIFT key shows visual state change

### 3. Command Execution
**Test:** Execute "help" command
- Type: h, e, l, p
- Click: Enter (↵) key OR click Send button
- **Expected:** Command executes, help text appears, input field clears
- **Feedback:** Terminal scrolls to bottom

### 4. Backspace Functionality
**Test:** Type and delete
- Type: t, e, s, t
- Click: Backspace (⌫) three times
- **Expected:** "t" remains in input field
- **Feedback:** Characters delete one at a time

### 5. Tab Character
**Test:** Insert tab
- Click: TAB
- **Expected:** Tab character inserted at cursor
- **Feedback:** Cursor moves forward

### 6. ESC Clear
**Test:** Clear input
- Type: some text
- Click: ESC
- **Expected:** Input field completely cleared
- **Feedback:** All text removed instantly

### 7. Hide Keyboard
**Test:** Toggle keyboard visibility
- Click: HIDE button on keyboard
- **Expected:** Keyboard disappears
- Click: ⌨ button (keyboard toggle)
- **Expected:** Keyboard reappears
- **Feedback:** Smooth visibility transition

### 8. Special Characters
**Test:** Type special characters
- Type: comma (,), period (.), slash (/), minus (-), equals (=)
- **Expected:** Each character appears correctly
- **Feedback:** Normal haptic + sound for each

### 9. Modifier Key States
**Test:** Toggle modifier keys
- Click: CTRL (should light up and stay lit)
- Click: CTRL again (should dim)
- Click: ALT (should light up)
- Click: ALT again (should dim)
- **Expected:** Visual state changes persist until toggled off
- **Feedback:** Modifier keys have different alpha/color when active

### 10. Cursor Position Insertion
**Test:** Insert text mid-string
- Type: "hello"
- Use device keyboard to move cursor between 'l' and 'l'
- Click: X on virtual keyboard
- **Expected:** "helxlo" appears
- **Feedback:** Text inserted at cursor, not at end

## Key Combinations (Future Enhancement)

The current implementation tracks modifier states but doesn't yet implement
control sequences like Ctrl+C or Ctrl+Z. These could be added in future
versions by detecting when both a modifier and another key are pressed.

## Color Scheme

- **Key Background (inactive)**: #151B3D (edex_surface)
- **Key Text**: #00D9FF (edex_primary/cyan)
- **Key Background (active modifier)**: #00D9FF (edex_primary)
- **Keyboard Background**: #151B3D (edex_surface)
- **Toggle Button**: #FF6700 (edex_accent/orange)

## Expected User Experience

1. **Initial State:** Keyboard hidden, only toggle button (⌨) visible
2. **Tap Toggle:** Keyboard slides into view below input area
3. **Tap Keys:** 
   - Instant haptic feedback
   - Beep sound plays
   - Character appears in input field
   - Cursor position updates
4. **Modifier Keys:** 
   - Toggle on/off with visual state
   - Affect next key press (Shift)
   - Stay toggled for combinations (Ctrl, Alt)
5. **HIDE or Toggle:** Keyboard disappears smoothly

## Known Limitations

1. **No Multi-touch:** Keys must be pressed sequentially
2. **No Long-press:** No special character variants on long press
3. **No Auto-complete:** No word suggestions
4. **Fixed Layout:** Only QWERTY, no layout switching
5. **No Control Sequences:** Ctrl+C, etc. not implemented
6. **Simple State:** Modifier keys don't send actual key events to terminal

## Accessibility Notes

- All keys are 48dp height (meets minimum touch target)
- High contrast: cyan (#00D9FF) on dark blue (#151B3D)
- Haptic feedback for users who can't hear sounds
- Sound feedback for users who can't feel haptics
- Large, clear labels on all keys
- Adequate spacing (2dp margins) between keys

## Integration Points

### TerminalActivity.java
- Line 47-50: VirtualKeyboard initialization
- Line 42-44: KeyboardContainer view reference
- Line 72-79: Toggle button click handler
- Line 98-106: toggleKeyboard() method

### terminal_tab.xml
- Line 66-73: Keyboard toggle button (⌨)
- Line 76-83: Keyboard container (initially hidden)

### VirtualKeyboard.java
- Line 46-50: Keyboard view creation
- Line 68-133: Key listener setup
- Line 171-195: Key press handling
- Line 197-238: Special key handling
- Line 240-266: Modifier key handling

## Performance Considerations

- Keyboard view created once at activity start
- Visibility toggled without recreation
- Minimal memory footprint (~363 lines)
- Efficient event handling with single listener per key
- No animations (instant feedback)
- No network calls or heavy operations

## Conclusion

The virtual keyboard implementation is complete and ready for testing on
an Android device or emulator. All core functionality has been implemented
according to the specifications in VISUAL_DESIGN.md and README.md.
