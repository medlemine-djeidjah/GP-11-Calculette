# Calculator Project

A simple yet powerful calculator application.

## Authors : Groupe 11

- DJEIDJAH Mohamed Lemine
- CHEIKH ABDELLAHI Moustapha

---

## 🚀 Getting Started

### Prerequisites

*   Java Development Kit (JDK) 8 or higher
*   Apache Maven

### Compilation

1.  Clone the repository:
    ```sh
    git clone <repository-url>
    ```
2.  Navigate to the project directory:
    ```sh
    cd GP-11-Calculette
    ```
3.  Compile the project using Maven:
    ```sh
    mvn compile
    ```

### Running the application

Once the project is compiled, you can run it using the following Maven command:

```sh
mvn exec:java -Dexec.mainClass="com.g11.app.Main"
```

---

## 🛠️ Technical Details

This project is a desktop calculator application built with Java and Swing. It follows the Model-View-Controller (MVC) design pattern to separate the application's logic, data, and user interface.

### 🏛️ Architecture

The application is divided into three main packages:

*   `com.g11.app.model`: Contains the data and business logic of the calculator.
*   `com.g11.app.view`: Contains the user interface components (GUI).
*   `com.g11.app.controller`: Acts as an intermediary between the Model and the View.

### ✨ Features

*   Basic arithmetic operations (addition, subtraction, multiplication, division)
*   Clear (C) and All Clear (AC) functionality
*   Responsive user interface

### 💻 Technologies

*   **Java**: Core programming language
*   **Swing**: For the graphical user interface (GUI)
*   **Maven**: For project management and dependencies
