package com.dino.great.apiHandler

import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.apiHandler.ApiFactory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    /* creating a singleton object for retrofit client */
    companion object {
//        val instance: ApiService by lazy {
//            ApiFactory.retrofit(BuildConfig.BASEURL).create(ApiService::class.java)
//        }
    }

//    /**
//     * Post List*/
//    @GET("posts")
//    suspend fun getPosts(): Response<List<Post>?>
//
//    /**
//     * Photo List*/
//    @GET("photos")
//    suspend fun getPhotos(): Response<List<Photo>?>
//
//    /**
//     * Get Comments*/
//    @GET("posts/{id}/comments")
//    suspend fun getComments(@Path("id") postId: Int): Response<List<Comment>?>
}