package com.example.currencyconversion.logic.network

import android.util.Base64
import com.example.githubuser.UserApplication
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL = "https://api.github.com/"
    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <T> create(serviceClass: Class<T>): T {
        val credentials = "${UserApplication.username}:${UserApplication.access_key}"
        val basic = "Basic " + Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", basic)
                .header("Accept", "application/json")
                .method(original.method(), original.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        val client = httpClient.build()
        val retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }


    inline fun <reified T> create(): T = create(T::class.java)
}