package com.example.app_c_truyn.Model;

public class User {
    private int Id;
    private String UserName;
    private String PassWord;
    private String Email;
    private int Role;
    private String ImagePath;

    public User() {}

    public User(String userName, String passWord, String email, int role, String imagePath) {
        UserName = userName;
        PassWord = passWord;
        Email = email;
        Role = role;
        ImagePath = imagePath;
    }

    public User(String userName, String email, String imagePath) {
        UserName = userName;
        Email = email;
        ImagePath = imagePath;
    }

    public User(String nameUser, String email) {
        UserName = nameUser;
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

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}