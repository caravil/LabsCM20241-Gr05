package co.edu.udea.compumovil.gr05_20241.lab1.navigation

sealed class AppScreens(val route: String) {
    //la ruta es el identificador de la pantalla a la cual navegar, tipar o limitar las pantallas
    object PersonalDataActivity : AppScreens("personal_data")
    object ContactDataActivity : AppScreens("contact_data")
    object MainActivity : AppScreens("main_activity")
    object HomeScreen : AppScreens("home")
}