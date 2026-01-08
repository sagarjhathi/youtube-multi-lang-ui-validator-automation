# YouTube Localization UI Validator

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

This enables reliable **localization testing and regression validation** for a highly dynamic, real-world web application.

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
- Excel-driven testing (Apache POI)
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

