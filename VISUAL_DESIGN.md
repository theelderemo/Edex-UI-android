# Edex-UI Android - Visual Design Documentation

## Overview
This document describes the visual appearance and user interface design of the Edex-UI Android application.

## Theme: Tron-Inspired Cyberpunk Aesthetic

### Color Palette

#### Primary Colors
- **Cyan (`#00D9FF`)**: Main accent color, used for:
  - Text headings
  - Borders
  - Interactive elements
  - Primary buttons
  - Clock display

- **Orange (`#FF6700`)**: Secondary accent color, used for:
  - Highlights
  - Warning states
  - Selected items
  - Attention elements

#### Background Colors
- **Deep Space Blue (`#0A0E27`)**: Main background
- **Dark Surface Blue (`#151B3D`)**: Card/module backgrounds
- **Terminal Black (`#000814`)**: Terminal background

#### Status Colors
- **Success Green (`#00FF9F`)**: Terminal output, success messages
- **Warning Yellow (`#FFB800`)**: Warnings, alerts
- **Error Magenta (`#FF006E`)**: Errors, critical states

#### Text Colors
- **Primary Text (`#00D9FF`)**: Headers, important text
- **Secondary Text (`#00A8CC`)**: Body text, descriptions
- **Monospace Font**: All system data displays

## Screen Layouts

### 1. Splash Screen (Startup)

```
╔══════════════════════════════════════╗
║   EDEX-UI ANDROID TERMINAL v1.0     ║
║   Futuristic Terminal Interface      ║
╚══════════════════════════════════════╝

        INITIALIZING SYSTEM...
        LOADING MODULES...
        ESTABLISHING CONNECTIONS...
        SYSTEM READY

              v1.0.0
```

**Visual Effects:**
- ASCII art logo fades in (1 second)
- Boot messages appear sequentially (600ms intervals)
- Each message has a fade-in animation
- Cyan text on dark background
- Full-screen immersive mode

### 2. Main Dashboard

```
┌────────────────────────────────────────────────┐
│ EDEX-UI                          HH:MM:SS      │ ← Top Bar (Dark Blue)
├────────────────────────────────────────────────┤
│                                                │
│ ╔══════════════════════════════════════════╗  │
│ ║ SYSTEM INFO                               ║  │ ← Card 1 (Fades in)
│ ║ HOSTNAME: device-name                     ║  │
│ ║ OS: Android 15                            ║  │
│ ║ MANUFACTURER: Brand                       ║  │
│ ║ MODEL: Device Model                       ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ ╔══════════════════════════════════════════╗  │
│ ║ CPU INFO                                  ║  │ ← Card 2 (Fades in)
│ ║ CPU: Processor Name                       ║  │
│ ║ CORES: 8                                  ║  │
│ ║ ARCHITECTURE: arm64-v8a                   ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ ╔══════════════════════════════════════════╗  │
│ ║ RAM USAGE                                 ║  │ ← Card 3 (Fades in)
│ ║ TOTAL: 8.0 GB                             ║  │
│ ║ USED: 4.2 GB                              ║  │
│ ║ FREE: 3.8 GB                              ║  │
│ ║ USAGE: 52%                                ║  │
│ ║ [██████████░░░░░░░░░░]                   ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ ╔══════════════════════════════════════════╗  │
│ ║ NETWORK INFO                              ║  │ ← Card 4 (Fades in)
│ ║ INTERFACE: wlan0                          ║  │
│ ║ LOCAL IP: 192.168.1.100                   ║  │
│ ║ STATUS: CONNECTED                         ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ ╔══════════════════════════════════════════╗  │
│ ║ TERMINAL                    [Click to open]║  │ ← Terminal Card
│ ║                                            ║  │   (Clickable)
│ ║ $ Welcome to Edex UI Terminal             ║  │
│ ║ $ Type 'help' for available commands      ║  │
│ ║ $ Type commands here...                   ║  │
│ ║                                            ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
│ ╔══════════════════════════════════════════╗  │
│ ║ FILE NAVIGATOR                            ║  │ ← File Browser
│ ║ Path: /storage/emulated/0                 ║  │
│ ║                                            ║  │
│ ║ [DIR]  ..                                 ║  │
│ ║ [DIR]  Documents                          ║  │
│ ║ [DIR]  Download                           ║  │
│ ║ [FILE] README.txt (1.2 KB)                ║  │
│ ╚══════════════════════════════════════════╝  │
│                                                │
└────────────────────────────────────────────────┘
```

**Card Design:**
- Rounded corners (4dp)
- Elevation shadow (4dp)
- Cyan headers in bold
- White/cyan body text in monospace
- Dark blue surface color
- Sequential fade-in animations (100ms stagger)
- 8dp padding inside cards
- 4dp margin between cards

**Interactive Elements:**
- Terminal card has ripple effect on touch
- Click opens full terminal interface
- All data updates every 2 seconds
- Smooth animations for data changes

### 3. Terminal Screen (Full)

```
┌────────────────────────────────────────────────┐
│ ╔════════════════════════════════════════════╗│
│ ║   EDEX-UI ANDROID TERMINAL v1.0           ║│
│ ║   Futuristic Terminal Interface            ║│
│ ╚════════════════════════════════════════════╝│
│                                                │
│ $ Welcome to Edex UI Terminal                 │
│ $ Type 'help' for available commands          │
│                                                │
│ $ help                                         │
│ ═══════════════════════════════════════════   │
│   EDEX-UI TERMINAL - AVAILABLE COMMANDS       │
│ ═══════════════════════════════════════════   │
│                                                │
│ Built-in Commands:                             │
│   help      - Show this help message           │
│   clear     - Clear terminal output            │
│   echo      - Display a line of text           │
│   date      - Display current date/time        │
│   whoami    - Display current user             │
│   pwd       - Print working directory          │
│   uname     - Display system information       │
│   cd        - Change directory (limited)       │
│   ls        - List files (use File Navigator)  │
│                                                │
│ System Commands:                               │
│   getprop   - Get system properties            │
│   ps        - Show running processes           │
│                                                │
│ $ ▊                                            │ ← Input field
├────────────────────────────────────────────────┤
│ $ [Input Field]                      [Send]    │ ← Input bar
└────────────────────────────────────────────────┘
```

**Terminal Features:**
- Green text (`#00FF9F`) for output
- Cyan prompt (`$`)
- Monospace font throughout
- Scrollable output area
- Command input at bottom
- Send button (cyan background)
- Full-screen black background

### 4. On-Screen Keyboard Layout

```
┌────────────────────────────────────────────────┐
│[ESC][1][2][3][4][5][6][7][8][9][0][⌫]        │
│[TAB][Q][W][E][R][T][Y][U][I][O][P]            │
│[CTRL][A][S][D][F][G][H][J][K][L][↵]           │
│[SHIFT][Z][X][C][V][B][N][M][,][.][/]          │
│[ALT][      SPACE      ][-][=][HIDE]           │
└────────────────────────────────────────────────┘
```

**Keyboard Design:**
- Each key: 48dp height, rounded corners
- Dark blue surface with cyan text
- 2dp margin between keys
- Tactile feedback on press
- Sound effect on keypress
- Sci-fi styled font

### 5. Process List Screen

```
┌────────────────────────────────────────────────┐
│ PROCESS LIST (TOP)                             │
├────────────────────────────────────────────────┤
│ ═══════════════════════════════════════════   │
│   PROCESS LIST (TOP)                           │
│ ═══════════════════════════════════════════   │
│                                                │
│ USER     PID   PPID  VSIZE  RSS   WCHAN  PC   │
│ root     1     0     2048   512   0      S    │
│ system   2     1     4096   1024  0      S    │
│ u0_a123  3456  1     65536  8192  0      S    │
│ ...                                            │
│                                                │
│ Current Process:                               │
│ PID: 12345                                     │
│ UID: 10123                                     │
│ TID: 12346                                     │
│                                                │
│ Memory Usage:                                  │
│ Total: 256.0 MB                                │
│ Free: 128.5 MB                                 │
│ Max: 512.0 MB                                  │
│                                                │
│ ═══════════════════════════════════════════   │
└────────────────────────────────────────────────┘
```

**Process List Features:**
- Monospace layout for alignment
- Updates every 3 seconds
- Cyan headers
- White/cyan data text
- Scrollable content
- Dark background

## Typography

### Fonts
- **Headers**: Roboto Bold / System Default Bold
- **Body Text**: Roboto Regular / System Default
- **Monospace**: Monospace system font (for all data displays)

### Font Sizes
- App Title: 18sp
- Card Headers: 16sp
- Body Text: 12sp
- Terminal: 12sp (monospace)
- Button Text: 14sp
- Clock: 14sp

## Animations

### Entry Animations
1. **Cards Fade In**: 500ms duration, staggered by 100ms
2. **Splash Logo**: Fade in over 1000ms
3. **Boot Messages**: Fade in 300ms, 600ms intervals

### Interactive Animations
- **Button Press**: Ripple effect (Material Design)
- **Card Click**: Scale down slightly (0.98x)
- **Data Update**: Subtle fade (200ms)

### Special Effects
- **Glow**: Alpha pulse on important elements
- **Glitch**: Random position shifts (cyberpunk effect)
- **Pulse**: Scale animation (1.0 to 1.1)

## Layout Specifications

### Spacing
- Screen Padding: 8dp - 16dp
- Card Margin: 4dp - 8dp
- Internal Padding: 12dp - 16dp
- Element Spacing: 8dp

### Elevation
- Cards: 4dp elevation
- Buttons: 2dp elevation
- Floating elements: 6dp elevation

### Corners
- Cards: 4dp radius
- Buttons: 4dp radius
- Small elements: 2dp radius

## Accessibility

### Touch Targets
- Minimum size: 48dp x 48dp
- Buttons: 48dp height minimum
- Card click areas: Full card

### Contrast
- High contrast between text and background
- Cyan on dark blue: WCAG AA compliant
- Green on black: WCAG AA compliant

### Readability
- Monospace font for data clarity
- 12sp minimum text size
- Adequate line spacing
- Clear visual hierarchy

## Responsive Design

### Orientations
- **Landscape** (Recommended): Full dashboard visible
- **Portrait**: Scrollable layout, same design

### Screen Sizes
- **Tablets**: Optimal experience, larger cards
- **Phones**: Scaled appropriately, scrollable
- **Large Screens**: Extra padding, centered content

## Status Bar & Navigation

### Full-Screen Mode
- Status bar hidden
- Navigation bar hidden
- Immersive sticky mode
- Swipe from edges to reveal system UI

## Visual Feedback

### User Actions
- **Touch**: Ripple effect (cyan)
- **Press**: Sound effect (optional beep)
- **Success**: Green highlight
- **Error**: Red/magenta highlight
- **Loading**: Subtle animation

## Theming System

### Available Themes
1. **Tron** (Default): Cyan/Orange on Dark Blue
2. **Matrix**: Green on Black
3. **Cyberpunk**: Magenta/Yellow on Purple
4. **Blade Runner**: Orange/Blue
5. **Amber**: Orange on Black (retro)
6. **Green**: Green on Black (classic)

Each theme changes:
- Primary color
- Accent color
- Background color
- Text colors

## Implementation Notes

All layouts use:
- ConstraintLayout for complex screens
- LinearLayout for simple stacks
- CardView for module containers
- ScrollView for long content
- Material Design components

Colors are defined in `colors.xml`
Themes in `themes.xml`
Layouts in `res/layout/`
All measurements in dp (density-independent pixels)
