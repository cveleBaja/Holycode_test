package com.cmilan.holycode_test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cmilan.holycode_test.data.model.UserRepo
import com.cmilan.holycode_test.databinding.RvItemUserRepoBinding
import com.cmilan.holycode_test.ui.rv_viewholder.UserRepositoryViewHolder
import com.cmilan.holycode_test.utils.diff_util.UserRepositoryDiffUtil

class UserRepositoryAdapter(
    private val mItems: MutableList<UserRepo>,
    private val mInterface: UserRepositoryViewHolder.UserRepositoryViewHolderInterface
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return UserRepositoryViewHolder(RvItemUserRepoBinding.inflate(inflater, parent, false), mInterface)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserRepositoryViewHolder) {
            holder.bind(mItems[position])
        }
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    fun updateItems(items: List<UserRepo>) {
        val diffUtil = UserRepositoryDiffUtil(mItems, items)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        mItems.clear()
        mItems.addAll(items)

        diffResult.dispatchUpdatesTo(this)
    }
}