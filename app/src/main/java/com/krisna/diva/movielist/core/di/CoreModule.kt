package com.krisna.diva.movielist.core.di

import com.krisna.diva.movielist.core.data.source.remote.MovieRepository
import com.krisna.diva.movielist.core.data.source.remote.RemoteDataSource
import com.krisna.diva.movielist.core.data.source.remote.network.ApiService
import com.krisna.diva.movielist.core.domain.repository.IMovieRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("accept", "application/json")
                    .header(
                        "Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMDU5ZjFkYzUzNTM2NjkzMTExYzAxN2Q1N2I5MmMwMyIsIm5iZiI6MTcyMzk4NTI1My44OTQwMTUsInN1YiI6IjY2YWI0YTAxOGJlYjdjZGQxZDY4NjA4MiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Hc4_ecZLPFjMYJdeiyUEGm3WQ1iU0CgRDxGat1nC6Fs"
                    )
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<IMovieRepository> { MovieRepository(get()) }
}
