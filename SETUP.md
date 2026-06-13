# RetroWatch Setup Guide 🔧

Complete step-by-step guide to build and deploy RetroWatch.

## Table of Contents
1. [Hardware Requirements](#hardware-requirements)
2. [Arduino Setup](#arduino-setup)
3. [Android Setup](#android-setup)
4. [Wiring Diagram](#wiring-diagram)
5. [Troubleshooting](#troubleshooting)

## Hardware Requirements

### Essential Components
- **Microcontroller**: Arduino UNO, Nano, or Pro Mini
- **Display**: SSD1306 OLED 0.96" or 1.3" monochrome
- **Bluetooth Module**: HC-05 (Master) or HC-06 (Slave)
- **Button**: Momentary push button (optional)
- **Power**: LiPo battery 3.7V/500mAh or USB power

### Optional Components
- Resistors for level shifting
- Capacitors for power filtering
- Custom watch case/band

## Arduino Setup

### Step 1: Install Arduino IDE
1. Download [Arduino IDE 1.0.x](https://www.arduino.cc/en/software/OldSoftwareReleases)
2. Install on your computer
3. Connect your Arduino board via USB

### Step 2: Install Libraries

#### Required for u8glib variant (recommended):
1. Open Arduino IDE
2. Go to `Sketch → Import Library → Manage Libraries`
3. Search for "u8glib" by Oliver Kraus
4. Click Install
5. Version: Latest stable (tested with 1.19.1+)

#### For Adafruit variants:
1. Install "Adafruit SSD1306" library
2. Install "Adafruit GFX Library"

### Step 3: Select Board & Variant

1. Open Arduino IDE
2. Go to `Tools → Board` and select your board (UNO, Nano, etc.)
3. Open the sketch from `/RetroWatch_Arduino` directory
4. Choose appropriate variant based on your hardware:

**For I2C connection (Recommended)**:
- `RetroWatchArduino_u8glib` (with button)
- `RetroWatchArduino_u8glib_no_button` (without button)

**For SPI connection**:
- `RetroWatchArduino_u8glib_spi` (with button)
- `RetroWatchArduino_u8glib_spi_no_button` (without button)

### Step 4: Configure Pin Mapping (if needed)
Edit pins in the sketch header:
```cpp
// I2C pins (default)
#define OLED_SDA_PIN A4  // Arduino SDA
#define OLED_SCL_PIN A5  // Arduino SCL
#define BUTTON_PIN 2     // Button pin

// Bluetooth pins (Software Serial)
#define BT_RX_PIN 0      // RX from HC-05
#define BT_TX_PIN 1      // TX to HC-05
```

### Step 5: Upload
1. Connect USB cable
2. Verify code: `Sketch → Verify/Compile`
3. Upload: `Sketch → Upload`
4. Monitor via `Tools → Serial Monitor` (9600 baud)

## Android Setup

### Step 1: Prerequisites
- Android 4.0+ (4.3+ recommended)
- Bluetooth enabled
- USB debugging enabled (for development)

### Step 2: Build from Source
1. Open Android Studio
2. Open project: `RetroWatch_Android/RetroWatch`
3. Sync Gradle dependencies
4. Build: `Build → Build Bundle/APK → Build APK`
5. Install on device

### Step 3: Configure Bluetooth
1. Pair your phone with HC-05/HC-06 module
2. Note the device MAC address
3. Launch RetroWatch app
4. Select paired device
5. Connect

### Step 4: Test
- Verify notifications appear on watch
- Test button responses
- Check battery consumption

## Wiring Diagram

### I2C Connection (Recommended)
```
Arduino Pro Mini / UNO:
  A4 (SDA) → OLED SDA
  A5 (SCL) → OLED SCL
  GND      → OLED GND
  5V       → OLED VCC
  
  Pin 2    → Button (other end to GND)
  GND      → Button
  
  D0 (RX)  ← HC-05 TX
  D1 (TX)  → HC-05 RX
  GND      → HC-05 GND
  5V       → HC-05 VCC
```

### SPI Connection
```
Arduino:
  Pin 11 (MOSI) → OLED DIN
  Pin 12 (MISO) → OLED MISO (not used)
  Pin 13 (SCK)  → OLED CLK
  Pin 8         → OLED CS
  Pin 9         → OLED DC
  GND           → OLED GND
  5V            → OLED VCC
  
  (Bluetooth wiring same as I2C)
```

## Troubleshooting

### Arduino Issues

**OLED not displaying:**
- Check I2C/SPI connections
- Verify library is installed correctly
- Try different I2C address (0x3C or 0x3D)
- Use Serial Monitor to debug

**Bluetooth not connecting:**
- Verify HC-05/HC-06 is powered
- Check baud rate (typically 9600)
- Test connection with AT commands
- Ensure TX/RX are not reversed

**Upload errors:**
- Select correct board type
- Check USB cable and port
- Try lower baud rate (57600)

### Android Issues

**App crashes on startup:**
- Check Bluetooth permissions in Android 6.0+
- Verify targetSdkVersion compatibility
- Check logcat for stack trace

**Notifications not appearing:**
- Enable notification access in Settings
- Verify Bluetooth is connected
- Check battery optimization settings

**Connection drops:**
- Reduce distance from phone
- Check power supply stability
- Update Android firmware

## Performance Tips
- Reduce update frequency if battery drains too fast
- Use power-saving mode on Bluetooth module
- Optimize display refresh rate
- Keep firmware updated

## Getting Help
- Check forums and existing issues
- Review original documentation links
- Email: godstale@hotmail.com

---
Happy building! 🎉
