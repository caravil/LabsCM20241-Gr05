import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDataActivity(navController: NavController) {
    // Definir los campos de datos
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }

    // Define un estado para controlar si los datos se enviaron correctamente
    var datosEnviadosExitosamente by remember { mutableStateOf(false) }

    // Variables para controlar los mensajes de error
    var telefonoError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var paisError by remember { mutableStateOf(false) }

    // Iconos para los campos
    val telefonoIcono = Icons.Filled.Phone
    val direccionIcono = Icons.Filled.Home
    val emailIcono = Icons.Filled.Email
    val ciudadIcono = Icons.Filled.LocationOn
    val paisIcono = Icons.Filled.Place

    // Verifica si la orientación es landscape
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    // Función para validar los campos obligatorios
    fun validarCamposObligatorios(): Boolean {
        var isValid = true
        telefonoError = telefono.isEmpty()
        paisError = pais.isEmpty()
        emailError = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        isValid = isValid && !telefonoError && !emailError
        return isValid
    }

    // Aquí puedes definir tus composiciones dependiendo de si estás en modo paisaje o no
    if (isLandscape) {
        // Composiciones específicas para orientación landscape
    } else {
        // Composiciones específicas para orientación portrait


        // Columna con los campos de entrada
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {

            TopAppBar(
                title = {
                    Text(
                        text = "Informacion de contacto",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            CampoDeEntrada(
                value = telefono,
                onValueChange = { telefono = it },
                label = "Teléfono*",
                keyboardType = KeyboardType.Phone,
                icono = telefonoIcono,
                isError = telefonoError
            )
            CampoDeEntrada(
                value = direccion,
                onValueChange = { direccion = it },
                label = "Dirección",
                icono = direccionIcono
            )
            CampoDeEntrada(
                value = email,
                onValueChange = { email = it },
                label = "Email*",
                keyboardType = KeyboardType.Email,
                icono = emailIcono,
                isError = emailError
            )

            CampoDeEntrada(
                value = ciudad,
                onValueChange = { ciudad = it },
                label = "Ciudad",
                keyboardType = KeyboardType.Email,
                icono = ciudadIcono
            )

            CampoDeEntrada(
                value = pais,
                onValueChange = { pais = it },
                label = "Pais*",
                icono = paisIcono,
                isError = paisError
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Botón para enviar datos
                Button(
                    onClick = {
                        if (validarCamposObligatorios()) {
                            // Todos los campos requeridos están llenos, proceder con el envío
                            // Actualizar el estado para indicar que los datos se han enviado con éxito
                            datosEnviadosExitosamente = true
                        } else {
                            // Mostrar un mensaje de error indicando los campos que faltan o son inválidos
                            // Esto podría hacerse mostrando un Toast, un Snackbar o simplemente actualizando un estado para mostrar un mensaje de error
                        }
                    }
                ) {
                    Text("Enviar")
                }

                // Botón para retroceder sin enviar datos
                Button(
                    onClick = {
                        // Puedes simplemente retroceder usando el NavController
                        navController.popBackStack()
                    }
                ) {
                    Text("Retroceder")
                }
            }
        }

        // Navegar a la pantalla inicial si los datos se enviaron correctamente
        if (datosEnviadosExitosamente) {
            // Utiliza una acción de navegación para volver a la página inicial
            // Esto podría ser mediante un NavController u otra forma de navegación en tu aplicación
            navController.navigate("ruta_a_la_pantalla_inicial")
        }
    }
}

@Composable
fun CampoDeEntrada(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    icono: ImageVector,
    isError: Boolean = false
) {
    Row(Modifier.padding(16.dp)) {
        Icon(icono, contentDescription = label, Modifier.padding(top = 20.dp, end = 20.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next // Esto cambiará el botón "Enter" por "Siguiente"
            ),
            isError = isError,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun ContactDataPreview() {
    val navController = rememberNavController() // NavController falso para la vista previa
    ContactDataActivity(navController = navController)
}