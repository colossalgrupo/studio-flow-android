package com.colossalgrupo.studioflow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.colossalgrupo.studioflow.domain.model.UserRole
import com.colossalgrupo.studioflow.ui.auth.LoginScreen
import com.colossalgrupo.studioflow.ui.client.ClientHomeScreen
import com.colossalgrupo.studioflow.ui.entrepreneur.EntrepreneurHomeScreen
import com.colossalgrupo.studioflow.ui.entrepreneur.NewProfessionalScreen
import com.colossalgrupo.studioflow.ui.profileselect.ProfileSelectionScreen
import com.colossalgrupo.studioflow.ui.splash.SplashScreen

@Composable
fun StudioFlowNavHost(
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
                    navController.navigate(Screen.ProfileSelection.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.ProfileSelection.route) {
            ProfileSelectionScreen(
                onProfileSelected = { role ->
                    val destination = when (role) {
                        UserRole.ENTREPRENEUR -> Screen.EntrepreneurHome.route
                        UserRole.CLIENT -> Screen.ClientHome.route
                    }
                    navController.navigate(destination) {
                        popUpTo(Screen.ProfileSelection.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.ClientHome.route) {
            ClientHomeScreen()
        }

        composable(Screen.EntrepreneurHome.route) {
            EntrepreneurHomeScreen(
                onAddProfessional = { navController.navigate(Screen.NewProfessional.route) }
            )
        }

        composable(Screen.NewProfessional.route) {
            NewProfessionalScreen(
                onSaved = { navController.popBackStack() },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
