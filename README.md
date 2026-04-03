# Selenium Java Automation Framework

This is a Selenium automation framework built using Java and TestNG.  
The goal of this project is to create a clean and scalable structure using Page Object Model and integrate reporting.

## Tech Used

- Java
- Selenium WebDriver
- TestNG
- ExtentReports
- Maven
  
## Project Structure

src/main  
- pages → page classes  
- driver → WebDriver setup and management  
- utils → reusable utilities (reporting, screenshots, waits, listeners, etc)

src/test  
- tests → test cases  

## Features

- Page Object Model (POM) design  
- ExtentReports integration  
- Screenshot capture on test failure  
- Data-driven testing using DataProvider (Excel and array-based inputs)  
- Custom annotation for Author  
- Test grouping using TestNG groups  
- Parallel test execution support  
- Thread-safe WebDriver using ThreadLocal  

## Running the Tests

Using TestNG:  
- Right click on `testng.xml` → Run  

Using Maven (if configured):  

mvn clean test

## Reports

After execution, the report is available at:
extent-output/index.html

Screenshots are automatically captured for failed tests and attached to the report.

## Notes

- Screenshot paths are handled relative to the report for proper preview  
- `@Author` annotation is used for tracking test ownership  
- Test names are generated dynamically during execution  

## CI/CD

- Integrated with Jenkins for test execution
- Source controlled using Git
  
## Author

Taniya Sharma
