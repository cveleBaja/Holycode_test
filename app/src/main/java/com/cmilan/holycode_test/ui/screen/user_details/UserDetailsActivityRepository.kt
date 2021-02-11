package com.cmilan.holycode_test.ui.screen.user_details

import androidx.lifecycle.LiveData
import com.cmilan.holycode_test.data.db.dao.UserDao
import com.cmilan.holycode_test.data.model.User
import com.cmilan.holycode_test.data.network.GeneralApiEndpoint
import com.cmilan.holycode_test.utils.Resource
import javax.inject.Inject

class UserDetailsActivityRepository
@Inject constructor(
    private val mService: GeneralApiEndpoint,
    private val mUserDao: UserDao
) {

    suspend fun fetchUserDetails(): Resource<String?> {
        return try {
            val response = mService.fetchUser()
            val user = response.body()

            user?.let {
                mUserDao.insertUser(it)
            }

            Resource.Success("Success")

        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    fun getUserLiveData(): LiveData<User?> {
        return mUserDao.getUserLiveData();
    }
}