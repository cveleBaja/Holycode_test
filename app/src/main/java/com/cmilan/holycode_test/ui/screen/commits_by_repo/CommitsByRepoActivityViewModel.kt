package com.cmilan.holycode_test.ui.screen.commits_by_repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmilan.holycode_test.data.model.Commit
import com.cmilan.holycode_test.utils.DispatcherProvider
import com.cmilan.holycode_test.utils.PrefUtils
import com.cmilan.holycode_test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CommitsByRepoActivityViewModel
@Inject constructor(
    private val mRepository: CommitsByRepoActivityRepository,
    private val dispatchers: DispatcherProvider,
    private val mPrefUtils: PrefUtils
) : ViewModel() {

    companion object {
        private const val TAG = "CommitsByRepoActivityVi"
    }

    sealed class CommitsEvent {
        class Success(val response: List<Commit>?) : CommitsEvent()
        class Error(val error: Exception) : CommitsEvent()
        object Loading : CommitsEvent()
        object Empty : CommitsEvent()
    }

    private val _commits = MutableLiveData<CommitsEvent>(CommitsEvent.Empty)
    val commits: LiveData<CommitsEvent> = _commits

    init {
        Log.i(TAG, ": ${mPrefUtils.repoName!!}")
        fetchCommitsByRepo()
    }

    fun fetchCommitsByRepo() {
        _commits.value = CommitsEvent.Loading

        viewModelScope.launch(dispatchers.io) {
            when (val userDetailsResponse = mRepository.fetchCommitsByRepo(mPrefUtils.repoName ?: "")) {
                is Resource.Success -> {
                    withContext(dispatchers.main) {
                        _commits.value = CommitsEvent.Success(userDetailsResponse.data)
                    }
                }
                is Resource.Error -> {
                    withContext(dispatchers.main) {
                        _commits.value = CommitsEvent.Error(userDetailsResponse.e!!)
                    }
                }
            }
        }
    }
}