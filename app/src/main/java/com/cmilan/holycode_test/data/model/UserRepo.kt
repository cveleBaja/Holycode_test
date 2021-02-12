package com.cmilan.holycode_test.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UserRepo(
    val commits_url: String?,
    val full_name: String?,
    val git_commits_url: String?,
    val git_url: String?,
    @PrimaryKey
    val id: Int,
    val name: String?,
    val open_issues: Int?,
    val open_issues_count: Int?,
    val size: Int?,
    val ssh_url: String?,
    val url: String?,
    @Ignore
    val owner: Owner?,
    val language: String?,
    val has_issues: Boolean?,
    val has_projects: Boolean?,
    val has_downloads: Boolean?,
    val has_wiki: Boolean?,
    val has_pages: Boolean?,
    val forks_count: Int?,
    val watchers: Int?,
    val default_branch: String?,
    @SerializedName("private") val isPrivate: Boolean?,
    var ownerIdReference: Int
) {
    constructor(
        commits_url: String?,
        full_name: String?,
        git_commits_url: String?,
        git_url: String?,
        id: Int,
        name: String?,
        open_issues: Int?,
        open_issues_count: Int?,
        size: Int?,
        ssh_url: String?,
        url: String?,
        language: String?,
        has_issues: Boolean?,
        has_projects: Boolean?,
        has_downloads: Boolean?,
        has_wiki: Boolean?,
        has_pages: Boolean?,
        forks_count: Int?,
        watchers: Int?,
        default_branch: String?,
        isPrivate: Boolean?,
        ownerIdReference: Int
    ) : this(
        commits_url,
        full_name,
        git_commits_url,
        git_url,
        id,
        name,
        open_issues,
        open_issues_count,
        size,
        ssh_url,
        url,
        null,
        language,
        has_issues,
        has_projects,
        has_downloads,
        has_wiki,
        has_pages,
        forks_count,
        watchers,
        default_branch,
        isPrivate,
        ownerIdReference
    )
}