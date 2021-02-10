package com.cmilan.holycode_test.data.network

import com.cmilan.holycode_test.data.model.Commit
import com.cmilan.holycode_test.data.model.User
import com.cmilan.holycode_test.data.model.UserRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GeneralApiEndpoint {

    @GET("users/octocat")
    suspend fun fetchUser(): Response<User?>

    @GET("users/octocat/repos")
    suspend fun fetchUserRepos(): Response<List<UserRepo?>>

    @GET("repos/octocat/{repo_name}boysenberry-repo-1/commits")
    suspend fun fetchCommitsByRepo(@Path("repo_name") repo_name: String): Response<List<Commit?>>
}