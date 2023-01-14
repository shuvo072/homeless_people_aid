package com.example.homelesspeopleaid;

public class User {
    public String name, signupEmail,  phone, address;

    public User(){

    }

    public User(String fullName, String signupEmail, String phone, String address){
        this.name = fullName;
        this.signupEmail = signupEmail;
        this.phone = phone;
        this.address = address;
    }
}
