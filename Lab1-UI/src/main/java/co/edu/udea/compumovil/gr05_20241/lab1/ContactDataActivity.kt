
import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ContactDataActivity(navController: NavController) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            AppBar("Información de contacto", onNavigationClick = { navController.popBackStack() })
        }
    ) { innerPadding ->
        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
            ) {
                Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                    ContactField("Teléfono*", Icons.Filled.Phone, KeyboardType.Phone)
                    ContactField("Email*", Icons.Filled.Email, KeyboardType.Email)
                    ContactField("Ciudad", Icons.Filled.LocationOn, KeyboardType.Text)
                }
                Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                    ContactField("Dirección", Icons.Filled.Home, KeyboardType.Text)
                    ContactField("País*", Icons.Filled.Place, KeyboardType.Text)
                    SubmitButton(navController)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
            ) {
                ContactField("Teléfono*", Icons.Filled.Phone, KeyboardType.Phone)
                ContactField("Dirección", Icons.Filled.Home, KeyboardType.Text)
                ContactField("Email*", Icons.Filled.Email, KeyboardType.Email)
                ContactField("Ciudad", Icons.Filled.LocationOn, KeyboardType.Text)
                ContactField("País*", Icons.Filled.Place, KeyboardType.Text)
                SubmitButton(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, onNavigationClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Regresar")
            }
        }
    )
}

@Composable
fun ContactField(label: String, icon: ImageVector, keyboardType: KeyboardType) {
    var value by remember { mutableStateOf("") }

    val isError = remember { mutableStateOf(false) }

    CampoDeEntrada(
        value = value,
        onValueChange = { newValue ->
            value = newValue
            isError.value = label.endsWith("*") && newValue.isEmpty()
        },
        label = label,
        icono = icon,
        keyboardType = keyboardType,
        isError = isError.value // Aquí se usa isError.value para pasar el valor booleano a CampoDeEntrada
    )
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

@Composable
fun SubmitButton(navController: NavController) {
    Button(
        onClick = {
            // Aquí podrías obtener los valores de los campos de entrada y pasarlos a la función de validación
            val isValid = validarCamposObligatorios(
                telefono = "valor_telefono",
                email = "valor_email",
                pais = "valor_pais"
            )
            if (isValid) {
                navController.navigate("MainActivity")
            }
        },
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Text("Enviar")
    }
}

fun validarCamposObligatorios(telefono: String, email: String, pais: String): Boolean {
    val telefonoValido = telefono.isNotBlank()
    val emailValido = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val paisValido = pais.isNotBlank()
    // Otros campos opcionales pueden agregarse y validarse aquí según sea necesario
    return telefonoValido && emailValido && paisValido
}

@Preview
@Composable
fun ContactDataPreview() {
    val navController = rememberNavController() // NavController falso
    ContactDataActivity(navController = navController)
}

@Preview(name = "Portrait", widthDp = 360, heightDp = 640)
@Composable
fun ContactDataPreviewPortrait() {
    ContactDataPreview()
}

@Preview(name = "Landscape", widthDp = 640, heightDp = 360)
@Composable
fun ContactDataPreviewLandscape() {
    ContactDataPreview()
}