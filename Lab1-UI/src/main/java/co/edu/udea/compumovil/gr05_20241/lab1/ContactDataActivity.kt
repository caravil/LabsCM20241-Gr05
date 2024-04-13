
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class ContactViewModel : ViewModel() {
    var telefono by mutableStateOf("")
    var email by mutableStateOf("")
    var ciudad by mutableStateOf("")
    var direccion by mutableStateOf("")
    var pais by mutableStateOf("")

    var telefonoError by mutableStateOf(false)
    var emailError by mutableStateOf(false)
    var paisError by mutableStateOf(false)
}

@Composable
fun ContactDataActivity(navController: NavController, viewModel: ContactViewModel = viewModel()) {
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            AppBar(title = "Contact Information", onNavigationClick = { navController.popBackStack() })
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
                    ContactField("Phone*", Icons.Filled.Phone, KeyboardType.Phone, viewModel.telefono, viewModel.telefonoError) {
                        viewModel.telefono = it
                    }
                    ContactField("Email*", Icons.Filled.Email, KeyboardType.Email, viewModel.email, viewModel.emailError) {
                        viewModel.email = it
                    }
                    ContactField("City", Icons.Filled.LocationOn, KeyboardType.Text, viewModel.ciudad, false) {
                        viewModel.ciudad = it
                    }
                }
                Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                    ContactField("Address", Icons.Filled.Home, KeyboardType.Text, viewModel.direccion, false) {
                        viewModel.direccion = it
                    }
                    ContactField("Country*", Icons.Filled.Place, KeyboardType.Text, viewModel.pais, viewModel.paisError) {
                        viewModel.pais = it
                    }
                    SubmitButton(navController, viewModel)
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
            ) {
                ContactField("Phone*", Icons.Filled.Phone, KeyboardType.Phone, viewModel.telefono, viewModel.telefonoError) {
                    viewModel.telefono = it
                }
                ContactField("Address", Icons.Filled.Home, KeyboardType.Text, viewModel.direccion, false) {
                    viewModel.direccion = it
                }
                ContactField("Email*", Icons.Filled.Email, KeyboardType.Email, viewModel.email, viewModel.emailError) {
                    viewModel.email = it
                }
                ContactField("City", Icons.Filled.LocationOn, KeyboardType.Text, viewModel.ciudad, false) {
                    viewModel.ciudad = it
                }
                ContactField("Country*", Icons.Filled.Place, KeyboardType.Text, viewModel.pais, viewModel.paisError) {
                    viewModel.pais = it
                }
                SubmitButton(navController, viewModel)
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
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go back")
            }
        }
    )
}

@Composable
fun ContactField(label: String, icon: ImageVector, keyboardType: KeyboardType, value: String, isError: Boolean, onValueChange: (String) -> Unit) {
    CampoDeEntrada(
        value = value,
        onValueChange = onValueChange,
        label = label,
        icono = icon,
        keyboardType = keyboardType,
        isError = isError
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
            label = { Text(label, color = if (isError) Color.Red else Color.Unspecified) },
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = ImeAction.Next
            ),
            isError = isError,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun SubmitButton(navController: NavController, viewModel: ContactViewModel) {
    Button(
        onClick = {
            viewModel.telefonoError = viewModel.telefono.isBlank()
            viewModel.emailError = !Patterns.EMAIL_ADDRESS.matcher(viewModel.email).matches()
            viewModel.paisError = viewModel.pais.isBlank()

            if (!viewModel.telefonoError && !viewModel.emailError && !viewModel.paisError) {
                navController.navigate("MainActivity")
            }
        },
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {
        Text("Submit")
    }
}

@Preview
@Composable
fun ContactDataPreview() {
    val navController = rememberNavController()
    ContactDataActivity(navController)
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