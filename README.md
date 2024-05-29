# Homie app - a roommate project

This app is a roommate and household management app that helps you and your roommates create a more manageable, stable and fun living space with and for each other.


To install this project, follow these steps:

1. Clone the repository:
    ```sh
    git clone https://github.com/username/RoommateProject.git
    ```
2. Navigate to the project directory:
    ```sh
    cd RoommateProject
    ```
3. Install the dependencies:
    ```sh
    npm install
    ```

## Getting started:
To get started with running the Homie app.
After cloning the repository, navigated to the project dictionary and ensuring the required dependencies are installed.
Android Studios will automatically prompt you to sync the Gradle files. If not, manually trigger a sync by selecting `File > Sync Project with Gradle Files`.

To configure the emulator, go to `AVD Manager` in Android Studio (in the top right corner of the IDE or under Tools > AVD Manager).
Here you can create a new virtual device or use an existing one. Ensure it has the necessary system images installed.

You are now ready to run the app on your emulator. Remember to choose a preferred emulator from the dropdown menu in the device.
Click the green Run button or use the shortcut Shift + F10.
The app will build and launch on the emulator.

To interact with the app, you simply just have to use your mouse, which simulates touch, and the keyboard to type in text.

## Project structure
Authentication and Firestore:
All services related to login and authentication via Firebase Firestore are contained within the service package.
This ensures a clean separation of concerns and makes it easy to manage and update authentication logic.

Components and State Management:
The application is divided into different page folders, each containing the necessary composable and a corresponding ViewModel for state management.
This structure promotes maintainability and usability of code.

Composable: are located within the respective page folders, they define the UI components of the app.
ViewModels: each page has a ViewModel that handles the state and logic, ensuring a clear separation between UI and logic.

## How to use the app
In order to use the app, simply create an account after you clone the repository, and register you and your roommates to your home.
From here all the functionality is on the front page.

    1. Register a user

## Contribution
If you want to contribute to our project, simply fork the repository within GitHub or your desired terminal.

## Authors and acknowledgements
Created by Birk Lauritzen, Natazja Dahl, Sofie Thorlund and Viktor Bach.

Contact us @
birklauritzen@gmail.com - natazjadahl@gmail.com - sofieamaliethorlund@gmail.com - vmb0107@gmail.com
