package com.cmilan.holycode_test.ui.rv_viewholder

import androidx.recyclerview.widget.RecyclerView
import com.cmilan.holycode_test.data.model.UserRepo
import com.cmilan.holycode_test.databinding.RvItemUserRepoBinding

class UserRepositoryViewHolder(private val mBinding: RvItemUserRepoBinding, private val mInterface: UserRepositoryViewHolderInterface?) : RecyclerView.ViewHolder(mBinding.root) {

    interface UserRepositoryViewHolderInterface {
        fun onRepositoryClicked(position: Int)
    }

    fun bind(item: UserRepo) {
        mBinding.tvRepoName.text = item.name
        mBinding.tvOpenIssuesNo.text = item.open_issues_count?.toString() ?: "0"
    }

    init {
        mBinding.root.setOnClickListener {
            if (mInterface != null && bindingAdapterPosition > -1) {
                mInterface.onRepositoryClicked(bindingAdapterPosition)
            }
        }
    }
}