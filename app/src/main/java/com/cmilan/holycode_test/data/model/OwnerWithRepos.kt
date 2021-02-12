package com.cmilan.holycode_test.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class OwnerWithRepos(
    @Embedded
    val owner: Owner,
    @Relation(parentColumn = "id", entityColumn = "ownerIdReference")
    val repos: List<UserRepo>
)
