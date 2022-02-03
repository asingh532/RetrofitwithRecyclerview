package com.example.retrofitwithrecyclerview.network

import com.example.retrofitwithrecyclerview.model.ProductModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


const val BASE_URL = "https://fakestoreapi.com/"
interface ApiProvider {

    @GET("products")
    fun getAllProducts():Call<List<ProductModel>>
}

object ApiService{
    val apiProvider:ApiProvider
    init {
        val okHttp = OkHttpClient.Builder()
            .connectTimeout(1,TimeUnit.MINUTES)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()


        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiProvider = retrofit.create(ApiProvider::class.java)
    }
}