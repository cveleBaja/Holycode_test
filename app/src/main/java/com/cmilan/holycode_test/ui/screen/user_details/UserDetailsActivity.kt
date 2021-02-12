package com.cmilan.holycode_test.ui.screen.user_details

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cmilan.holycode_test.R
import com.cmilan.holycode_test.data.model.User
import com.cmilan.holycode_test.databinding.ActivityUserDetailsBinding
import com.cmilan.holycode_test.ui.screen.base.BaseActivity
import com.cmilan.holycode_test.ui.screen.user_repos.UserReposActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsActivity : BaseActivity() {

    private val mViewModel: UserDetailsActivityViewModel by viewModels()
    private lateinit var mBinding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setOnClickListeners()
        configureSwipeToRefresh()

        mViewModel.userDetailsEvent.observe(this) { event ->
            when (event) {
                is UserDetailsActivityViewModel.UserEvent.Loading -> {
                    mBinding.swipeRefresh.isRefreshing = true
                }
                is UserDetailsActivityViewModel.UserEvent.Error -> {
                    mBinding.swipeRefresh.isRefreshing = false
                    showGeneralError(event.error)
                }
                is UserDetailsActivityViewModel.UserEvent.Success -> {
                    mBinding.swipeRefresh.isRefreshing = false
                }
                else -> Unit
            }
        }

        mViewModel.getUserLiveData().observe(this) {
            it?.let {
                refreshUi(it)
            }
        }
    }

    private fun setOnClickListeners() {
        mBinding.btnRepos.setOnClickListener {
            val intent = Intent(this, UserReposActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureSwipeToRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener { mViewModel.fetchUserDetails() }
    }

    private fun refreshUi(user: User?) {

        user?.let {
            mBinding.tvName.text = it.name
            mBinding.tvCompany.text = it.company
            mBinding.tvType.text = it.type ?: getString(R.string.no_value_label)
            mBinding.tvEmail.text = it.email ?: getString(R.string.no_value_label)
            mBinding.tvLocation.text = it.location ?: getString(R.string.no_value_label)
            mBinding.tvReposCount.text = it.public_repos?.toString() ?: getString(R.string.no_value_label)
            mBinding.tvFollowers.text = it.followers?.toString() ?: getString(R.string.no_value_label)
            mBinding.tvFollowing.text = it.following?.toString() ?: getString(R.string.no_value_label)

            Glide
                .with(this)
                .load(it.avatar_url)
                .circleCrop()
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_user_default)
                        .error(R.drawable.ic_user_default)
                )
                .into(mBinding.imgUser)
        }
    }
}