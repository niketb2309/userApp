package com.web.userlogin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User")
public class User {

    public User(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=6, max=30)
    private String userName;

    @NotNull
    @Size(min=6, max=30)
    private String name;

    @NotNull(message = "Password cannot be null")
    @Size(min=8, max=30)
    private String password;

    @NotNull(message = "Confirm Password cannot be null")
    @Size(min=8, max=30)
    private String confirmPassword;

    @NotNull
    @Size(min=6, max=15)
    private String phoneNumber;

    @NotNull
    private String gender;

    @Email(message = "Email should be valid")
    private String emailId;

    @Size(min = 6, max = 200, message
            = "Address must be between 6 and 200 characters")
    private String address;

    public User(Long id, String userName, String name, String password, String confirmPassword, String emailId, String address, String phoneNumber, String gender) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.emailId = emailId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
