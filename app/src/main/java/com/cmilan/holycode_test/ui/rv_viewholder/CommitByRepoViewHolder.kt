package com.cmilan.holycode_test.ui.rv_viewholder

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cmilan.holycode_test.R
import com.cmilan.holycode_test.data.model.Commit
import com.cmilan.holycode_test.databinding.RvItemCommitByRepoBinding
import com.cmilan.holycode_test.utils.DateTimeUtils

class CommitByRepoViewHolder(
    private val mBinding: RvItemCommitByRepoBinding
) : RecyclerView.ViewHolder(mBinding.root) {

    private val mContext: Context = mBinding.root.context

    fun bind(item: Commit) {

        mBinding.tvCommit.text = String.format(mContext.getString(R.string.commit_label_format), bindingAdapterPosition + 1)

        mBinding.tvAuthorNameValue.text = item.commit?.author?.name
        mBinding.tvAuthorEmailValue.text = item.commit?.author?.email
        mBinding.tvAuthorDateValue.text = DateTimeUtils.formatCommitTime(item.commit?.author?.date)

        mBinding.tvCommitterNameValue.text = item.commit?.committer?.name
        mBinding.tvCommitterEmailValue.text = item.commit?.committer?.email
        mBinding.tvCommitterDateValue.text = DateTimeUtils.formatCommitTime(item.commit?.committer?.date)

        mBinding.tvMessageValue.text = item.commit?.message

        mBinding.tvCommentCountValue.text = item.commit?.comment_count.toString()
        val isVerified = item.commit?.verification?.verified ?: false
        mBinding.tvVerifiedValue.text = if (isVerified) "Verified" else "Not Verified"
        mBinding.tvVerifiedValue.setTextColor(ContextCompat.getColor(mContext, if (isVerified) R.color.colorGreen else R.color.colorRed ))
        mBinding.tvReasonValue.text = item.commit?.verification?.reason

        mBinding.tvHtmlUrl.text = item.html_url ?: ""

    }

}