package com.cmilan.holycode_test.ui.screen.commits_by_repo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cmilan.holycode_test.data.model.Commit
import com.cmilan.holycode_test.databinding.ActivityCommitsByRepoBinding
import com.cmilan.holycode_test.ui.adapter.CommitByRepoAdapter
import com.cmilan.holycode_test.ui.screen.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommitsByRepoActivity : BaseActivity() {

    private val mViewModel: CommitsByRepoActivityViewModel by viewModels()
    private lateinit var mBinding: ActivityCommitsByRepoBinding
    private lateinit var mCommits: MutableList<Commit>
    private lateinit var mCommitsAdapter: CommitByRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityCommitsByRepoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initialiseRecyclerView()
        configureSwipeRefresh()
        setUpActionBar()

        mViewModel.commitsEvent.observe(this) { event ->
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

                    event.response?.let {
                        mCommitsAdapter.updateItems(it)
                    }
                }
                else -> Unit
            }
        }
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initialiseRecyclerView() {
        mCommits = ArrayList()

        val layoutManager = LinearLayoutManager(this)
        mBinding.rvCommits.layoutManager = layoutManager

        mCommitsAdapter = CommitByRepoAdapter(mCommits)
        mBinding.rvCommits.adapter = mCommitsAdapter
    }

    private fun configureSwipeRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener { mViewModel.fetchCommitsByRepo() }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}