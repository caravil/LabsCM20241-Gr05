package co.edu.udea.compumovil.gr05_20241.lab1.navigation

import ContactDataActivity
import PersonalDataActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr05_20241.lab1.screens.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.PersonalDataActivity.route) {
            PersonalDataActivity(navController)
        }
        composable(route = AppScreens.ContactDataActivity.route) {
            ContactDataActivity(navController)
        }
    }
}