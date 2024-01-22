package com.lamz.kumpulandoadoa

import android.app.Application
import com.lamz.core.di.databaseDepedency
import com.lamz.core.di.networkDepedency
import com.lamz.core.di.repositoryDepedency
import com.lamz.kumpulandoadoa.di.useCaseModule
import com.lamz.kumpulandoadoa.di.viewModelModule
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
                    databaseDepedency,
                    networkDepedency,
                    repositoryDepedency,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}