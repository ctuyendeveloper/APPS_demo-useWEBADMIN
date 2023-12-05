package com.example.php_asm1.helpers;

import com.example.php_asm1.models.ForgotPassword.ForgotPasswordRequest;
import com.example.php_asm1.models.ForgotPassword.ForgotPasswordResponse;
import com.example.php_asm1.models.News.NewsDetailModelResponse;
import com.example.php_asm1.models.News.NewsModelResponse;
import com.example.php_asm1.models.Register.RegisterRequest;
import com.example.php_asm1.models.Register.RegisterResponse;
import com.example.php_asm1.models.User.UserLoginRequest;
import com.example.php_asm1.models.User.UserLoginResponse;
import com.example.php_asm1.models.User.UserUpdateRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface IRetrofitRouter {
    @POST("/login.php")
    Call<UserLoginResponse> login(@Body UserLoginRequest user);
    @GET("/get-news.php")
    Call<List<NewsModelResponse>> getNews();

    @GET("/get-news-trending.php")
    Call<NewsModelResponse> getNewsTrengding();
    @POST("/forgot-password.php")
    Call<ForgotPasswordResponse> forgot(@Body ForgotPasswordRequest forgot1);
    @POST("/register.php")
    Call<RegisterResponse> register(@Body RegisterRequest register1);

    @GET("/get-news-detail.php")
    Call<List<NewsDetailModelResponse>> getNewDetails(@Query("id") int id);

    @GET("update-read-news.php")
    Call<Void> updateSeenValue(@Query("id") int postId);
}
