package com.tinellus.adminproject.views

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object LoginView : Screen("login")
    object ProductsView : Screen("products")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}