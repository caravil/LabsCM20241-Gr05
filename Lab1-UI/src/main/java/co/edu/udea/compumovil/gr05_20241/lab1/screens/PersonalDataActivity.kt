import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr05_20241.lab1.navigation.AppNavigation
import co.edu.udea.compumovil.gr05_20241.lab1.navigation.AppScreens
import java.time.Instant
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataActivity (navController: NavController){
    var hombreSeleccionado by remember { mutableStateOf(false) }
    var mujerSeleccionada by remember { mutableStateOf(false) }

    var nombres by remember {
        mutableStateOf("")
    }

    var apellidos by remember {
        mutableStateOf("")
    }
    var fecha = rememberDatePickerState()

    var showDialog by remember{
        mutableStateOf(false)
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var escolaridad by remember {
        mutableStateOf("")
    }

    Column (modifier= Modifier.fillMaxSize()){

        ContactField(
            label = "Nombres",
            icon = Icons.Filled.Person,
            keyboardType = KeyboardType.Text,
            value=nombres,
            isError = false,
            onValueChange = {nombres = it}
        )
        ContactField(
            label = "Apellidos",
            icon =Icons.Filled.Person,
            keyboardType = KeyboardType.Text,
            value=apellidos,
            isError = false,
            onValueChange = {apellidos = it}
        )
        Row (modifier = Modifier.padding(15.dp)){
            Icon(imageVector = Icons.Filled.Star, contentDescription = "sexo")
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Sexo")
            RadioButton(
                selected = hombreSeleccionado,
                onClick = { hombreSeleccionado = !hombreSeleccionado;
                          mujerSeleccionada = false},
            )
            Text(text= "Hombre")
            RadioButton(
                selected = mujerSeleccionada,
                onClick = { mujerSeleccionada = !mujerSeleccionada;
                          hombreSeleccionado = false},
            )
            Text(text = "Mujer")
        }



        Row (modifier = Modifier.padding(15.dp)){
            TextField(
                value = fecha.selectedDateMillis.toString() ,
                onValueChange = {},
                readOnly = true)
            Spacer(modifier = Modifier.width(15.dp))
            Button(onClick = {showDialog = true}) {
                Text(text = "f")
            }
        }

        if (showDialog){

        DatePickerDialog(
            onDismissRequest = { showDialog=false},
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = "confirmar")
                    
                }
            }) {

            DatePicker(state = fecha)
        }}



               ExposedDropdownMenuBox(

                   expanded = isExpanded,
                   onExpandedChange = { isExpanded = it }, modifier = Modifier.padding(15.dp)) {
                   TextField(
                       value = escolaridad,
                       onValueChange = {},
                       readOnly = true,
                       trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
                   )

                   ExposedDropdownMenu(
                       expanded = isExpanded,
                       onDismissRequest = { isExpanded = false }) {
                       DropdownMenuItem(
                           text = { "bachiller" },
                           onClick = {
                               escolaridad = "bachiller"
                               isExpanded = false
                           }


                       )
                       DropdownMenuItem(
                           text = { "pregrado" },
                           onClick = {
                               escolaridad = "pregrado"
                               isExpanded = false
                           }


                       )
                       DropdownMenuItem(
                           text = { "maestria" },
                           onClick = {
                               escolaridad = "maestria"
                               isExpanded = false
                           }


                       )

                       DropdownMenuItem(
                           text = { "posgrado" },
                           onClick = {
                               escolaridad = "posgrado"
                               isExpanded = false
                           }


                       )

                   }
               }


        Button(
            modifier = Modifier.fillMaxWidth().padding(30.dp),

            onClick = {
            navController.navigate(AppScreens.ContactDataActivity.route)

        }) {
            Text(text = "Siguiente")
        }



    }
}














@Preview
@Composable
fun previa (){
    AppNavigation()
}