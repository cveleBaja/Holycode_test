package com.cmilan.holycode_test.ui.screen.user_repos

import com.cmilan.holycode_test.data.model.UserRepo
import com.cmilan.holycode_test.data.network.GeneralApiEndpoint
import com.cmilan.holycode_test.utils.Resource
import javax.inject.Inject

class UserReposActivityRepository
@Inject constructor(
    private val mService: GeneralApiEndpoint,
) {

    suspend fun fetchUserRepos() : Resource<List<UserRepo>?> {
        return try {
            val response = mService.fetchUserRepos()
            val userRepos = response.body()

            Resource.Success(userRepos)

        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

}