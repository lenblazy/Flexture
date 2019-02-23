package com.example.lennox.flexture;

public class UserProfile {
<<<<<<< HEAD
    private String firstName, lastName, regNumber, emailAddress, phoneNumber;
=======
    public String firstName, lastName, regNumber, emailAddress, phoneNumber;
>>>>>>> parent of 4283dea... Remove the top UI from the flexture page and tried to retrieve data from firebase and paste on profile page. but the details get lost when i try to do it

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
