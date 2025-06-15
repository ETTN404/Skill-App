package com.example.skillhub.Model.repo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.skillhub.Model.models.Courses;
import com.example.skillhub.Model.models.MyLearning;
import com.example.skillhub.Model.models.UpdateProfile;
import com.example.skillhub.Model.models.User;
import com.example.skillhub.Model.models.UserLogin;
import com.example.skillhub.Model.models.UserRegister;
import com.example.skillhub.Network.Api.CoursesApi;
import com.example.skillhub.Network.RetrofitDB;
import com.example.skillhub.Utils.SharedPrefs;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    CoursesApi api;

    public Repository(){
        api = RetrofitDB.getInstance().create(CoursesApi.class);
    }
    public LiveData<List<Courses>> getCourseList(){
        MutableLiveData<List<Courses>> CoursesListLiveData = new MutableLiveData<>();

        api.getAllCourses().enqueue(new Callback<List<Courses>>() {
            @Override
            public void onResponse(@NonNull Call<List<Courses>> call, @NonNull Response<List<Courses>> response)
            {
                if (response.isSuccessful()) {
                    CoursesListLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Courses>> call, @NonNull Throwable throwable)
            {
                CoursesListLiveData.setValue(null);
            }
        });
                return CoursesListLiveData;

    }
    public LiveData<Courses> getSingleCourse(int courseId) {
        MutableLiveData<Courses> singleCourse = new MutableLiveData<>();

        api.getSingleCourse(courseId).enqueue(new Callback<Courses>() {
            @Override
            public void onResponse(Call<Courses> call, Response<Courses> response) {
                if (response.isSuccessful()) {
                    singleCourse.setValue(response.body());
                    Log.e("API1111111111111111111111111111111111111111111111111", "Success: Course data loaded"+response.body().description);
                } else {
                    Log.e("API1111111111111111111111111111111111111111111111111", "Response not successful: " + response.message());
                    singleCourse.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Courses> call, Throwable throwable) {
                Log.e("API111111111111111111111111111111111111111111111111111111", "Failure: " + throwable.getMessage());
                singleCourse.setValue(null);
            }
        });

        return singleCourse;
    }

    public LiveData<Integer> registor(String username,String email,String password){
        UserRegister userRegister=new UserRegister(username,email,password);
        MutableLiveData<Integer> status =new MutableLiveData<>();

        Call<Integer> call=api.registerUser(userRegister);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response)
            {

                if (response.isSuccessful() && response.body() != null) {
                   status.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                status.setValue(null);
            }
        });
        return status;
    }
    public LiveData<UserLogin> Login(String username, String password){
        UserLogin userLogin=new UserLogin(username,password,"",false,"",0);
        MutableLiveData<UserLogin> userData =new MutableLiveData<>();

        Call<UserLogin> call=api.login(userLogin);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {

                if (response.isSuccessful() && response.body() != null && response.body().status){
                    userData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable throwable) {
                userData.setValue(null);
            }
        });
        return userData;
    }

    public LiveData<User> userData(int userId)
    {
        MutableLiveData<User> userData =new MutableLiveData<>();

        Call<User> call=api.getUserInfo(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                userData.setValue(null);
            }
        });
        return userData;
    }


    public LiveData<Integer> editProfile(String username,String phone, String email, String dob, String gender, String proffession, String profilePic, Context context)
    {
        UpdateProfile updateProfile=new UpdateProfile(username,phone,email,dob,gender,proffession,profilePic, SharedPrefs.getUserId(context));
        updateProfile.phone=phone;
        updateProfile.email=email;
        updateProfile.date_of_birth=dob;
        updateProfile.gender=gender;
        updateProfile.profession=proffession;
        updateProfile.profile_pic=profilePic;
        MutableLiveData<Integer> status =new MutableLiveData<>();

        Call<Integer> call=api.editProfile(updateProfile);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
               Log.e("uupdate","succuss");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {

                Log.e("uupdate",throwable.getMessage());
            }
        });
        return status;
    }

    public LiveData<List<Courses>> getNewCourse()
    {
        MutableLiveData<List<Courses>> courses =new MutableLiveData<>();
        api.getAllNewCourses(1).enqueue(new Callback<List<Courses>>() {
            @Override
            public void onResponse(Call<List<Courses>> call, Response<List<Courses>> response) {
                courses.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Courses>> call, Throwable throwable) {
                courses.setValue(null);
            }
        });
        return courses;
    }

    public LiveData<List<MyLearning>> getMyCourses(Context context)
    {
        MutableLiveData<List<MyLearning>> mycourse=new MutableLiveData<>();
        api.myCourses(SharedPrefs.getUserId(context)).enqueue(new Callback<List<MyLearning>>() {
            @Override
            public void onResponse(Call<List<MyLearning>> call, Response<List<MyLearning>> response) {
                mycourse.setValue(response.body());
                Log.e("333333333333333333333333333333333333333",response.message());
            }

            @Override
            public void onFailure(Call<List<MyLearning>> call, Throwable throwable) {
                mycourse.setValue(null);
            }
        });
        return mycourse;
    }
}
