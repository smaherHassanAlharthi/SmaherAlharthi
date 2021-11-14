package com.smaher.smaher_alharthi_beltexam2.api

import com.smaher.smaher_alharthi_beltexam2.model.Universities
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET
    fun getUnivercityInfo(@Url url:String): Call<Universities?>?

//    @Headers("Content-Type: application/json")
//    @PUT("/test/{id}") //for update
//    fun updateUnivercityInfo(@Path("id")id:Int, @Body requestBody: User?): Call<User>
//
//    @Headers("Content-Type: application/json")
//    @DELETE("/test/{id}")
//    fun deleteUnivercityInfo(@Path("id")id:Int): Call<Void>

}