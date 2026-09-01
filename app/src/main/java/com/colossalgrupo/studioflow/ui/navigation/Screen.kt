package com.colossalgrupo.studioflow.ui.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object ProfileSelection : Screen("profile_selection")
    data object ClientHome : Screen("client_home")
    data object EntrepreneurHome : Screen("entrepreneur_home")
    data object NewProfessional : Screen("new_professional")
}
