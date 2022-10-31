# COSC 310 Project: Office Security System

## Developed by:
- Anisha Parikh
- Jaden Edgar
- Kyra Barnes

# What is our project?

Office Security System is a dual computer project utilising both locally run android studio and processing java applications to emulate a redimentary office building security system. The android studio java application will be emulating a NFC card reader with variable permissions depending on which employee of the office attempts to gain access, and the room in which the employee is attempting to enter. The processing java application will reflect these attempted actions by the employee by showing a top down view of the office building floor layout, and represent granted access into a certain room by showing a dynamic door that will open and close when access is granted.

# How will this software system work?

In user stages, the employee will first select the room in which they are attempting to access using the NFCScanner android studio java application. They will then scan their appropriate NFC card on the back of the emulator. Should their access level by suitable for the room they are trying to enter, they will be prompted with the text "ACCESS GRANTED". Should their access level not be suitablem, they will instead be prompted with "ACESS DENIED".

On an 'Access Granted' event, the appropriate room and user details will be sent over a local servlet to the processing application running locally on a seperate computer, including the room accessed, time, and employee access level. This information will then be used in two ways; it will be used initially to show visually the door opening and then closing after a set time (3s), and the user information previously described will be stored in a database to allow for logs of access attempts to be stored on a database to be determined.

# How to compile and run the code

The project_demo was made using Processing 4.0.1. It should also run with earlier versions. The program will be updated when new versions of Processing are released if necessary. Download the 'project_demo' folder in the current main branch, and using a suitable version of processing open the project folder. To emulate a succesfull 'Access Grnated' scenario as of current, clicking on a door will prompt it to change colour and remain open for 3 seconds.

To implement the NFCScanner, download the latest version of android studio, clone the current main branch repository, and open the NFCScanner application using android studio. You may either run the application in the android sutdio IDE connected to a physical android device, or build an APK through androud studio, install onto a physical android device, and run the app locally. To use the app, first select the room you are attempting to access, scan your NFC card onto the back of the phone, and then press the test button to emulate the result.

NOTE: Should you choose to test the NFCScanner app, NFC DATA FOR EACH CARD IS WITHIN 'strings.xml' INSIDE THE PROJECT FOLDER. 

NOTE: As NFC scanning capabilities are essential to the NFCScanner app, the android studio app is required to be run on a physical device with an NFC scanner enabled. Failure to do so will result in the app immediately closing. 
