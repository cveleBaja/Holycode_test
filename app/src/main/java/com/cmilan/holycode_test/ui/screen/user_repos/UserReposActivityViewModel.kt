package com.cmilan.holycode_test.ui.screen.user_repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmilan.holycode_test.data.model.OwnerWithRepos
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
        class Success(val response: String?) : UserReposEvent()
        class Error(val error: Exception) : UserReposEvent()
        object Loading : UserReposEvent()
        object Empty : UserReposEvent()
    }

    private val _userReposEvent = MutableLiveData<UserReposEvent>(UserReposEvent.Empty)
    val userReposEvent: LiveData<UserReposEvent> = _userReposEvent

    init {
        fetchUserRepos()
    }

    fun fetchUserRepos() {
        _userReposEvent.value = UserReposEvent.Loading

        viewModelScope.launch(dispatchers.io) {
            when (val userDetailsResponse = mRepository.fetchUserRepos()) {
                is Resource.Success -> {
                    withContext(dispatchers.main) {
                        _userReposEvent.value = UserReposEvent.Success(userDetailsResponse.data)
                    }
                }
                is Resource.Error -> {
                    withContext(dispatchers.main) {
                        _userReposEvent.value = UserReposEvent.Error(userDetailsResponse.e!!)
                    }
                }
            }
        }
    }

    fun setRepoName(name: String) = mPrefUtils.setRepoName(name)

    fun getOwnerWithReps(): LiveData<OwnerWithRepos?> = mRepository.getOwnerWithRepos()

}