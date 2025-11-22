package com.example.vespaair.api;

import com.example.vespaair.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("posts/{id}")
    Call<User> getPostById(@Path("id") int id);
    @POST("users")
    Call<User> addUser(@Body User user);
    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);

}
