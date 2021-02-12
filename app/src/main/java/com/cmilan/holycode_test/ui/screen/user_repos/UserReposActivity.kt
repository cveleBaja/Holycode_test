package com.cmilan.holycode_test.ui.screen.user_repos

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cmilan.holycode_test.R
import com.cmilan.holycode_test.data.model.UserRepo
import com.cmilan.holycode_test.databinding.ActivityUserReposBinding
import com.cmilan.holycode_test.ui.adapter.UserRepositoryAdapter
import com.cmilan.holycode_test.ui.rv_viewholder.UserRepositoryViewHolder
import com.cmilan.holycode_test.ui.screen.base.BaseActivity
import com.cmilan.holycode_test.ui.screen.commits_by_repo.CommitsByRepoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserReposActivity : BaseActivity(), UserRepositoryViewHolder.UserRepositoryViewHolderInterface {

    private val mViewModel: UserReposActivityViewModel by viewModels()
    private lateinit var mBinding: ActivityUserReposBinding
    private lateinit var mUserReposAdapter: UserRepositoryAdapter
    private lateinit var mRepos: MutableList<UserRepo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUserReposBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initialiseRecyclerView()
        configureSwipeRefresh()
        setUpActionBar()

        mViewModel.userReposEvent.observe(this) { event ->
            when(event) {
                is UserReposActivityViewModel.UserReposEvent.Loading -> {
                    mBinding.swipeRefresh.isRefreshing = true
                }
                is UserReposActivityViewModel.UserReposEvent.Error -> {
                    mBinding.swipeRefresh.isRefreshing = false
                    showGeneralError(event.error)
                }
                is UserReposActivityViewModel.UserReposEvent.Success -> {
                    mBinding.swipeRefresh.isRefreshing = false
                }
                else -> Unit
            }
        }

        mViewModel.getOwnerWithReps().observe(this) {
            it?.let {
                mUserReposAdapter.updateItems(it.repos)
            }
        }
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun configureSwipeRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener { mViewModel.fetchUserRepos() }
    }

    private fun initialiseRecyclerView() {
        mRepos = ArrayList()

        val layoutManager = LinearLayoutManager(this)
        mBinding.rvUserRepos.layoutManager = layoutManager

        mUserReposAdapter = UserRepositoryAdapter(mRepos, this as UserRepositoryViewHolder.UserRepositoryViewHolderInterface)
        mBinding.rvUserRepos.adapter = mUserReposAdapter
    }

    override fun onRepositoryClicked(position: Int) {
        mViewModel.setRepoName(mRepos[position].name ?: getString(R.string.no_value_label))

        val intent = Intent(this, CommitsByRepoActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}