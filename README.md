# Tunisia Heritage Quiz App 🇹🇳

An Android quiz application built with **Jetpack Compose** and **Material3**, themed around Tunisia’s cultural and historical heritage.  
Players select categories, choose difficulty, answer timed questions, and receive feedback and scores.

---

## 📖 Project Overview
This app is designed as an educational quiz game highlighting monuments and heritage sites of Tunisia.  
It provides:
- A **splash screen** and main menu entry point.
- **Category selection** (e.g., Roman Heritage).
- **Difficulty settings** with timer, sound, and haptic options.
- **Quiz gameplay** with images, facts, and multiple-choice answers.
- **Feedback panel** showing correct answers and extra facts.
- **Results screen** with retry and back-to-menu options.
- Responsive layouts for phones and tablets.

---

## 🏛️ Architecture Explained
The app follows **MVVM (Model–View–ViewModel)** with Jetpack libraries:

- **UI Layer (Compose)**  
  - `SplashScreen`, `MainMenuScreen`, `CategorySelectionScreen`, `DifficultySettingsScreen`, `QuizScreen`, `ResultsScreen`  
  - Uses `NavGraph.kt` for navigation between routes.

- **ViewModel Layer**  
  - `QuizViewModel` manages quiz state using `StateFlow`.  
  - Handles category selection, difficulty, scoring, feedback, and navigation triggers.

- **Data Layer**  
  - `Question` entity stored locally (Room database).  
  - Provides quiz questions, facts, and options.

- **Utilities**  
  - **Coil** for image loading.  
  - **WindowSizeClass** for responsive layouts.

---

## ⚙️ Setup Instructions
1. **Clone the repository**
   ```bash
   git clone https://github.com/chaima-khoualdia/LabExam.git
   cd LabExam
