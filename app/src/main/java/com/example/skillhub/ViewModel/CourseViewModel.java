package com.example.skillhub.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.skillhub.Model.models.Courses;
import com.example.skillhub.Model.models.MyLearning;
import com.example.skillhub.Model.models.User;
import com.example.skillhub.Model.models.UserLogin;
import com.example.skillhub.Model.repo.Repository;
import com.example.skillhub.Utils.SharedPrefs;

import java.util.List;


public class CourseViewModel extends ViewModel {
    private final Repository repositoryR1 = new Repository();
    private LiveData<List<Courses>> coursesList;
    private LiveData<List<MyLearning>> mylearning;
    private boolean is_mylearning=false;
    private MutableLiveData<Courses> singleCourse;

    public CourseViewModel() {
        coursesList = repositoryR1.getCourseList();
        singleCourse = new MutableLiveData<>();
    }
    public LiveData<List<Courses>> getCoursesList() {
        return coursesList;
    }
    public LiveData<List<Courses>> getCewCoursesList() {
        return repositoryR1.getNewCourse();
    }
    public LiveData<List<MyLearning>> getMyCourse(Context context)
    {
        if(!is_mylearning)
        {
            mylearning= repositoryR1.getMyCourses(context);
            is_mylearning=true;
        }
        return mylearning;
    }
    public LiveData<Courses> getSingleCourse() {
        return singleCourse;
    }
    public void fetchSingleCourse(int id) {
        repositoryR1.getSingleCourse(id).observeForever(course -> {
            singleCourse.setValue(course);
        });
    }
    public LiveData<Integer> registerUser(String username,String email,String password){
            return repositoryR1.registor(username, email, password);
    }
    public LiveData<Integer> editProfile(String username,String phone,String email,String dob,String gender,String Proffession,String profilePic,Context context){
            return repositoryR1.editProfile(username,phone,email,dob,gender,Proffession,profilePic,context);

    }
    public LiveData<UserLogin> Login(String username ,String password){
            return repositoryR1.Login(username, password);
    }
    public LiveData<User> userData(Context context){
            return repositoryR1.userData(SharedPrefs.getUserId(context));
    }

}


