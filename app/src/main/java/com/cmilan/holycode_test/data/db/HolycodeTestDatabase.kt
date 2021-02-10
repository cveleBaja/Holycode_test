package com.cmilan.holycode_test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cmilan.holycode_test.data.db.dao.UserDao
import com.cmilan.holycode_test.data.db.dao.UserReposDao
import com.cmilan.holycode_test.data.model.Owner
import com.cmilan.holycode_test.data.model.User
import com.cmilan.holycode_test.data.model.UserRepo

@Database(
    entities = [User::class, UserRepo::class, Owner::class],
    version = 1,
    exportSchema = false
)
abstract class HolycodeTestDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getUserReposDao(): UserReposDao

}