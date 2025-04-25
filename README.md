Modular Weather Android App

A modularized Android application that fetches and displays the current weather and a 7-day forecast for a given city using free Weatherbit API.

Table of Contents

Features

Architecture

Module Structure

Tech Stack

Getting Started

Prerequisites

Installation

Configuration

Usage

Screenshots

Contributing

License

Contact

Features

Enter a city name and retrieve:

🌡️ Current weather (temperature, condition, humidity, wind)

📅 7-day daily forecast (high/low, description)

Modular architecture for maintainability and testability

Caching support (optional Room integration)

Clean, responsive UI with Jetpack Compose (or XML)

Architecture

This project follows Clean Architecture principles and is split into feature, data, and core modules to promote separation of concerns and ease of testing.

Module Structure

:app                # Android application module (UI, navigation)
:core               # Core utilities, network setup (Retrofit, OkHttp)
:data               # Data sources, repositories, DTOs, caching
:domain (optional)  # Business logic, use cases
:feature-weather    # Weather feature: ViewModel, UI screens

Tech Stack

Language: Kotlin

UI: Jetpack Compose (or XML), ViewModel, StateFlow

Networking: Retrofit, OkHttp, Kotlinx Serialization

Asynchronous: Kotlin Coroutines

DI: Hilt (or Koin)

Caching: Room (optional) or DataStore

Testing: JUnit, MockK, Espresso

Getting Started

Prerequisites

Android Studio Flamingo or later

Android SDK API Level 21+

Kotlin 1.8+

Installation

Clone the repository:

git clone https://github.com/your-username/modular-weather-app.git
cd modular-weather-app

Open in Android Studio and let it sync Gradle.

Configuration

Register for a free Weatherbit API key at https://www.weatherbit.io/.

In local.properties, add:

WEATHERBIT_API_KEY=your_api_key_here

The core module’s Retrofit builder reads the key via BuildConfig.WEATHERBIT_API_KEY.

Usage

Run the app on an emulator or device.

Enter a city name in the search bar.

Press Search to fetch current weather and 7-day forecast.

Swipe through the daily forecast list.

Screenshots
