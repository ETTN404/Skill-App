package com.example.skillhub.Network.Api;

import com.example.skillhub.Model.models.Courses;
import com.example.skillhub.Model.models.MyLearning;
import com.example.skillhub.Model.models.UpdateProfile;
import com.example.skillhub.Model.models.User;
import com.example.skillhub.Model.models.UserLogin;
import com.example.skillhub.Model.models.UserRegister;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CoursesApi
{
    @GET("final/SkillHUB/Server/mob_app_apies.php")
    public Call<List<Courses>> getAllCourses();
    @GET("final/SkillHUB/Server/mob_app_apies.php")
    public Call<List<Courses>> getAllNewCourses(@Query("new") int a);
    @GET("final/SkillHUB/Server/mob_app_apies.php")
    public Call<List<MyLearning>> myCourses(@Query("mylearning") int a);

    @GET("final/SkillHUB/Server/mob_app_apies.php")
    public Call<List<Courses>> getCoursesbyCatagory();

    @GET("final/SkillHUB/Server/mob_app_apies.php")
    public Call<Courses> getSingleCourse(@Query("courseId") int courseId);
    @GET("final/SkillHUB/Server/mob_app_apies.php")
    public Call<User> getUserInfo(@Query("userId") int userId);
    @POST("final/SkillHUB/Server/mob_app_apies.php")
    Call<Integer> registerUser(@Body UserRegister request);
    @PUT("final/SkillHUB/Server/mob_app_apies.php")
    Call<Integer> editProfile(@Body UpdateProfile request);
    @POST("final/SkillHUB/Server/mob_app_apies.php")
    Call<UserLogin> login(@Body UserLogin request);


}
