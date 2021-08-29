# Job Management System

## Description
A simple Job Management Service to handle the
execution of multiple types of Jobs.

### Versions
- Java 11
- Spring boot 2.4.4

### How to test the email service job locally
- Set the recipient email address on line 26 of "EmailSendingJob" class.
- Set the sender's email address (MUST be a gmail account) and password in environment variable using the variable names: "MY_GMAIL" and "PASSWORD".
- Go to "https://myaccount.google.com/security" and turn on "Less secure app access".

### Further Improvements
- Handle TimeoutException for Job Services.

### Total Time Spent to Develop this Application (Tests Inclusive)
- 6 Hours