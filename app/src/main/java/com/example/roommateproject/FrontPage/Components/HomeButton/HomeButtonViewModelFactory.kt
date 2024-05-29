package com.example.roommateproject.FrontPage.Components.HomeButton

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roommateproject.Services.AccountService

/*****************************************************************************/
                // HomeButtonViewModelFactory class //
/*****************************************************************************/

// Natazja
class HomeButtonViewModelFactory(
    private val accountService: AccountService,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeButtonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeButtonViewModel(accountService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
