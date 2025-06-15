package com.example.skillhub.Utils;

import com.example.skillhub.Network.RetrofitDB;

public class pictureFormat
{
    public static String getCorrect_path(String string)
    {

        String cleanedPath = string.replace("../", "final/SkillHUB/");
        String baseUrl= RetrofitDB.baseUrl;
        String fullImageUrl = baseUrl + cleanedPath;
        return fullImageUrl;
    }
}
