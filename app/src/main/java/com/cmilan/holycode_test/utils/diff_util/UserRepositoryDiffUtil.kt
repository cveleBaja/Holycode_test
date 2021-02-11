package com.cmilan.holycode_test.utils.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.cmilan.holycode_test.data.model.UserRepo

class UserRepositoryDiffUtil(
    private val oldList: List<UserRepo>,
    private val newList: List<UserRepo>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }
}