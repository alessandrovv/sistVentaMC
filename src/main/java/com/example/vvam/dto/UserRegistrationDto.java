package com.example.vvam.dto;

import com.example.vvam.model.Seller;

public class UserRegistrationDto {
    //@NotEmpty(message = "First name can't be empty!")
    private String firstName;

    //@NotEmpty(message = "Last name can't be empty!")
    private String lastName;

    //@NotEmpty(message = "Email name can't be empty!")
    //@Email(message = "*Please provide a valid Email")
    private String userName;

    //@Length(min = 5, message = "*Your password must have at least 5 characters")
    //@NotEmpty(message = "*Please provide your password")
    private String password;

    private Seller seller;

    public UserRegistrationDto(){
    }

    public UserRegistrationDto(String firstName, String lastName, String userName, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
    public UserRegistrationDto(String firstName, String lastName, String userName, String password, Seller seller) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.seller = seller;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String email) {
        this.userName = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
