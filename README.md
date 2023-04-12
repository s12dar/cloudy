<img src="/arts/cloudy_feature_preview.png">

## cloudy ☁️
Cloudy is an open-source weather app for Android that provides users with up-to-date weather information based on their current location, as well as a week-long forecast. It is built using the latest Android technologies and follows the MVVM with clean architecture, with an offline-first approach that caches weather data for improved performance.

### Tech stack 🏗
- [Kotlin](https://kotlinlang.org/)
- [Jetpack Components](https://developer.android.com/jetpack) - Compose, ViewModel and more
- [Dagger Hilt](https://dagger.dev/hilt/)
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://developer.android.com/kotlin/flow)
- [Retrofit](https://square.github.io/retrofit/)
- [OkHttp3](https://square.github.io/okhttp/)
- [JUnit](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)
- [Fastlane](https://fastlane.tools/)

### Development Setup 💻
To build and run the app, you will need the latest version of Android Studio Giraffe (or newer) installed on your system.

### Weather API 😍
The weather data is provided by the [open-meteo API](https://open-meteo.com/), which does not require an API key. The app retrieves the user's location using the device's GPS and uses the latitude and longitude to fetch the current weather and forecast for the next seven days.

### Contributions 🙌
All contributions to Cloudy are welcome! Please feel free to jump in and pick an issue to work on. Just make sure to comment on the issue before you start working on it.

### Todos 👷
Here are some areas of improvement that I'm currently working on:
- Adopt unit tests to improve app stability
- Improve buildSrc to streamline app building
- Modularization improvements for better code structure
- Adopt Fastlane for for Google Play Store deployment

### LICENSE 📄
```
MIT License

Copyright (c) 2023 s12dar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```