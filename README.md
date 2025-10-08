# Edex-UI-android

An Android application based on the Edex-UI project. I maintain the updated desktop version located here [eDEX UI Security Patched](https://github.com/theelderemo/eDEX-UI-security-patched)

# Core Functionality & App Structure
- Main Terminal Interface: The central component of the app is a fully-functional terminal emulator.
- Multiple Tabs: Users can open and manage multiple terminal sessions in tabs.
- Sci-Fi-Inspired UI: The entire user experience is wrapped in a futuristic, Tron-like visual theme. This includes the color scheme, typography, animations, and sound effects.
- Modular Dashboard: The main screen is a dashboard composed of several modules (widgets) that provide real-time system information.
- Touch-Friendly Interface: Although originally for desktops, the interface is designed with a touch-screen in mind, making it suitable for a tablet or large-screen phone.
- On-Screen Keyboard: A virtual QWERTY keyboard is displayed on-screen with support for terminal input.
- File System Navigator: A graphical file browser is integrated into the UI.

# Main UI Shell & Theming
- Customizable Themes: Users can choose from a variety of visual themes that change the color scheme, fonts, and overall appearance of the app.
- Live Wallpaper Mode: The app can run in a "live wallpaper" mode, displaying the animated UI on the device's home screen.
- Fullscreen Mode: The app can be run in a fully immersive fullscreen mode.
- Startup Animation: A boot-up sequence with a retro-futuristic animation and sound effects is shown when the app starts.
- Sound Effects: Most user interactions are accompanied by sound effects, such as key presses, window openings, and notifications.
- On-Screen Keyboard
    - QWERTY Layout: The on-screen keyboard uses a QWERTY layout optimized for terminal input.
    - Special Keys: Includes ESC, TAB, CTRL, ALT, SHIFT, and other special keys useful for terminal commands.
    - Toggle Button: A keyboard icon button in the terminal allows users to show/hide the virtual keyboard.
    - Haptic & Sound Feedback: Each key press provides haptic feedback and an audible click sound.
- File System Navigator
    - Directory Listing: Displays a list of files and folders in the current directory.
    - Directory Traversal: Users can navigate up and down the file system hierarchy.
    - File Icons: Different file types are represented by unique icons.
    - File Details: The navigator shows file permissions, size, and modification date.
    - Hidden File Toggle: A button to show or hide hidden files (dotfiles).
    - "Go to Path" Input: A text field allows users to directly enter a path to navigate to.
- System Monitoring Modules
    - System Information (SysInfo):
    - Displays the device's hostname, OS, and kernel version.
- Shows the CPU model and architecture.
    - CPU Usage (CPUInfo):
        - A real-time graph shows the overall CPU load.
        - Displays the current CPU temperature (if available).
        - A list of cores shows the usage of each individual CPU core.
- RAM Usage (RAMWatcher):
    - A real-time graph shows the system's memory usage.
    - Displays the total, used, and free RAM.
- Process List (TopList):
    - Shows a list of running processes, similar to the top command.
    - Displays the PID, user, CPU usage, and memory usage for each process.
- Network Information (ConnInfo & Netstat):
    - Shows the device's local IP address and public IP address.
    - Displays a list of active network connections.
    - Visualizes network traffic with a world map showing incoming connections (Globe).

# Additional Modules & Features
- Clock: An analog and digital clock display. <br/>
- Fuzzy Finder: A quick search tool to find and open files.
- Document Reader: A simple text file viewer.
- Media Player: A basic media player for audio and video files.
