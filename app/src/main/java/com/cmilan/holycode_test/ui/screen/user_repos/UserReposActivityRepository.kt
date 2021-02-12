package com.cmilan.holycode_test.ui.screen.user_repos

import androidx.lifecycle.LiveData
import com.cmilan.holycode_test.data.db.dao.ReposDao
import com.cmilan.holycode_test.data.model.OwnerWithRepos
import com.cmilan.holycode_test.data.model.UserRepo
import com.cmilan.holycode_test.data.network.GeneralApiEndpoint
import com.cmilan.holycode_test.utils.Resource
import javax.inject.Inject

class UserReposActivityRepository
@Inject constructor(
    private val mService: GeneralApiEndpoint,
    private val mReposDao: ReposDao
) {

    suspend fun fetchUserRepos() : Resource<String?> {
        return try {
            val response = mService.fetchUserRepos()
            val userRepos = response.body()

            if (userRepos != null) {
                for (repo in userRepos) {
                    repo.ownerIdReference = repo.owner?.id ?: 0
                    mReposDao.insertRepo(repo)
                    mReposDao.insertOwner(repo.owner!!)
                }
            }

            Resource.Success("Success")

        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    fun getOwnerWithRepos(): LiveData<OwnerWithRepos?> = mReposDao.getOwnerWithRepos()

}