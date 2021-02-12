package com.cmilan.holycode_test.ui.screen.user_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmilan.holycode_test.data.model.User
import com.cmilan.holycode_test.utils.DispatcherProvider
import com.cmilan.holycode_test.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserDetailsActivityViewModel
@Inject constructor(
    private val mRepository: UserDetailsActivityRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    sealed class UserEvent {
        class Success(val response: String?) : UserEvent()
        class Error(val error: Exception) : UserEvent()
        object Loading : UserEvent()
        object Empty : UserEvent()
    }

    private val _userDetailsEvent = MutableLiveData<UserEvent>(UserEvent.Empty)
    val userDetailsEvent: LiveData<UserEvent> = _userDetailsEvent

    init {
        fetchUserDetails()
    }

    fun fetchUserDetails() {
        _userDetailsEvent.value = UserEvent.Loading

        viewModelScope.launch(dispatchers.io) {
            when (val userDetailsResponse = mRepository.fetchUserDetails()) {
                is Resource.Success -> {
                    withContext(dispatchers.main) {
                        _userDetailsEvent.value = UserEvent.Success(userDetailsResponse.data)
                    }
                }
                is Resource.Error -> {
                    withContext(dispatchers.main) {
                        _userDetailsEvent.value = UserEvent.Error(userDetailsResponse.e!!)
                    }
                }
            }
        }
    }

    fun getUserLiveData(): LiveData<User?> = mRepository.getUserLiveData()
}