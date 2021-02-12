package com.cmilan.holycode_test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cmilan.holycode_test.data.model.Commit
import com.cmilan.holycode_test.databinding.RvItemCommitByRepoBinding
import com.cmilan.holycode_test.ui.rv_viewholder.CommitByRepoViewHolder
import com.cmilan.holycode_test.utils.diff_util.CommitByRepoDiffUtil

class CommitByRepoAdapter(
    private val mItems: MutableList<Commit>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return CommitByRepoViewHolder(RvItemCommitByRepoBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CommitByRepoViewHolder) {
            holder.bind(mItems[position])
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun updateItems(items: List<Commit>) {
        val diffUtil = CommitByRepoDiffUtil(mItems, items)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        mItems.clear()
        mItems.addAll(items)

        diffResult.dispatchUpdatesTo(this)
    }
}