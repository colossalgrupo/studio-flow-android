package com.colossalgrupo.studioflow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.colossalgrupo.studioflow.ui.auth.LoginScreen
import com.colossalgrupo.studioflow.ui.client.ClientHomeScreen
import com.colossalgrupo.studioflow.ui.splash.SplashScreen

@Composable
fun StudioScheduleNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    // Este app é só para o cliente final; o login já rejeita contas de
                    // Empreendedor antes de chegar aqui (ver RemoteAuthRepository).
                    navController.navigate(Screen.ClientHome.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.ClientHome.route) {
            ClientHomeScreen()
        }
    }
}
