package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitconverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitconverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    unitConverter()
                }
            }
        }
    }
}


@Composable
fun unitConverter(){

    var inputValue by remember {mutableStateOf("")}
    var outputValue by remember {mutableStateOf(" ")}
    var inputUnit by remember {mutableStateOf("Maters")}
    var outputUnit by remember {mutableStateOf("Maters")}
    var iExpended by remember {mutableStateOf(false)}
    var oExpended by remember {mutableStateOf(false)}
    val conversionFactor = remember {mutableStateOf(1.00)}
    val oConversionFactor = remember {mutableStateOf(1.00)}

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 30.sp,
        color = Color.Blue,
    )
    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =
            (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt()/ 100.0
        outputValue = result.toString()

    }




    Column(
        modifier = Modifier.fillMaxSize()
    ,   verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {


        Text("Unit Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {inputValue = it
                            convertUnits()},
            label = { Text("Enter value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //Input
            Box{
                Button(onClick = { iExpended=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "")
                }
                DropdownMenu(expanded = iExpended,
                    onDismissRequest = { iExpended=false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters")},
                        onClick = { iExpended=false
                                    inputUnit = "Centimeters"
                                    conversionFactor.value = 0.01
                                    convertUnits()})
                    DropdownMenuItem(text = { Text(text = "Meters")},
                        onClick = { iExpended=false
                            inputUnit = "Maters"
                            conversionFactor.value = 1.0
                            convertUnits()})
                    DropdownMenuItem(text = { Text(text = "Feet")},
                        onClick = { iExpended=false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()})
                    DropdownMenuItem(text = { Text(text = "Millimeters")},
                        onClick = { iExpended=false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()})

                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output
            Box{
                Button(onClick = { oExpended=true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "")
                    DropdownMenu(expanded = oExpended,
                        onDismissRequest = { oExpended=false }) {
                        DropdownMenuItem(text = { Text(text = "Centimeters")},
                            onClick = { oExpended=false
                                outputUnit = "Centimeters"
                                oConversionFactor.value = 0.01
                                convertUnits()})
                        DropdownMenuItem(text = { Text(text = "Meters")},
                            onClick = { oExpended=false
                                outputUnit = "Meters"
                                oConversionFactor.value = 1.0
                                convertUnits()})
                        DropdownMenuItem(text = { Text(text = "Feet")},
                            onClick = { oExpended=false
                                outputUnit = "Feet"
                                oConversionFactor.value = 0.3048
                                convertUnits()})
                        DropdownMenuItem(text = { Text(text = "Millimeters")},
                            onClick = { oExpended=false
                                outputUnit = "Millimeters"
                                oConversionFactor.value = 0.001
                                convertUnits()})

                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue $outputUnit",
            style = customTextStyle
            )
    }
}






@Preview(showBackground = true)
@Composable
fun unitConverterPreview(){
    unitConverter()


}