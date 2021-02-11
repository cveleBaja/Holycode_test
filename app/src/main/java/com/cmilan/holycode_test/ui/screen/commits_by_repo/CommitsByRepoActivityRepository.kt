package com.cmilan.holycode_test.ui.screen.commits_by_repo

import com.cmilan.holycode_test.data.model.Commit
import com.cmilan.holycode_test.data.network.GeneralApiEndpoint
import com.cmilan.holycode_test.utils.Resource
import javax.inject.Inject

class CommitsByRepoActivityRepository
@Inject constructor(
    private val mService: GeneralApiEndpoint
) {

    suspend fun fetchCommitsByRepo(repoName: String): Resource<List<Commit>?> {
        return try {
            val response = mService.fetchCommitsByRepo(repoName)
            val commits = response.body()

            Resource.Success(commits)

        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}