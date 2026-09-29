package com.colossalgrupo.studioflow.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object ClientHome : Screen("client_home")
}
