package com.example.roommateproject.FrontPage.Components.LogoutButton

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.FrontPage.FrontPageViewModel
import com.example.roommateproject.R

/*****************************************************************************/
                    // Logout Button Implementation //
/*****************************************************************************/
@Composable
fun LogoutButton(navigateRegisterPage: () -> Unit, onClick: () -> Unit) {
    val frontPageViewModel = viewModel<FrontPageViewModel>()
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxHeight(0.8f)
            .fillMaxWidth(0.85f)
            .clickable(onClick = onClick)
            .padding(top = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logouticon),
            contentDescription = "Logout",
            modifier = Modifier.fillMaxSize()
        )
    }
}
