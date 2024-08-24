package com.krisna.diva.movielist

import android.app.Application
import com.krisna.diva.movielist.core.di.databaseModule
import com.krisna.diva.movielist.core.di.networkModule
import com.krisna.diva.movielist.core.di.repositoryModule
import com.krisna.diva.movielist.di.useCaseModule
import com.krisna.diva.movielist.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}