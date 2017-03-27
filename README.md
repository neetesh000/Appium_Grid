# Appium-Grid
Allows you to run automation test on multiple android devices (Real/Emulator) simultaneously.
Just plug your android devices to machine and run tests.

## Requirements:
- Java
- Nodejs <a href="https://nodejs.org/en/blog/release/v6.10.1/" target="_blank">(v6.10.1)</a>
- Android SDK

## Setup
- Install java and set java path in system variable.
- Set ADT bundle path in system variable.
- Install Nodejs and set path in system variable.

## Setting Appium server
- Open command propmt/Terminal.
- Hit command <code>npm install -g appium</code> (It will take 10-15 minutes to complete)
- Verify appium server by command <code>appium</code>
- If you got output as following  then appium server started correctly
- <code> info: Welcome to Appium v1.3.5 (REV a124a15677e26b33db16e81c4b3b34d9c6b8cac9) </code>
- <code> info: Appium REST http interface listener started on 0.0.0.0:4723 </code>
- <code> info: Console LogLevel: debug </code>
- For Mac download [Mac-Command-Setup.zip](https://github.com/sameer49/Appium-Grid-For-Android/blob/master/Mac-Command-Setup.zip?raw=true) unzip it, open terminal go to download location and run command <code>sh Mac-Command-Setup.sh</code> this will set adb and appium execuatbles into /usr/bin. 



