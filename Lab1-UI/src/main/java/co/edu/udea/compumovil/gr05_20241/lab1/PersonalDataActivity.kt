import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataActivity (){
    var sexo by remember {
        mutableStateOf(false)
    }

    var fecha = rememberDatePickerState()

    Column {

        ContactField(label = "Nombres", icon = Icons.Filled.Person, keyboardType = KeyboardType.Text, value="pollito", isError = false){}
        ContactField(label = "Apellidos", icon =Icons.Filled.Person , keyboardType = KeyboardType.Text, value="chicken", isError = false){}
        Row (modifier = Modifier.padding(15.dp)){
            Icon(imageVector = Icons.Filled.Star, contentDescription = "sexo")
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Sexo")
            RadioButton(selected = sexo, onClick = { sexo = true})
            Text(text= "Hombre")
            RadioButton(selected = sexo, onClick = { sexo= false })
            Text(text = "Mujer")
        }



        DatePicker(state = fecha)
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Siguiente")

        }
    }
}














@Preview
@Composable
fun previa (){
    PersonalDataActivity()
}