# RPiSmartHome
RPiSmartHome

A small home automation project based on the Raspberry Pi microcomputer. It uses Java Spring as the backbone of the controll applications. To build it on your machine you need the following:
- properly configured PostgreSQL (or any database if you are willing to change the app config a bit),
- Installed pi4j library oon your Pi (follow instructions on project page)
- Maven
- Java 1.8 (could not go higher because pi4j)

Version 0.01 
- Working Api for temperature read history
- SI7021 sensor compatibility
- Basic I2C protocol communication
