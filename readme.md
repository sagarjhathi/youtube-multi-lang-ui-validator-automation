# YouTube Localization UI Validator

![Java](https://img.shields.io/badge/Java-21+-blue.svg)
![Selenium](https://img.shields.io/badge/Selenium-4.x-green.svg)
![TestNG](https://img.shields.io/badge/TestNG-Framework-orange.svg)
![CI](https://img.shields.io/badge/CI-GitHub%20Actions-success.svg)
![Status](https://img.shields.io/badge/Project-Active-brightgreen.svg)

A **Java-based UI automation framework for localization testing**, designed to validate **YouTube’s user interface across multiple languages and regions** using data-driven assertions, automated language detection, and CI/CD execution.

This project focuses on verifying that **UI content, language attributes, and region-specific indicators** update correctly when localization settings change.

---

## 1. What This Project Does

The framework automatically:

- Switches **YouTube language and region settings**
- Reads **expected UI content from Excel files**
- Captures **actual UI text dynamically**
- Asserts **actual vs expected UI behavior**
- Detects the rendered language programmatically
- Verifies the HTML `lang` attribute at the DOM level
- Generates **detailed execution reports with logs and screenshots**
- Runs locally or via **CI/CD pipelines (GitHub Actions)**

This enables reliable **localization testing** for a highly dynamic, real-world web application.

---

## 2. Key Features

- Localization testing across **multiple languages and regions**
- Excel-driven test data for expected UI content
- Automated language detection for rendered UI text
- DOM-level validation of accessibility language attributes
- Page Object Model (POM) based framework design
- Safe, retry-based UI interactions for dynamic pages
- Parallel test execution with thread-safe drivers
- Rich HTML reporting with screenshots and logs
- CI-ready execution with scheduled runs

---

## 3. Tech Stack & Tools

### Core Technologies
- Java
- Selenium WebDriver (4.x)
- TestNG

### Data & Validation
- Excel-driven testing using Apache POI
- Lingua for automated language detection

### Reporting & Observability
- Extent Reports (HTML reports with screenshots)
- Log4j2 (per-test log files)

### CI/CD
- GitHub Actions
- Scheduled and on-demand execution
- Artifact publishing and GitHub Pages deployment

---

## 4. Project Architecture Overview

```text
yt_multi_language_ui_validator/
├── base/
│   ├── BaseTest.java
│   └── BasePage.java
│
├── config/
│   └── ConfigManager.java
│
├── driverManager/
│   └── DriverManager.java
│
├── pages/
│   ├── YtLandingPage.java
│   └── YtInnerPage.java
│
├── safeActions/
│   └── SafeActions.java
│
├── utilities/
│   ├── WaitUtility.java
│   ├── GenericUtility.java
│   └── ScreenshotUtility.java
│
├── lingua/
│   └── LinguaHelper.java
│
├── fileReader/
│   └── ExcelFileReader.java
│
├── fileWriter/
│   └── ExcelFileWriter.java
│
├── reporting/
│   ├── ExtentManager.java
│   └── TestListener.java
│
├── logger/
│   └── log4j2.xml
│
├── tests/
│   └── YtLocalizationTests.java
│
├── resources/
│   ├── config.properties
│   └── testdata/
│       ├── languages.xlsx
│       ├── side_menu_text.xlsx
│       └── filters.xlsx
│
└── .github/
    └── workflows/
        └── ci.yml



## 5. Core Components Explained

### Base Layer
- **BaseTest**  
  Manages WebDriver lifecycle, logging context, and test setup/teardown.
- **BasePage**  
  Provides shared driver and wait configuration for all page objects.

---

### Driver Management
- **DriverManager**
  - Thread-safe WebDriver handling
  - Supports parallel execution
  - Browser configuration via properties
  - CI-friendly setup using WebDriverManager

---

### Page Object Model (POM)
- **YtLandingPage**
  - Encapsulates YouTube landing page interactions
  - Language, region, filters, menus
- **YtInnerPage**
  - Handles inner YouTube page interactions

This keeps **locators and UI actions isolated from test logic**.

---

### Data-Driven Testing
- **FileReader**
  - Reads expected UI values from Excel sheets
- **Excel test data**
  - Languages list
  - Expected UI text per language
  - Region-to-country code mappings

Test logic remains unchanged when expected values change.

---

### Localization Validation
- **LinguaHelper**
  - Detects rendered language from UI text
- **GenericUtility**
  - Maps expected language values
  - Validates HTML `lang` attribute
  - Cross-verifies UI language correctness

Validation happens at:
1. UI content level  
2. Programmatic language detection  
3. DOM-level language attribute  

---

### Safe UI Interactions
- **SafeActions**
  - Retry-based element interactions
  - Explicit waits
  - JavaScript click fallback
  - Page refresh recovery

Designed to handle **dynamic SPA behavior and UI flakiness**.

---

### Reporting & Logging
- **Extent Reports**
  - Test execution summary
  - Screenshots per test
  - Failure context
- **Log4j2**
  - Per-test log files
  - Thread-aware logging
  - CI-compatible log storage

---

## 6. Test Scenarios Covered

- Side menu language validation (expanded and collapsed)
- Settings menu localization
- Search filter localization
- Country code validation based on region
- Language attribute validation (`<html lang="">`)
- Localization checks using automated language detection

---

## 7. CI/CD Pipeline

- Implemented using **GitHub Actions**
- Supports:
  - Manual execution
  - Pull request validation
  - Scheduled (cron) runs
- Generates:
  - HTML reports
  - Screenshots
  - Logs
- Publishes reports to **GitHub Pages**
- Uploads artifacts for later analysis

---

## 8. Configuration Management

- All environment-specific settings are externalized:
  - Browser selection
  - Execution scope (full vs limited)
  - CI vs local execution
  - Wait times and retries

Configuration is managed via **properties files and environment variables**.

---

## 9. Skills Demonstrated

- UI Automation Engineering
- Localization Testing
- Data-Driven Test Design
- Java & Selenium Framework Development
- TestNG-based Execution Strategy
- Resilient Automation for Dynamic UIs
- CI/CD Integration
- Logging, Reporting, and Debugging
- Scalable Test Architecture Design

---

## 10. Project Intent

This project demonstrates **real-world automation engineering practices**, focusing on:

- Maintainability
- Reliability
- Clear separation of concerns
- Practical localization testing challenges
- CI-ready automation design

---

## 11. Demo

A private execution demo video is available and linked externally for resume and portfolio review.
