# User Directory Android App

A modern Android application built for displaying and managing a user directory with a clean, intuitive interface.

## Features

- **User List Display**: Browse through a comprehensive list of users fetched from a REST API
- **User Details**: Tap on any user to view detailed information including contact details, address, website, and company information
- **Add New Users**: Easy-to-use form for adding new users to the directory
- **Material Design**: Modern UI following Google's Material Design guidelines
- **Offline Support**: Fallback to mock data when API is unavailable

## Technical Stack

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI Framework**: Android Views with Material Design Components
- **Networking**: Retrofit + OkHttp
- **Data Binding**: ViewBinding
- **Async Operations**: Kotlin Coroutines
- **Dependency Injection**: Manual dependency injection
- **API**: JSONPlaceholder (with fallback mock data)

## Project Structure

```
app/src/main/java/com/example/userdirectory/
├── MainActivity.kt              # Main screen showing user list
├── UserDetailActivity.kt        # User detail screen
├── AddUserActivity.kt          # Add new user form
├── User.kt                     # Data models
├── UserAdapter.kt              # RecyclerView adapter
├── UserViewModel.kt            # ViewModel for business logic
├── UserRepository.kt           # Data repository
├── ApiService.kt              # Retrofit API interface
└── ApiClient.kt               # Network client configuration
```

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK 24 or higher
- Kotlin 1.8.10 or later

### Installation

1. Clone this repository
2. Open the project in Android Studio
3. Sync the project with Gradle files
4. Run the app on an emulator or physical device

### Building the Project

1. Open terminal in the project root
2. Run: `./gradlew assembleDebug` (Linux/Mac) or `gradlew.bat assembleDebug` (Windows)
3. The APK will be generated in `app/build/outputs/apk/debug/`

## Usage

1. **View Users**: Launch the app to see a list of users
2. **User Details**: Tap on any user card to view detailed information
3. **Add User**: Tap the floating action button (+) to add a new user
4. **Form Validation**: Fill out the required fields (Name, Email, Phone) to add a user

## API Integration

The app uses JSONPlaceholder API (https://jsonplaceholder.typicode.com/) for demo purposes. In case of network issues, the app falls back to local mock data to ensure functionality.

### Endpoints Used:
- `GET /users` - Fetch all users
- `POST /users` - Add new user (simulated)

## Architecture

The app follows MVVM architecture:

- **Model**: Data classes and repository for data management
- **View**: Activities and layouts for UI
- **ViewModel**: Business logic and state management

## Dependencies

- AndroidX libraries
- Material Design Components
- Retrofit for networking
- Gson for JSON parsing
- OkHttp for HTTP logging
- Kotlin Coroutines for async operations

## Future Enhancements

- Search and filter functionality
- User editing capabilities
- Image upload for user profiles
- Local database caching
- Unit and integration tests
- Dark theme support

## License

This project is created for technical assessment purposes.
