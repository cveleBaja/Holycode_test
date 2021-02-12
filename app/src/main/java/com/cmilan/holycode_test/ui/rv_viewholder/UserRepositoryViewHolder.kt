package com.cmilan.holycode_test.ui.rv_viewholder

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cmilan.holycode_test.R
import com.cmilan.holycode_test.data.model.UserRepo
import com.cmilan.holycode_test.databinding.RvItemUserRepoBinding

class UserRepositoryViewHolder(private val mBinding: RvItemUserRepoBinding, private val mInterface: UserRepositoryViewHolderInterface?) : RecyclerView.ViewHolder(mBinding.root) {

    interface UserRepositoryViewHolderInterface {
        fun onRepositoryClicked(position: Int)
    }

     private val mContext: Context = mBinding.root.context

    fun bind(item: UserRepo) {

        val hasDownloads = item.has_downloads ?: false
        val hasWiki = item.has_wiki ?: false
        val hasIssues = item.has_issues ?: false
        val hasProjects = item.has_projects ?: false
        val isPrivate = item.isPrivate ?: false

        mBinding.tvRepoName.text = item.name
        mBinding.tvOpenIssuesNo.text = item.open_issues_count?.toString() ?: mContext.getString(R.string.no_value_label)
        mBinding.tvHasDownloads.text = if (hasDownloads) mContext.getString(R.string.yes_label) else mContext.getString(R.string.no_label)
        mBinding.tvHasDownloads.setTextColor(ContextCompat.getColor(mContext, if (hasDownloads) R.color.colorGreen else R.color.colorRed))
        mBinding.tvHasWiki.text = if (hasWiki) mContext.getString(R.string.yes_label) else mContext.getString(R.string.no_label)
        mBinding.tvHasWiki.setTextColor(ContextCompat.getColor(mContext, if (hasWiki) R.color.colorGreen else R.color.colorRed))
        mBinding.tvHasIssues.text = if (hasIssues) mContext.getString(R.string.yes_label) else mContext.getString(R.string.no_label)
        mBinding.tvHasIssues.setTextColor(ContextCompat.getColor(mContext, if (hasIssues) R.color.colorGreen else R.color.colorRed))
        mBinding.tvHasProjects.text = if (hasDownloads) mContext.getString(R.string.yes_label) else mContext.getString(R.string.no_label)
        mBinding.tvHasProjects.setTextColor(ContextCompat.getColor(mContext, if (hasProjects) R.color.colorGreen else R.color.colorRed))
        mBinding.tvIsPrivate.text = if (isPrivate) mContext.getString(R.string.yes_label) else mContext.getString(R.string.no_label)
        mBinding.tvIsPrivate.setTextColor(ContextCompat.getColor(mContext, if (isPrivate) R.color.colorGreen else R.color.colorRed))
        mBinding.tvLanguage.text = item.language ?: mContext.getString(R.string.no_value_label)

    }

    init {
        mBinding.root.setOnClickListener {
            if (mInterface != null && bindingAdapterPosition > -1) {
                mInterface.onRepositoryClicked(bindingAdapterPosition)
            }
        }
    }
}