@file:OptIn(ExperimentalMaterial3Api::class)
package com.softdevelopers.techbridge_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.softdevelopers.techbridge_compose.ui.theme.TechBridge_composeTheme
import com.softdevelopers.techbridge_compose.ui.theme.lilitaone

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TechBridge_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

//Declaramos la fuente importada y esta la podemos llamar cada ves que querramos mediante CustomFont
val CustomFont = FontFamily(
    Font(R.font.lilitaone_regular)
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
//Pantalla de bienvenida de la aplicacion
@Composable

fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Imagen del logo sin Card
        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = stringResource(R.string.logodetechbridge),
            modifier = Modifier
                .width(286.dp)
                .height(118.dp)
                .padding(vertical = 0.dp)
                .offset(y = -70.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        // Texto de pie de página
        Text(
            text = stringResource(R.string.softdevelopers),
            fontSize = 22.sp,
            fontFamily = CustomFont,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 230.dp)
        )
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo1), // Cambia a tu logo en drawable
                contentDescription = "Logo",
                modifier = Modifier
                    .width(286.dp)
                    .height(118.dp)
                    .offset(y = -70.dp)
            )




            Spacer(modifier = Modifier.height(32.dp))

            // Campo de usuario
            Text(
                text = "Username",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black,
                fontFamily = CustomFont

            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = { Text(text = "Username", color = Color.Black ) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(50.dp),
                colors = outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF007AFF), // Color del borde cuando está enfocado
                    unfocusedBorderColor = Color.Gray      // Color del borde cuando no está enfocado
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de contraseña
            Text(
                text = "Password",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start),
                color = Color.Black,
                fontFamily = CustomFont
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(text = "Password", color = Color.Black) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(50.dp),
                colors = outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF007AFF), // Color del borde cuando está enfocado
                    unfocusedBorderColor = Color.Gray     // Color del borde cuando no está enfocado
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de inicio de sesión
            Button(
                onClick = { /* Acción de inicio de sesión */ },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .offset(y = 0.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(text = "Iniciar Sesión", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre de la empresa
            Text(
                text = "SoftDevelopers",
                fontSize = 14.sp,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 120.dp),
                fontFamily = CustomFont
            )
        }
    }
}






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TechBridge_composeTheme {
        //MainScreen()
        LoginScreen()
    }
}