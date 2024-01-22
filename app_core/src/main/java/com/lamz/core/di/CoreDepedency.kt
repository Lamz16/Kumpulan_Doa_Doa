package com.lamz.core.di

import androidx.room.Room
import com.lamz.core.BuildConfig
import com.lamz.core.data.KumpulanDoaRepository
import com.lamz.core.data.resource.local.LocalDataSource
import com.lamz.core.data.resource.local.room.DoaDatabase
import com.lamz.core.data.resource.remote.RemoteDataSource
import com.lamz.core.data.resource.remote.network.ApiService
import com.lamz.core.domain.repository.IKumpulanDoaRepository
import com.lamz.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseDepedency = module {
    factory { get<DoaDatabase>().doaDao() }
    single {
        val passPhrase: ByteArray = SQLiteDatabase.getBytes("doa-doa".toCharArray())
        val factory = SupportFactory(passPhrase)
        Room.databaseBuilder(
            androidContext(),
            DoaDatabase::class.java, "Doa.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkDepedency = module {
    single {
        val hostname = "doa-doa-api-ahmadramadhan.fly.dev"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/2+mDaFbC6VrlvuHalQ11nxTDqrhkwyN0aEMN6ZEMlac=")
            .add(hostname, "sha256/jQJTbIh0grw0/1TkHSumWb+Fs0Ggogr621gT3PvPKG0=")
            .add(hostname, "sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(3000, TimeUnit.SECONDS)
            .readTimeout(3000, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}


val repositoryDepedency = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IKumpulanDoaRepository> {
        KumpulanDoaRepository(
            get(),
            get(),
            get()
        )
    }
}