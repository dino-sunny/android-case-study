package com.target.targetcasestudy.apiHandler

import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    /* creating a singleton object for retrofit client */
    companion object {
        val instance: ApiService by lazy {
            ApiFactory.retrofit(BuildConfig.BASEURL).create(ApiService::class.java)
        }
    }

    /**
     * Get deals*/
    @GET("deals")
    suspend fun getDeals(): Response<Products>

    /**
     * Get Deal*/
//    @GET("deals/{id}")
//    suspend fun getDeal(@Path("id") dealId: Int): Response<List<DealItem>?>
}