package co.edu.udea.compumovil.gr05_20241.lab1.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr05_20241.lab1.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { navController.navigate(AppScreens.PersonalDataActivity.route) }) {
            Text("Ir a Datos Personales de Contacto")
        }
    }
}