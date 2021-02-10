package com.cmilan.holycode_test.data.db.dao

import androidx.room.Dao

@Dao
interface UserReposDao {

    suspend fun insertUserRepos()
}