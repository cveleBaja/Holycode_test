package com.cmilan.holycode_test.di

import android.content.Context
import androidx.room.Room
import com.cmilan.holycode_test.BuildConfig
import com.cmilan.holycode_test.data.db.HolycodeTestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    companion object {
        private const val DATABASE_NAME = "holycode_test_" + BuildConfig.FLAVOR + "_.db"
    }

    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): HolycodeTestDatabase {
        return Room.databaseBuilder(context, HolycodeTestDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}