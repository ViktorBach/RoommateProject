package com.example.roommateproject.FrontPage.Components.HomeButton

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roommateproject.Services.AccountService
import kotlinx.coroutines.launch
import sendNotification

class HomeButtonViewModel(
    private val accountService: AccountService
) : ViewModel() {

    private var _isHome = mutableStateOf(false)
    val isHome: State<Boolean> get() = _isHome

    fun toggleHomeStatus(context: Context) {
        _isHome.value = !_isHome.value
        viewModelScope.launch {
            if (_isHome.value) {
                sendNotification("User is home", context)
                accountService.addEvent(AccountService.EventType.I_AM_HOME)
            } else {
                sendNotification("User is leaving", context)
                accountService.addEvent(AccountService.EventType.I_AM_LEAVING)
            }
        }
    }
}
