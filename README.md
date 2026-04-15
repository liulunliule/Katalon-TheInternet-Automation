# Katalon Automation Practice

## 📌 Overview

This project demonstrates automation testing using Katalon Studio with:

* UI Testing (Web automation)
* API Testing (RESTful APIs)
* Data-Driven Testing (Excel)
* Custom Keywords (Reusable functions)

---

## 🧪 Test Coverage

### 🔹 UI Testing

* Login with valid credentials → Success
* Login with empty password → Error
* Login with wrong username → Error
* Login with empty fields → Error
* Checkbox interactions
* Dropdown selection

---

### 🔹 API Testing

Located in: `Test Cases/API`

* **API_TC01_GetUser_1**
  → Verify API returns correct data for user ID = 1

* **API_TC02_GetUser_Validation**
  → Validate response structure, data types, and required fields

* **API_TC03_GetAllUsers**
  → Fetch all users and detect invalid data entries

---

## 🛠 Technologies

* Katalon Studio
* Groovy
* Selenium (UI Testing)
* REST API (API Testing)

---

## 📂 Project Structure

* **Test Cases**

  * UI test cases
  * `API/` → API test cases
* **Object Repository** → UI elements
* **Data Files** → Excel test data
* **Keywords** → Custom reusable functions
---

## 📂 Test Suites

* **UI_Regression_Suite** → Contains all UI test cases
* **API_Regression_Suite** → Contains all API test cases
* **Regression_Suite** → Full regression (UI + API)

---

## 🚀 How to Run

### Run UI Tests

1. Go to **Test Suites**
2. Select: `UI_Regression_Suite`
3. Click **Run**

### Run API Tests

1. Go to **Test Suites**
2. Select: `API_Regression_Suite`
3. Click **Run**

### Run Full Regression (UI + API)

1. Go to **Test Suites**
2. Select: `Regression_Suite`
3. Click **Run**

---

## 📊 Test Strategy

* **Smoke Testing**: Validate critical login scenarios
* **Regression Testing**: Run full `Regression_Suite`
* **API Testing**: Validate data correctness and structure

---

## 👩‍💻 Author

Cao Thuy Lieu
