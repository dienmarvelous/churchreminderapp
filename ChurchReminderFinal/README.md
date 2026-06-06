# ✝ Church Reminder — Android App

A production-ready Android app that sends voice calls + SMS reminders to all church members every Saturday evening before Sunday Service, powered by Africa's Talking API.

---

## How to Build the APK (3 easy steps)

### Requirements
- [Android Studio](https://developer.android.com/studio) (free) — download and install it
- Internet connection (to download dependencies automatically)

### Step 1 — Open the project
1. Open Android Studio
2. Click **"Open"** (or File → Open)
3. Select this **ChurchReminder** folder
4. Wait for Gradle to sync (1–3 minutes, downloads dependencies automatically)

### Step 2 — Build the APK
1. In Android Studio, go to **Build → Build Bundle(s) / APK(s) → Build APK(s)**
2. Wait ~1 minute
3. A notification pops up: **"APK(s) generated successfully"** — click **"Locate"**
4. The APK is at: `app/build/outputs/apk/debug/app-debug.apk`

### Step 3 — Install on your Android phone
**Option A — USB cable:**
1. Enable **Developer Options** on your phone (Settings → About Phone → tap "Build Number" 7 times)
2. Enable **USB Debugging** in Developer Options
3. Connect phone via USB → In Android Studio click **Run ▶**

**Option B — Transfer the APK file:**
1. Copy `app-debug.apk` to your phone (WhatsApp, email, USB, Google Drive)
2. On your phone, open the APK file
3. Allow "Install from unknown sources" if prompted
4. Install and open!

---

## App Setup (after installing)

1. **Get Africa's Talking credentials (free account):**
   - Go to https://africastalking.com → Sign Up (free)
   - Create a **production app** in your dashboard
   - Go to Settings → API Key → Generate and copy your key
   - Top up your wallet (even ₦500 to start)

2. **In the app → Settings tab:**
   - Enter your **API Username** (your production app name)
   - Enter your **API Key**
   - Enter your **caller phone number** (e.g. +2348012345678)
   - Set schedule: **Saturday, 6:00 PM**
   - Toggle **Auto-send every week** ON
   - Tap **Save**

3. **In the app → Members tab:**
   - Add members manually (Name, Phone, Group), OR
   - Import from CSV file (columns: Name, Phone, Group)

4. **In the app → Send tab:**
   - Customize your SMS and voice call message
   - Tap **Send Reminders to All Members**

---

## Project Structure

```
ChurchReminder/
├── app/
│   ├── src/main/
│   │   ├── assets/
│   │   │   └── index.html          ← Full app UI (HTML/CSS/JS)
│   │   ├── java/com/church/reminder/
│   │   │   ├── MainActivity.java   ← WebView host + alarm scheduling
│   │   │   ├── AlarmReceiver.java  ← Receives weekly alarm trigger
│   │   │   ├── BootReceiver.java   ← Restores alarm after phone reboot
│   │   │   └── ReminderService.java← Foreground service + notification
│   │   ├── res/
│   │   │   ├── layout/activity_main.xml
│   │   │   ├── values/strings.xml
│   │   │   ├── values/themes.xml
│   │   │   ├── drawable/ic_launcher.xml
│   │   │   └── xml/network_security_config.xml
│   │   └── AndroidManifest.xml
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── settings.gradle
├── gradle.properties
└── gradle/wrapper/gradle-wrapper.properties
```

---

## Africa's Talking Pricing (Nigeria)
- **Account + API key:** FREE
- **SMS:** ~₦5–10 per message (bulk discounts available)
- **Voice calls:** billed per minute
- **Sandbox:** completely free for testing

## Support
Built with: Android WebView + Africa's Talking API + AlarmManager
