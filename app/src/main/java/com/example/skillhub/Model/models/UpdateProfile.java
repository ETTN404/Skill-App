package com.example.skillhub.Model.models;

public class UpdateProfile {
    public String username;
    public String phone;
    public String email;
    public String date_of_birth;
    public String gender;
    public String profession;
    public String profile_pic;
    public int id;

    public UpdateProfile(String username, String phone, String email, String date_of_birth,
                         String gender, String profession, String profile_pic, int id) {
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.profession = profession;
        this.profile_pic = profile_pic;
        this.id = id;
    }
}

