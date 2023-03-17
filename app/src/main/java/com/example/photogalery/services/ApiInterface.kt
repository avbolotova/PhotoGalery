package com.example.photogalery.services

import com.example.photogalery.models.Photo
import com.example.photogalery.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface ApiInterface {
    @GET("photos")
    fun getRecentPhotos(
        @Query("page") page: Int,
        @Query("per_page") pageLimit: Int,
        @Query("order_by") order: String
    ) : Call<MutableList<Photo>>

}
