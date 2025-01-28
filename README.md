# Shopping App 🛒 
 
A modern Android shopping app built with **Jetpack Compose**, demonstrating integration with dummy APIs, shimmer effects, and tab navigation. Part of my journey to explore advanced Android UI/UX patterns.

## Features ✨

- **Tab Layout**: Switch seamlessly between **Lifestyle** and **Home Essentials** categories
- **Shimmer Effect**: Beautiful loading animation while fetching data
- **Lazy Column Optimization**: Smooth scrolling for large product lists
- **Dummy API Integration**: Using [DummyJSON](https://dummyjson.com/) for realistic product data
- **Modern Architecture**: MVVM pattern with Repository pattern
- **Image Loading**: Efficient image handling with Coil

## Tech Stack 🛠️

- **Language**: Kotlin
- **UI Toolkit**: Jetpack Compose
- **Network**: Retrofit + Kotlin Coroutines
- **Architecture**: MVVM + Clean Architecture
- **Image Loading**: Coil
- **Design System**: Material3

## Screenshots 📸

| Loading State | Lifestyle Tab | Home Essentials Tab | Detail View |
|---------------|---------------|---------------------|-------------|
| ![Screenshot_20250128-162645_Shopping App](https://github.com/user-attachments/assets/eeb2e1be-7de5-4c44-9a20-bee12ff25f4b)| ![Screenshot_20250128-162831_Shopping App](https://github.com/user-attachments/assets/3b0eb275-90fb-4924-ad6b-be0a6d5bf440)| ![Screenshot_20250128-162846_Shopping App](https://github.com/user-attachments/assets/a65b424f-df9b-4ff6-8038-7791d4e9fe34)|![Screenshot_20250128-163051_Shopping App](https://github.com/user-attachments/assets/370f05e6-afc5-4f6b-8925-27a46a9ac8b2)|


## Learning Experience 🎓

This project significantly enhanced my Android development skills:
- **Shimmer Effect Implementation**: Mastered creating attractive loading states
- **Tab Layout Architecture**: Learned to manage multiple data streams with ViewModel
- **Compose Optimization**: Improved Lazy Column performance techniques
- **API Handling**: Gained experience with Retrofit error handling
- **State Management**: Implemented robust loading/error/success states

## Challenges Faced ❌

### 1. Shimmer Effect Implementation
**Initial Struggle:**  
_"I couldn't understand how to coordinate loading states with actual data fetch operations"_

**Breakthrough:**  
```kotlin
// Shimmer composable
@Composable
fun ShimmerEffect() {
    Box(
  modifier = Modifier
    .size(128.dp)
    .background(Color.Blue)
    .shimmer(),
  contentAlignment = Alignment.Center
) {
  Box(
    modifier = Modifier
      .size(64.dp)
      .background(Color.Red)
  )
 }
}

```

**Dependency Used:**
```kotlin
dependencies {
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.1")
}
```
### 2. API 

### Add Dependencies
```kotlin
// build.gradle.kts
dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("io.coil-kt:coil-compose:2.4.0")
}
```
- json 
```bash
https://dummyjson.com/products?limit=10&skip=0
```

**Let's connect!** 🔗  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/shivam-kumar-79a851266?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app)
