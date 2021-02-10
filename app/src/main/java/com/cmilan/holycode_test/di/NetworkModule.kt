package com.cmilan.holycode_test.di

import com.cmilan.holycode_test.BuildConfig
import com.cmilan.holycode_test.data.network.GeneralApiEndpoint
import com.cmilan.holycode_test.data.network.NetworkInterceptor
import com.cmilan.holycode_test.utils.PrefUtils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(networkInterceptor: NetworkInterceptor): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(220, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)
            .addInterceptor(httpLoggingInterceptor.apply { httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Singleton
    @Provides
    fun providesNetworkInterceptor(prefUtils: PrefUtils): NetworkInterceptor = NetworkInterceptor(prefUtils)

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesGeneralApiInterface(retrofit: Retrofit): GeneralApiEndpoint = retrofit.create(GeneralApiEndpoint::class.java)

}