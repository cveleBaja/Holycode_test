package com.cmilan.holycode_test.data.model

data class UserRepo(
    val commits_url: String?,
    val full_name: String?,
    val git_commits_url: String?,
    val git_url: String?,
    val id: Int,
    val name: String?,
    val open_issues: Int?,
    val open_issues_count: Int?,
    val size: Int?,
    val ssh_url: String?,
    val url: String?,
    val owner: Owner?
)