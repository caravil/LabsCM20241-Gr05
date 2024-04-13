@file:OptIn(ExperimentalMaterial3Api::class)

package co.edu.udea.compumovil.gr05_20241.lab1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.time.format.TextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun PersonalDataScreen (navController: NavController){
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var selectedSexo by remember { mutableStateOf("") }
    var fechaNacimiento by remember { mutableStateOf("") }
    var selectedGradoEscolaridad by remember { mutableStateOf("") }




        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ){
            OutlinedTextField(
                value = nombres,
                onValueChange = {nombres= it},
                label = {Text("Nombres*")},
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words
                )
            )

            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Apellidos*") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words
                )
            )


            Row {
                Text("Sexo:")
                RadioButton(
                    selected = selectedSexo == "Masculino",
                    onClick = { selectedSexo = "Masculino" },
                    
                )

                RadioButton(
                    selected = selectedSexo == "Femenino",
                    onClick = { selectedSexo = "Femenino" },
                )
            }


            
        }



}



