package com.krisna.diva.movielist.core.di

import androidx.room.Room
import com.krisna.diva.movielist.BuildConfig
import com.krisna.diva.movielist.core.data.source.local.LocalDataSource
import com.krisna.diva.movielist.core.data.source.local.room.MovieDatabase
import com.krisna.diva.movielist.core.data.source.remote.MovieRepository
import com.krisna.diva.movielist.core.data.source.remote.RemoteDataSource
import com.krisna.diva.movielist.core.data.source.remote.network.ApiService
import com.krisna.diva.movielist.core.domain.repository.IMovieRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "favorite_movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}


val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("accept", "application/json")
                    .header(
                        "Authorization",
                        BuildConfig.API_KEY
                    )
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource(get()) }
    single<IMovieRepository> { MovieRepository(get(), get()) }
}
