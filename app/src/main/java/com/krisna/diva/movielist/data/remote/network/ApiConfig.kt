package com.krisna.diva.movielist.data.remote.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("accept", "application/json")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMDU5ZjFkYzUzNTM2NjkzMTExYzAxN2Q1N2I5MmMwMyIsIm5iZiI6MTcyMzk4NTI1My44OTQwMTUsInN1YiI6IjY2YWI0YTAxOGJlYjdjZGQxZDY4NjA4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Hc4_ecZLPFjMYJdeiyUEGm3WQ1iU0CgRDxGat1nC6Fs")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        .build()

    fun getApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}