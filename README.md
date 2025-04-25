# Modular Weather Android App

A modularized Android application that fetches and displays the current weather and a 7-day forecast for any city, using the free Weatherbit API.

---

## 🚀 Table of Contents

- [About](#about)  
- [Features](#features)  
- [Architecture](#architecture)  
- [Module Structure](#module-structure)  
- [Tech Stack](#tech-stack)  
- [Prerequisites](#prerequisites)  
- [Setup & Installation](#setup--installation)  
- [API Configuration](#api-configuration)  
- [Usage](#usage)  
- [Screenshots](#screenshots)  
- [Contributing](#contributing)  
- [License](#license)  
- [Contact](#contact)  

---

## 📖 About

This Android app lets users enter a city name and immediately see:

- 🌡️ **Current weather** (temperature, humidity, wind, description)  
- 📅 **7-day daily forecast** (high/low temps, weather summary)  

It’s built with a clean, modular architecture so you can easily extend, test, and maintain it.

---

## ✨ Features

- Search by city name (with optional country code)  
- Displays current conditions and 7-day forecast  
- Offline caching of last-fetched data (via Room)  
- Responsive UI in Jetpack Compose (or XML fallback)  
- Dependency Injection with Hilt  
- Asynchronous calls with Kotlin Coroutines  

---

## 🧱 Architecture

We follow **Clean Architecture** and separate concerns into modules:

```
:app                # Android application (UI, navigation)
:core               # Networking setup, utilities, constants
:data               # API models, Retrofit service, Repository, Room entities/DAO
:domain (optional)  # Use-cases / business logic
:feature-weather    # ViewModel, Compose screens, UI logic
```

---

## 🛠 Tech Stack

| Layer         | Technology                   |
|---------------|------------------------------|
| Language      | Kotlin                       |
| UI            | Jetpack Compose (or XML)     |
| Networking    | Retrofit, OkHttp             |
| Serialization | Kotlinx Serialization        |
| DI            | Hilt                         |
| Async         | Kotlin Coroutines            |
| Caching       | Room                         |
| Testing       | JUnit, MockK, Espresso       |

---

## 🔧 Prerequisites

- Android Studio Flamingo or later  
- Android SDK API Level 21+  
- Kotlin 1.8+  
- Internet connection for API calls  

---

## ⚙️ Setup & Installation

1. **Clone the repository**  
   ```bash
   git clone https://github.com/ali72-20/modular-weather-app.git
   cd modular-weather-app
   ```

2. **Open in Android Studio**  
   - File → Open → select the project folder  
   - Let Gradle sync and download dependencies  

3. **Build & Run**  
   - Connect an Android device or start an emulator  
   - Click Run ▶️  

---

## 🔑 API Configuration

1. **Get a free Weatherbit API key**  
   - Sign up at https://www.weatherbit.io/  
   - Copy your API key

2. **Add your key to local properties**  
   In the project root, open `local.properties` and add:
   ```properties
   WEATHERBIT_API_KEY=YOUR_API_KEY_HERE
   ```

3. **Access in code**  
   The `core` module’s Retrofit builder reads:
   ```kotlin
   BuildConfig.WEATHERBIT_API_KEY
   ```

---

## 🚀 Usage

1. Launch the app on your device/emulator.  
2. Enter a city name (e.g. “London”) in the search field.  
3. Tap **Search**.  
4. View current weather at the top and swipe through the 7-day forecast below.  

---

## 📸 Screenshots

| Current Weather                         | 7-Day Forecast                          |
|-----------------------------------------|-----------------------------------------|
| ![current-weather](screenshots/current.png) | ![7-day-forecast](screenshots/forecast.png) |

---

## 🤝 Contributing

1. Fork this repository.  
2. Create a feature branch:  
   ```bash
   git checkout -b feature/YourFeatureName
   ```  
3. Commit your changes:  
   ```bash
   git commit -m "Add YourFeatureName"
   ```  
4. Push to your fork:  
   ```bash
   git push origin feature/YourFeatureName
   ```  
5. Open a Pull Request against `main`.  

Please follow the existing code style and include unit/UI tests where applicable.

---

## 📝 License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

## 📬 Contact

Ali Safwat  
- GitHub: [@ali72-20](https://github.com/ali72-20)  
- LinkedIn: [ali-safwat-1801a1222](https://www.linkedin.com/in/ali-safwat-1801a1222)  
- Email: ali.safwat@example.com  

Project link: https://github.com/ali72-20/modular-weather-app

