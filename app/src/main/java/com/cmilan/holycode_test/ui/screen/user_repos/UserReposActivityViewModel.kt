package com.cmilan.holycode_test.ui.screen.user_repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmilan.holycode_test.data.model.UserRepo
import com.cmilan.holycode_test.utils.DispatcherProvider
import com.cmilan.holycode_test.utils.PrefUtils
import com.cmilan.holycode_test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserReposActivityViewModel
@Inject constructor(
    private val mRepository: UserReposActivityRepository,
    private val dispatchers: DispatcherProvider,
    private val mPrefUtils: PrefUtils
) : ViewModel() {

    sealed class UserReposEvent {
        class Success(val response: List<UserRepo>?) : UserReposEvent()
        class Error(val error: Exception) : UserReposEvent()
        object Loading : UserReposEvent()
        object Empty : UserReposEvent()
    }

    private val _userRepos = MutableLiveData<UserReposEvent>(UserReposEvent.Empty)
    val userRepos: LiveData<UserReposEvent> = _userRepos

    init {
        fetchUserRepos()
    }

    fun fetchUserRepos() {
        _userRepos.value = UserReposEvent.Loading

        viewModelScope.launch(dispatchers.io) {
            when (val userDetailsResponse = mRepository.fetchUserRepos()) {
                is Resource.Success -> {
                    withContext(dispatchers.main) {
                        _userRepos.value = UserReposEvent.Success(userDetailsResponse.data)
                    }
                }
                is Resource.Error -> {
                    withContext(dispatchers.main) {
                        _userRepos.value = UserReposEvent.Error(userDetailsResponse.e!!)
                    }
                }
            }
        }
    }

    fun setRepoName(name: String) = mPrefUtils.setRepoName(name)

}