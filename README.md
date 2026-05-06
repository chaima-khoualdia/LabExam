# Tunisia Heritage Quest

A Kotlin + Jetpack Compose quiz app for Tunisia's heritage.

## Build

```powershell
.\gradlew.bat :app:assembleDebug
```

## Tests

```powershell
.\gradlew.bat :app:testDebugUnitTest
```

```powershell
.\gradlew.bat :app:connectedAndroidTest
```

## Notes

- UI is built with Material 3 Compose.
- Navigation flow: Splash -> Main Menu -> Category -> Difficulty -> Quiz -> Results.
- Quiz data is seeded in `SeedData.kt`.

