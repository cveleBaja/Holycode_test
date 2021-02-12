package com.cmilan.holycode_test.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cmilan.holycode_test.data.model.Owner
import com.cmilan.holycode_test.data.model.UserRepo
import com.cmilan.holycode_test.data.model.OwnerWithRepos

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(repo: UserRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOwner(owner: Owner)

    @Query("SELECT * FROM owner")
    fun getOwnerWithRepos(): LiveData<OwnerWithRepos?>
}