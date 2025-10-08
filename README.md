# Edex-UI-android

An Android application based on the Edex-UI project. I maintain the updated desktop version located here [eDEX UI Security Patched](https://github.com/theelderemo/eDEX-UI-security-patched)

---

## 🚀 What's Working / Implemented

- **Main Terminal Interface** — A fully functional terminal emulator serves as the app's central component.  
- **Sci-Fi-Inspired UI** — A futuristic, Tron-like visual theme featuring bold colors, typography, animations, and sound effects.  
- **Modular Dashboard** — The main screen is a dashboard composed of several modules that provide real-time system information.  
- **Touch-Friendly Interface** — Designed for touch screens, making it ideal for tablets or large-screen phones.  
- **File System Navigator** — A graphical file browser integrated directly into the UI.  
- **Fullscreen Mode** — Supports a fully immersive fullscreen experience.  
- **Startup Animation** — Displays a retro-futuristic boot-up animation with accompanying sound effects.  

---

## 🧩 To-Do List

### 🔹 Terminal & UI Enhancements
- [ ] **Multiple Tabs** — Allow users to open and manage multiple terminal sessions in tabs.  
- [ ] **On-Screen Keyboard**
  - [ ] Integrate and enable an on-screen keyboard.  
  - [ ] Add support for multiple layouts (Dvorak, Colemak, etc.).  
- [ ] **Theme Customization** — Implement a UI for theme selection and customization.  
- [ ] **Live Wallpaper Mode** — Enable Edex-UI as a dynamic live wallpaper.

### 🔹 File System Navigator
- [ ] Add icons for different file types.  
- [ ] Display file details (permissions, size, modification date).  
- [ ] Add a toggle to show/hide hidden files.  
- [ ] Implement a “Go to Path” input field.

### 🔹 Network & Modules
- [ ] **Network Information** — Add a world map visualization for network traffic.  
- [ ] **Additional Modules**
  - [ ] Fuzzy Finder  
  - [ ] Document Reader  
  - [ ] Media Player  

---

# Core Functionality & App Structure
- Main Terminal Interface: The central component of the app is a fully-functional terminal emulator.
- Multiple Tabs: Users can open and manage multiple terminal sessions in tabs.
- Sci-Fi-Inspired UI: The entire user experience is wrapped in a futuristic, Tron-like visual theme. This includes the color scheme, typography, animations, and sound effects.
- Modular Dashboard: The main screen is a dashboard composed of several modules (widgets) that provide real-time system information.
- Touch-Friendly Interface: Although originally for desktops, the interface is designed with a touch-screen in mind, making it suitable for a tablet or large-screen phone.
- On-Screen Keyboard: A virtual keyboard is displayed on-screen, with multiple layouts and themes.
- File System Navigator: A graphical file browser is integrated into the UI.

# Main UI Shell & Theming
- Customizable Themes: Users can choose from a variety of visual themes that change the color scheme, fonts, and overall appearance of the app.
- Live Wallpaper Mode: The app can run in a "live wallpaper" mode, displaying the animated UI on the device's home screen.
- Fullscreen Mode: The app can be run in a fully immersive fullscreen mode.
- Startup Animation: A boot-up sequence with a retro-futuristic animation and sound effects is shown when the app starts.
- Sound Effects: Most user interactions are accompanied by sound effects, such as key presses, window openings, and notifications.
- On-Screen Keyboard
    - Multiple Layouts: The on-screen keyboard supports various international and alternative layouts like Dvorak and Colemak.
    - Key Press Animations & Sounds: Each key press is visualized with an animation and an audible click sound.
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


