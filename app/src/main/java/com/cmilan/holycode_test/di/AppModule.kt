package com.cmilan.holycode_test.di

import android.content.Context
import android.content.SharedPreferences
import com.cmilan.holycode_test.utils.DispatcherProvider
import com.cmilan.holycode_test.utils.PrefUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesPreferences(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences("user_preference", Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesPrefUtils(sharedPreferences: SharedPreferences): PrefUtils = PrefUtils(sharedPreferences)

    @Singleton
    @Provides
    fun provideDispatchers(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main
        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
        override val default: CoroutineDispatcher
            get() = Dispatchers.Default
        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

}