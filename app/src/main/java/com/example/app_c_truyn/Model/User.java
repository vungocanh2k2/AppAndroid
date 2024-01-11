package com.example.app_c_truyn.Model;

public class User {
    private int Id;
    private String UserName;

    public User(String userName, String passWord, String email, int roles) {
        UserName = userName;
        PassWord = passWord;
        Email = email;
        Roles = roles;
    }

    public User(String userName, String email) {
        UserName = userName;
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getRoles() {
        return Roles;
    }

    public void setRoles(int roles) {
        Roles = roles;
    }

    private String PassWord;
    private String Email;
    private int Roles;
}


