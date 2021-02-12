package com.cmilan.holycode_test.utils.diff_util

import androidx.recyclerview.widget.DiffUtil
import com.cmilan.holycode_test.data.model.Commit

class CommitByRepoDiffUtil(
    private val oldList: List<Commit>,
    private val newList: List<Commit>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.node_id == newItem.node_id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }
}