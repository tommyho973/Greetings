package com.example.greetings

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.greetings.ui.theme.GreetingsTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingsTheme {
                SimpleTextInput()
            }
        }
    }
}


@Composable
fun SimpleTextInput(){
    Column() {
        var text1 by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text1,
            onValueChange = { text1 = it },
            label = { Text("Label") }
        )
        ButtonCreate(text1)
    }
}

@Composable
fun ButtonCreate(name: String){
    var showText by remember{mutableStateOf(false)}
    val estTimeZone = TimeZone.getTimeZone("America/New_York")
    val cal = Calendar.getInstance(estTimeZone)
    val hour = cal.get(Calendar.HOUR_OF_DAY)
    val formattedHour = String.format("%02d", hour)
    val numericalHour = formattedHour.toInt()


    Column() {
        Button(onClick = { showText = !showText }) {
            Text(text = "Get Greeting")
        }
        if (showText) {
            Text(text = "Hello $name")
            if (numericalHour in 12..17) {
                Text(text = "Good Afternoon")
            } else if (numericalHour in 18..23 || numericalHour in 0..4) {
                Text(text = "Have a nice night")
            } else {
            Text(text = "Good Morning, I hope you have a nice day!")
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingsTheme {
    SimpleTextInput()
    }
}