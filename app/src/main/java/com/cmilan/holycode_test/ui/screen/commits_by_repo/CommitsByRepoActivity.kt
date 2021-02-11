package com.cmilan.holycode_test.ui.screen.commits_by_repo

import android.os.Bundle
import androidx.activity.viewModels
import com.cmilan.holycode_test.databinding.ActivityCommitsByRepoBinding
import com.cmilan.holycode_test.ui.screen.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommitsByRepoActivity : BaseActivity() {

    private val mViewModel: CommitsByRepoActivityViewModel by viewModels()
    private lateinit var mBinding: ActivityCommitsByRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCommitsByRepoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        configureSwipeRefresh()

        mViewModel.commits.observe(this) { event ->
            when(event) {
                is CommitsByRepoActivityViewModel.CommitsEvent.Loading -> {
                    mBinding.swipeRefresh.isRefreshing = true
                }
                is CommitsByRepoActivityViewModel.CommitsEvent.Error -> {
                    mBinding.swipeRefresh.isRefreshing = false
                    showGeneralError(event.error)
                }
                is CommitsByRepoActivityViewModel.CommitsEvent.Success -> {
                    mBinding.swipeRefresh.isRefreshing = false

                    mBinding.tvtv.text = event.response?.get(0)?.sha
                }
                else -> Unit
            }
        }
    }

    private fun configureSwipeRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener { mViewModel.fetchCommitsByRepo() }
    }
}