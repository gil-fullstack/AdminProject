package com.tinellus.adminproject.views

sealed class Screen(val route: String, val title: String? = null) {
    object Home : Screen("home")
    object LoginView : Screen("login")
    object ProductsView : Screen("products")
    object ProductRegistrationScreen : Screen("products_register")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}