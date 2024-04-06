import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContactDataActivity() {
    // Definir los campos de datos
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var ciudad by remember { mutableStateOf("") }

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

    // Función para validar los campos obligatorios
    fun validarCamposObligatorios(): Boolean {
        var isValid = true
        telefonoError = telefono.isEmpty()
        paisError = pais.isEmpty()
        emailError = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        isValid = isValid && !telefonoError && !emailError
        return isValid
    }

    // Columna con los campos de entrada
    Column {
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

        Button(
            onClick = {
                if (validarCamposObligatorios()) {
                    // Todos los campos requeridos están llenos, proceder con el envío
                    // Aquí puedes realizar cualquier acción necesaria, como enviar los datos a un servidor
                } else {
                    // Mostrar un mensaje de error indicando los campos que faltan o son inválidos
                }
            },
            modifier = Modifier
            .align(Alignment.End)
            .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text("Enviar")
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
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            isError = isError,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
fun ContactDataPreview() {
    ContactDataActivity()
}