@file:OptIn(ExperimentalMaterial3Api::class)
package com.softdevelopers.techbridge_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.softdevelopers.techbridge_compose.ui.theme.TechBridge_composeTheme
import com.softdevelopers.techbridge_compose.ui.theme.lilitaone
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TechBridge_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

val CustomFont = FontFamily(
    Font(R.font.lilitaone_regular)
)

@Composable
fun AppContent(modifier: Modifier = Modifier) {
    var showWelcomeScreen by remember { mutableStateOf(true) }
    var isLoggedIn by remember { mutableStateOf(false) }
    var showConsultasScreen by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        delay(3000)
        showWelcomeScreen = false
    }

    when {
        showWelcomeScreen -> MainScreen()
        showConsultasScreen -> ConsultasMongoScreen(
            onBack = { showConsultasScreen = false } // Regresa al MenuScreen

        )

        // Pantalla de consultas
        isLoggedIn -> MenuScreen(
            onConsultasClick = { showConsultasScreen = true } // Navega a ConsultasScreen
        )
        else -> LoginScreen(onLoginSuccess = { isLoggedIn = true })
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
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

            Text(
                text = stringResource(R.string.softdevelopers),
                fontSize = 18.sp,
                fontFamily = CustomFont,
                color = Color.Blue,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 230.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(modifier: Modifier = Modifier, onLoginSuccess: () -> Unit) {
    val validUsers = listOf("carlos" to "12345", "user1" to "password1")
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginError by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(286.dp)
                    .height(118.dp)
                    .offset(y = -70.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

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
                placeholder = { Text(text = "Username", color = Color.Black) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(50.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF007AFF),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

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
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF007AFF),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    loginError = !validUsers.any { it.first == username && it.second == password }
                    if (!loginError) {
                        onLoginSuccess()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(text = "Iniciar Sesión", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (loginError) {
                Text(
                    text = "Usuario o contraseña incorrectos",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            Text(
                text = "SoftDevelopers",
                fontSize = 18.sp,
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

@Composable
fun MenuScreen(onConsultasClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Icon(
            painter = painterResource(id = R.drawable.iconback),
            contentDescription = "Back",
            tint = Color(0xFF007AFF),
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.Start)
                .offset(y = 30.dp)
                .clickable { /* Acción para retroceder */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Menu",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007AFF),
            fontFamily = CustomFont
        )

        Spacer(modifier = Modifier.height(24.dp))

        MenuOption(
            iconId = R.drawable.icondashboard,
            text = "Consultas de mongo",
            textColor = Color(0xFF007AFF),
            onClick = onConsultasClick // Llama a ConsultasScreen al hacer clic
        )
        MenuOption(
            iconId = R.drawable.iconssas,
            text = "Consultas del modelo tabular",
            textColor = Color(0xFF007AFF),
            onClick = onConsultasClick // Llama a ConsultasScreen al hacer clic
        )

        MenuOption(
            iconId = R.drawable.iconconfig,
            text = "Configuraciones",
            textColor = Color(0xFF007AFF),
            onClick = onConsultasClick // Llama a ConsultasScreen al hacer clic
        )

        MenuOption(
            iconId = R.drawable.iconacerca,
            text = "Acerca De",
            textColor = Color(0xFF007AFF),

            )

        MenuOption(
            iconId = R.drawable.iconcerrarsesion,
            text = "Cerrar Sesión",
            textColor = Color.Red
        )
    }
}

@Composable
fun MenuOption(iconId: Int, text: String, textColor: Color, onClick: () -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color(0xFF007AFF)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = text,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    fontFamily = CustomFont
                )
            }
        }
    }
}

@Composable
fun ConsultasMongoScreen(onBack: () -> Unit, onClick: () -> Unit = {} ) {
    var selectedScreen by remember { mutableStateOf<String?>(null) }

    when (selectedScreen) {
        "Consulta1" -> Consulta1Screen()
        "Consulta2" -> Consulta2Screen()
        "Consulta3" -> Consulta3Screen()
        "Consulta4" -> Consulta4Screen()
        "Consulta5" -> Consulta5Screen()
        "Consulta6" -> Consulta6Screen()
        "Consulta7" -> Consulta7Screen()
        "Consulta8" -> Consulta8Screen()
        "Consulta9" -> Consulta9Screen()
        "Consulta10" -> Consulta10Screen()
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.iconback),
                    contentDescription = "Back",
                    tint = Color(0xFF007AFF),
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.Start)
                        .offset(y = 30.dp)
                        .clickable { onBack() }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Consultas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF007AFF),
                    fontFamily = CustomFont,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(24.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    item {
                        ConsultaOption(
                            iconId = R.drawable.icondashboard,
                            text = "Consultas1",
                            textColor = Color(0xFF007AFF),
                            onClick = { selectedScreen = "Consulta1" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconconfig,
                            text = "Consulta2",
                            textColor = Color(0xFF007AFF),
                            onClick = { selectedScreen = "Consulta2" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconacerca,
                            text = "Consulta3",
                            textColor = Color(0xFF007AFF),
                            onClick = { selectedScreen = "Consulta3" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconacerca,
                            text = "Consulta4",
                            textColor = Color(0xFF007AFF),
                            onClick = { selectedScreen = "Consulta4" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconacerca,
                            text = "Consulta5",
                            textColor = Color(0xFF007AFF),
                            onClick = { selectedScreen = "Consulta5" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconacerca,
                            text = "Consulta6",
                            textColor = Color(0xFF007AFF),
                            onClick = { selectedScreen = "Consulta6" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconacerca,
                            text = "Consulta7",
                            textColor = Color(0xFF007AFF),
                            onClick = { selectedScreen = "Consulta7" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconacerca,
                            text = "Consulta8",
                            textColor = Color(0xFF007AFF),
                            onClick = { selectedScreen = "Consulta8" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconacerca,
                            text = "Consulta9",
                            textColor = Color(0xFF007AFF),
                            onClick = { selectedScreen = "Consulta9" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconacerca,
                            text = "Consulta10",
                            textColor = Color(0xFF007AFF),
                            onClick = { selectedScreen = "Consulta10" }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ConsultaOption(iconId: Int, text: String, textColor: Color, onClick: () -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color(0xFF007AFF)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = text,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = text,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    fontFamily = CustomFont
                )
            }
        }
    }
}

@Composable
fun Consulta2Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Consulta 2", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF007AFF))
    }
}

@Composable
fun Consulta3Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Consulta 3", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF007AFF))
    }
}

@Composable
fun Consulta4Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Consulta 4", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF007AFF))
    }
}

@Composable
fun Consulta5Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Consulta 5", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF007AFF))
    }
}

@Composable
fun Consulta6Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Consulta 6", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF007AFF))
    }
}
@Composable
fun Consulta7Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Consulta 7", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF007AFF))
    }
}
@Composable
fun Consulta8Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Consulta 8", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF007AFF))
    }
}

@Composable
fun Consulta9Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Consulta 9", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF007AFF))
    }
}

@Composable
fun Consulta10Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Consulta 10", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF007AFF))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TechBridge_composeTheme {
        //MainScreen()
        Consulta1Screen()
    }
}