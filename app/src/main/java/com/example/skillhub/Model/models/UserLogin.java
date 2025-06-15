package com.example.skillhub.Model.models;

public class UserLogin
{
    public String Username;
    public String Password;
    public  String Profile;
    public boolean status;
    public String role;
    public int id;
    public UserLogin(String Username, String Password, String Profile, boolean status, String role, int id) {
        this.Username = Username;
        this.Password = Password;
        this.Profile = Profile;
        this.status = status;
        this.role = role;
        this.id = id;
    }
}
