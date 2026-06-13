# RetroWatch 🕐

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Languages](https://img.shields.io/badge/Languages-Java%20%7C%20C%2B%2B%20%7C%20C-brightgreen)](https://github.com/Developer246/retrowatch)

Retro Watch is an **open-source smart watch project** built with Arduino and Android, combining retro aesthetics with modern functionality.

## 🌟 Features

- **Bluetooth Connectivity**: Seamless communication between Android phone and Arduino watch
- **Multiple Display Options**: Support for I2C and SPI OLED displays (SSD1306)
- **Notification System**: Real-time notifications on your wrist
- **Button Control**: Physical button interface for navigation
- **Low Power Design**: Optimized for battery efficiency
- **Modular Architecture**: Multiple variants for different configurations

## 📦 What's Included

### RetroWatch_Android
Android application for smart watch companion:
- **RetroWatch** - Full-featured app (Android 4.3+) ⭐ Recommended
- **RetroWatchLE** - Lightweight version (Android 4.0+, legacy support)

### RetroWatch_Arduino
Arduino firmware with multiple variants:

| Variant | Display | Interface | Status |
|---------|---------|-----------|--------|
| `RetroWatchArduino` | I2C OLED | Button | ✅ Supported |
| `RetroWatchArduino_no_button` | I2C OLED | Button-less | ✅ Supported |
| `RetroWatchArduino_spi` | SPI OLED | Button | ✅ Supported |
| `RetroWatchArduino_spi_no_button` | SPI OLED | Button-less | ✅ Supported |
| `RetroWatchArduino_u8glib` | I2C OLED (u8glib) | Button | ⭐ **Recommended** |
| `RetroWatchArduino_u8glib_no_button` | I2C OLED (u8glib) | Button-less | ✅ Supported |
| `RetroWatchArduino_u8glib_spi` | SPI OLED (u8glib) | Button | ✅ Supported |
| `RetroWatchArduino_u8glib_spi_no_button` | SPI OLED (u8glib) | Button-less | ✅ Supported |

## 🚀 Quick Start

### Hardware Requirements
- **Microcontroller**: Arduino-compatible board (UNO, Nano, Pro Mini)
- **Display**: SSD1306 OLED (0.96" or 1.3")
- **Bluetooth Module**: HC-05 or HC-06
- **Power**: Battery pack or USB power

### Arduino Setup

1. **Recommended**: Use `RetroWatchArduino_u8glib` variant
2. **Clone** this repository
3. **Open** Arduino IDE (v1.0.x recommended to avoid library conflicts)
4. **Install** required libraries:
   - [u8glib](https://github.com/olikraus/u8glib)
   - Adafruit libraries (for alternative variants)
5. **Select** your Arduino board and COM port
6. **Upload** the sketch

### Android Setup

1. Install the RetroWatch app from `/RetroWatch_Android`
2. Pair your phone with the Bluetooth module
3. Launch the app and connect to your watch
4. Enjoy real-time notifications on your wrist!

## 📚 Documentation

- [Korean Tutorial](http://www.hardcopyworld.com/ngine/aduino/index.php/archives/376)
- [English How-To Guide](http://www.hardcopyworld.com/ngine/aduino/index.php/archives/670) 
- [Android App Guide](http://www.hardcopyworld.com/ngine/android/index.php/archives/192)
- [Polish Translation](http://akademia.nettigo.pl/smartwatch/index.html) by Sebastian

## 🤝 Contributing

Contributions are welcome! Please feel free to:
- Report bugs and issues
- Submit pull requests with improvements
- Share your ideas and suggestions

## 📧 Support & Feedback

- **Email**: godstale@hotmail.com
- **Questions**: [Q&A Forum](http://www.hardcopyworld.com/gnuboard5/bbs/board.php?bo_table=qna)

## 📄 License

This project is licensed under the **Apache License 2.0** - see the [LICENSE](LICENSE) file for details.

## 🎯 Project Status

- ✅ Arduino firmware: Active
- ✅ Android app: Maintained
- 🔄 Community contributions: Encouraged
- 📈 Roadmap: Bluetooth LE support, Enhanced UI

---

**Made with ❤️ by the RetroWatch Community**
