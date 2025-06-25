# Neptune-Bank

Neptune-Bank is a modular banking backend system implemented in Java with Gradle. It is designed for scalability, maintainability, and clear separation of concerns between different banking functionalities. The repository is structured as a monorepo, with each key service (user, employee, OTP) managed in its independent module.

## Table of Contents

- [Project Structure](#project-structure)
- [Modules](#modules)
- [Getting Started](#getting-started)
- [Build and Run](#build-and-run)
- [Development](#development)
- [Contributing](#contributing)
- [License](#license)

---

## Project Structure

```
Neptune-Bank/
├── .idea/                   # IDE settings
├── Neptune Bank.iml         # IntelliJ IDEA module file
├── emplopyee-service/       # Employee service module
├── otp-service/             # OTP (One-Time Password) service module
├── user-service/            # User management service module
```

Each service follows a typical Gradle project layout:
```
<service>/
├── build.gradle
├── settings.gradle
├── gradle/
├── gradlew
├── gradlew.bat
└── src/
    ├── main/
    └── test/
```

---

## Modules

### 1. Employee Service (`emplopyee-service`)
Handles all employee-related operations, such as employee onboarding, management, and authentication.

### 2. OTP Service (`otp-service`)
Manages generation and validation of One-Time Passwords (OTPs) for secure authentication across the platform.

### 3. User Service (`user-service`)
Responsible for user registration, authentication, and profile management.

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Gradle 7.x or higher (Wrapper scripts included)
- Git

### Clone the Repository

```bash
git clone https://github.com/A-Shannidhya/Neptune-Bank.git
cd Neptune-Bank
```

---

## Build and Run

Each module is independently buildable using Gradle.

To build all modules:
```bash
./gradlew build
```

To run tests for all modules:
```bash
./gradlew test
```

To build a specific service (for example, user-service):
```bash
cd user-service
../gradlew build
```

---

## Development

- All services follow a layered architecture, with source code under `src/main` and tests under `src/test`.
- Use the provided `.gitignore` and `.gitattributes` for consistent repository hygiene.
- Modules can be started individually. Instructions for running each service (e.g., as a Spring Boot application) should be placed in the respective module’s README or documentation.

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/YourFeature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/YourFeature`)
5. Create a Pull Request

---

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## Contact

For questions or suggestions, please open an issue or contact [A-Shannidhya](https://github.com/A-Shannidhya).
