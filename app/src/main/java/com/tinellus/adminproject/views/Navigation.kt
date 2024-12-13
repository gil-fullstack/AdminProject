package com.tinellus.adminproject.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            Home(navController)
        }
        composable(route = Screen.LoginView.route) {
            LoginView(navController)
        }
        composable(route = Screen.ProductsView.route) {
            ProductsView(navController)
        }

    }
}