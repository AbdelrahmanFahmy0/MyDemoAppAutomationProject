# 📱 MyDemoApp Automation Project

<div align="center">

![Java](https://img.shields.io/badge/Java-23-red?style=flat-square&logo=java)
![TestNG](https://img.shields.io/badge/TestNG-7.12.0-green?style=flat-square)
![Appium](https://img.shields.io/badge/Appium-10.1.1-blue?style=flat-square&logo=appium)
![Allure](https://img.shields.io/badge/Allure-2.33.0-orange?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-3.6+-yellow?style=flat-square&logo=apache-maven)

</div>

---

## 📋 Overview

**MyDemoApp Automation Project** is a comprehensive, production-ready test automation framework built with **Java**, **Appium**, and **TestNG**. This project demonstrates best practices in mobile test automation, including page object models, custom utilities, advanced reporting, and cross-platform testing capabilities.

The framework is designed to automate end-to-end workflows for the MyDemoApp mobile application, supporting both **Android** and **iOS** platforms with native, hybrid, and web application testing capabilities.

### Key Highlights
- ✅ **Cross-Platform Testing**: Android (UiAutomator2) & iOS (XCUITest) support
- ✅ **Hybrid Capabilities**: Native, Hybrid, and Web application support
- ✅ **Enterprise Reporting**: Allure test reports with detailed analytics
- ✅ **Scalable Architecture**: Page Object Model (POM) design pattern
- ✅ **Advanced Logging**: Log4j2 integration for comprehensive logging
- ✅ **CI/CD Ready**: Maven-based configuration for pipeline integration

---

## 🛠️ Technology Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| **Java** | 23 | Programming Language |
| **TestNG** | 7.12.0 | Test Framework |
| **Appium** | 10.1.1 | Mobile Automation |
| **Selenium** | (via Appium) | Web Automation |
| **Maven** | 3.6+ | Build & Dependency Management |
| **Allure** | 2.33.0 | Test Reporting |
| **Log4j2** | 2.26.0 | Logging Framework |
| **AspectJ** | 1.9.25.1 | AOP for Allure Integration |
| **JSON Path** | 3.0.0 | JSON Data Processing |
| **JSoup** | 1.22.2 | HTML Parsing |
| **Commons IO** | 2.21.0 | Utility Library |

---

## 📋 Prerequisites

Before you begin, ensure you have the following installed on your system:

### System Requirements
- **Java Development Kit (JDK)** 23 or higher
- **Maven** 3.6.0 or higher
- **Node.js & npm** (for Appium)
- **Appium Server** 2.0 or higher

### For Android Testing
- **Android SDK** with API Level 23+
- **Android Device** or **Android Emulator** (Pixel 9 recommended)
- **ADB (Android Debug Bridge)** configured and added to PATH
- **MyDemoApp.apk** test application

### For iOS Testing
- **Xcode** 15.0 or higher (macOS only)
- **iOS Device** or **iOS Simulator** (iPhone 16 recommended with iOS 17.0)
- **WebDriverAgent** pre-installed on device
- **MyDemoApp.app** test application

### Additional Tools
- **Git** for version control
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code with Java extensions)

---

## ⚙️ Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/your-repo/MyDemoAppAutomationProject.git
cd MyDemoAppAutomationProject
```

### 2. Verify Java Installation
```bash
java -version
# Output should show Java 23+
```

### 3. Verify Maven Installation
```bash
mvn -version
# Output should show Maven 3.6.0+
```

### 4. Install Project Dependencies
```bash
mvn clean install
```

### 5. Install Appium Server (if not already installed)
```bash
npm install -g appium@latest
appium driver install uiautomator2
appium driver install xcuitest
appium driver install chromium
```

### 6. Start Appium Server
```bash
# Start Appium on default port 4723
appium

# Or specify a custom port
appium --port 4723
```

### 7. Prepare Test Devices/Emulators
```bash
# For Android - Start emulator
emulator -avd Pixel_9

# Verify ADB connection
adb devices

# For iOS - Start simulator
open /Applications/Xcode.app/Contents/Developer/Applications/Simulator.app
```

---

## Configuration

### Framework Configuration File
Located at: `src/main/resources/framework.properties`

#### Platform Configuration
```properties
# Supported: android | ios
platform=android

# Supported: native | hybrid | web
app.type=native
app.installed=true
```

#### Appium Server Configuration
```properties
appium.server.url=http://127.0.0.1
appium.server.port=4723
```

#### Android Configuration
```properties
android.device.name=Pixel 9
android.app.package=com.saucelabs.mydemoapp.rn
android.app.activity=com.saucelabs.mydemoapp.rn.MainActivity
android.app.path=./src/test/resources/MyDemoApp.apk
android.automation.name=UiAutomator2
android.no.reset=false
```

#### iOS Configuration
```properties
ios.device.name=iPhone 16
ios.platform.version=17.0
ios.app.path=./src/test/resources/apps/app.app
ios.bundle.id=com.example.app
ios.automation.name=XCUITest
ios.no.reset=true
ios.wda.local.port=8100
```

#### Browser Configuration
```properties
browser.name=Chrome
browser.url=https://www.google.com
chrome.driver.path=./src/test/resources/chromedriver.exe
```

### Logging Configuration
Located at: `src/main/resources/log4j2.properties`

Logs are written to: `test-output/Logs/logs.log`

**Override Framework Properties via Command Line:**
```bash
mvn test -Dplatform=ios -Dapp.type=native
```

---

## 🚀 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=LoginTest
```

### Run Specific Test Method
```bash
mvn test -Dtest=LoginTest#testValidLogin
```

### Run Tests with Custom Properties
```bash
mvn test -Dplatform=android -Dapp.type=native
```

### Run Tests in Parallel
```bash
# Update pom.xml or use threadCount
mvn test -DthreadCount=4
```

### Generate Allure Report
```bash
# Generate report after test execution
mvn allure:report

# Open the report
mvn allure:serve
```

### Skip Tests
```bash
mvn clean install -DskipTests
```

---

## 📁 Project Structure

```
MyDemoAppAutomationProject/
│
├── src/
│   ├── main/
│   │   ├── java/com/mydemo/
│   │   │   ├── drivers/              # WebDriver initialization & management
│   │   │   ├── listeners/            # TestNG listeners & Allure integration
│   │   │   ├── pages/                # Page Object Model classes
│   │   │   ├── server/               # Appium server management
│   │   │   ├── utils/                # Helper utilities (wait strategies, etc.)
│   │   │   ├── validations/          # Custom assertions & validations
│   │   │   └── media/                # Media/screenshot utilities
│   │   │
│   │   └── resources/
│   │       ├── framework.properties       # Framework configuration
│   │       ├── log4j2.properties         # Logging configuration
│   │       ├── waits.properties          # Wait timeout configurations
│   │       ├── allure.properties         # Allure report settings
│   │       └── META-INF/                 # Metadata configuration
│   │
│   └── test/
│       ├── java/com/mydemo/
│       │   ├── BaseTest.java         # Base test class with setup/teardown
│       │   └── Tests/                # Test classes
│       │       ├── AddToCartTest.java
│       │       ├── E2ETest.java
│       │       ├── LoginTest.java
│       │       ├── LogoutTest.java
│       │       ├── PaymentTest.java
│       │       ├── ShippingTest.java
│       │       ├── SortProductsTest.java
│       │       └── ViewProductTest.java
│       │
│       └── resources/
│           ├── testng.xml           # TestNG suite configuration
│           ├── MyDemoApp.apk        # Android test app
│           └── apps/                # iOS test apps
│
├── test-output/
│   ├── target/                  # Maven build output
│   ├── allure-results/          # Raw Allure test results
│   ├── full-report/             # Generated Allure report (HTML)
│   ├── Logs/                    # Test execution logs
│   ├── reports/                 # HTML test reports
│   └── screenshots/             # Test execution screenshots
│
├── pom.xml                      # Maven Project Object Model
└── README.md                    # Project documentation
```

---

## 📊 Test Reports

### Allure Report
After test execution, comprehensive reports are generated:

**Location:** `test-output/full-report/index.html`
**Raw Results:** `test-output/allure-results/`

**Features:**
- 📈 Test execution statistics
- 📊 Pass/Fail breakdown
- 🔍 Detailed test steps and logs
- 📸 Screenshots and video attachments
- ⏱️ Test duration analytics
- 🏷️ Test categorization and filtering

**Generate and View Report:**
```bash
# Generate the report
mvn allure:report

# Start a local server and view the report
mvn allure:serve
```

### Test Logs
**Location:** `test-output/Logs/logs.log`

- **Format**: `[LEVEL] - [TIMESTAMP] [THREAD] [CLASS] - MESSAGE`
- **Levels**: DEBUG, INFO, WARN, ERROR, FATAL
- **Console Output**: Colored logs for better readability

### Screenshots
**Location:** `test-output/screenshots/`

Screenshots are automatically captured for:
- ✅ Passed tests
- ❌ Failed tests
- 🔄 Each test step (configurable)

---

## 🏗️ Project Architecture

### Design Patterns

#### 1. Page Object Model (POM)
```java
// pages/ directory contains page classes
LoginPage.java
HomePage.java
ProductPage.java
CheckoutPage.java
```

Each page class encapsulates:
- Element locators
- User interactions
- Business logic

#### 2. BaseTest Template
```java
// BaseTest provides:
- Driver initialization
- Browser/App launch
- Implicit/Explicit waits
- Test setup and teardown
- Screenshot on failure
```

#### 3. Driver Management
```java
// drivers/ package handles:
- WebDriver initialization
- Device capabilities setup
- Wait strategies
- Driver cleanup
```

#### 4. Listener Pattern
```java
// listeners/ package contains:
- TestNG listeners
- Allure report integration
- Failure handling
- Logging integration
```

### Architecture Flow
```
┌─────────────────┐
│   Test Classes  │
│  (Tests/*.java) │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Base Test      │
│  (Setup/Config) │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Page Objects   │
│  (POM Pattern)  │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Driver Manager │
│  (Appium/Web)   │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│  Device/Browser │
└─────────────────┘
```

---

## 🎁 Framework Features

### 1. **Smart Wait Strategies**
- Implicit waits (configurable via `waits.properties`)
- Explicit waits with custom conditions
- Fluent wait implementations
- Element visibility checks

### 2. **Cross-Platform Support**
- Android UiAutomator2 automation
- iOS XCUITest automation
- Web application testing via Selenium
- Hybrid app support

### 3. **Advanced Logging**
- Log4j2 integration with color-coded output
- File and console logging
- Configurable log levels
- Thread-safe logging

### 4. **Comprehensive Reporting**
- Allure Test Reports with rich analytics
- TestNG HTML reports
- Custom failure reports
- Step-by-step execution logs
- Screenshot attachments

### 5. **Custom Validations**
- Custom assertions (validations/ package)
- Soft assertions for non-critical checks
- Failure messages with context
- Screenshot capture on validation failure

### 6. **Media Management**
- Automatic screenshot capture
- Screenshot on test failure
- Screenshot organization by test result
- Media file management utilities

### 7. **Error Handling**
- Custom exception classes
- Graceful failure handling
- Retry mechanisms
- Detailed error logs

### 8. **Configuration Management**
- Externalized properties files
- Environment-specific configurations
- Command-line property overrides
- Dynamic configuration loading

---

## 🧪 Test Scenarios Covered

### 1. **Login Test** (`LoginTest.java`)
- ✅ Valid credentials login
- ✅ Invalid credentials handling
- ✅ Session management
- ✅ Login error validation

### 2. **Logout Test** (`LogoutTest.java`)
- ✅ Successful logout
- ✅ Session termination
- ✅ Return to login screen
- ✅ Logout confirmation

### 3. **Product Viewing** (`ViewProductTest.java`)
- ✅ Product list display
- ✅ Product details view
- ✅ Product image loading
- ✅ Product information accuracy

### 4. **Add to Cart** (`AddToCartTest.java`)
- ✅ Add single product to cart
- ✅ Add multiple products to cart
- ✅ Quantity updates
- ✅ Cart count verification

### 5. **Product Sorting** (`SortProductsTest.java`)
- ✅ Sort by price (ascending)
- ✅ Sort by price (descending)
- ✅ Sort by rating
- ✅ Sort by name

### 6. **Shipping** (`ShippingTest.java`)
- ✅ Shipping address entry
- ✅ Address validation
- ✅ Shipping method selection
- ✅ Shipping cost calculation

### 7. **Payment** (`PaymentTest.java`)
- ✅ Payment method selection
- ✅ Card information entry
- ✅ Payment validation
- ✅ Transaction confirmation

### 8. **End-to-End Workflows** (`E2ETest.java`)
- ✅ Complete purchase flow
- ✅ Login → Browse → Cart → Checkout → Payment
- ✅ Order confirmation
- ✅ Email receipt

---

## 📧 Support & Contact

### Issues & Bug Reports
- 📝 Create an issue on GitHub with detailed steps to reproduce
- 📸 Include screenshots/logs from `test-output/`
- 📋 Specify platform (Android/iOS) and device details

### Contributing
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Documentation
- 📖 For Appium: [Appium Documentation](http://appium.io/)
- 📖 For TestNG: [TestNG Documentation](https://testng.org/)
- 📖 For Allure: [Allure Framework](https://docs.qameta.io/allure/)

### Project Team
- **Project Owner**: MyDemo QA Team
- **Maintainers**: Automation Engineers
- **Contributors**: Quality Assurance Team

---

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 🙏 Acknowledgments

- Appium community for excellent mobile automation tools
- TestNG for robust testing framework
- Allure for comprehensive reporting solution
- Maven community for build management

---

## 🔗 Useful Links

- [Appium Documentation](http://appium.io/docs/en/about-appium/intro/)
- [TestNG User Guide](https://testng.org/doc/documentation-main.html)
- [Allure Report Documentation](https://docs.qameta.io/allure/)
- [Log4j2 Documentation](https://logging.apache.org/log4j/2.x/)
- [Maven Getting Started](https://maven.apache.org/guides/getting-started/)

---

<div align="center">

**Built with ❤️ for Quality Assurance**

Last Updated: May 18, 2026 | Version: 1.0-SNAPSHOT

</div>

