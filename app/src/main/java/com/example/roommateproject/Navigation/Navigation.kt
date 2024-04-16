package com.example.roommateproject.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.roommateproject.FrontPage.FrontPage
import com.example.roommateproject.Register.Register

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Register") {
        composable("Register"){
            Register(navigateRoomLogin = { navController.navigate("RoomLogin")})
        }
        composable("RoomLogin"){
            FrontPage()
        }
        composable("FrontPage"){
            FrontPage()
        }
    }
}