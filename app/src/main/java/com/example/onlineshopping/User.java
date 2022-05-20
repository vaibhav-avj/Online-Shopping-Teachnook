package com.example.onlineshopping;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullname="null", number="xxxxxxxxxx", address="@@@";

    public User() {

    }

    public User(int id, String username,String email, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(int id, String username,  String email, String password, String fullname, String number, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.number = number;
        this.address = address;

    }
//    public User(String fullname, String number, String address)
//    {
//        this.fullname = fullname;
//        this.number = number;
//        this.address = address;
//    }

    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

