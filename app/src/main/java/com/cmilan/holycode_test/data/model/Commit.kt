package com.cmilan.holycode_test.data.model

data class Commit(
    val author: Author?,
    val comments_url: String?,
    val commit: CommitX?,
    val html_url: String?,
    val node_id: String?,
    val sha: String?,
    val url: String?
)