package com.example.lennox.flexture;

public class UserProfile {
    public String firstName, lastName, regNumber, emailAddress, phoneNumber;

    public UserProfile(){}

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserProfile(String firstName, String lastName, String regNumber, String emailAddress, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.regNumber = regNumber;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public UserProfile(String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

}
