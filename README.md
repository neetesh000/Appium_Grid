# Appium-Grid
Allows you to run automation test on multiple android devices (Real/Emulator) simultaneously.
Just plug your android devices to machine and run tests.

## Requirements:
- Java
- Nodejs <a href="https://nodejs.org/en/blog/release/v6.10.1/" target="_blank">(v6.10.1)</a>
- Android SDK
- Appium
- Apache Maven
- (Optional) Tesseract setup for OCR: click on the link for the instructions to setup on <a href="http://emop.tamu.edu/Installing-Tesseract-Mac" target="_blank">Mac</a> or on the <a href="http://emop.tamu.edu/Installing-Tesseract-Windows8" target="_blank">Windows</a>

## Setup
- Install java and set java path in system variable.
- Install Maven and set its path in system variable.
- Set ADT bundle path in system variable.
- Install Nodejs and set path in system variable.
- Open the project in eclipse and configure the project to maven project

## Setting Appium server
- Open command prompt/Terminal.
- Hit command <code>npm install -g appium</code> (It will take 10-15 minutes to complete)
- Verify appium server by command <code>appium</code>
- If you got output as following  then appium server started correctly
- [Appium] Welcome to Appium v1.6.3
- [Appium] Appium REST http interface listener started on 0.0.0.0:4723

## Running the tests
- Start the emulators or connect the devices with the system
- Open command prompt/Terminal. 
- Hit command 'adb devices'
- Verify all the devices are listed by adb
- Update the testng.xml file with the deviceIds
- Run the tests either of the following ways
1. From eclipse right click on the 'testng.xml' file and select 'Run As > TestNg Suite' option
2. In command promt hit command 'mvn test'
