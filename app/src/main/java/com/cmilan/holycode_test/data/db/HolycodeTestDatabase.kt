package com.cmilan.holycode_test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cmilan.holycode_test.data.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class HolycodeTestDatabase : RoomDatabase() {

}