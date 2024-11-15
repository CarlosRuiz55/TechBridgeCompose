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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.material3.TopAppBar
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
    var isLoggedIn by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = if (isLoggedIn) "menu" else "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = {
                isLoggedIn = true
                navController.navigate("menu") {
                    // Limpiar la pila para evitar que el usuario vuelva a la pantalla de login
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        composable("menu") {
            MenuScreen(
                onConsultasClick = { navController.navigate("consultas") },
                onLogoutClick = {
                    isLoggedIn = false
                    navController.navigate("login") {
                        popUpTo("menu") { inclusive = true }
                    }
                },
                onConsultasTabularesClick = { navController.navigate("consultatabular") },
                onAcercaClick = { navController.navigate("acerca") }
            )
        }
        composable("acerca") {
            AcercadeScreen(onBackClick = { navController.popBackStack() })
        }
        composable("consultatabular") {
            ConsultasTabularScreen(onBack = { navController.popBackStack() })
        }
        composable("consultas") {
            ConsultasMongoScreen(onBack = { navController.popBackStack() })
        }
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
                ),
                textStyle = TextStyle(color = Color.Black)  // Cambiar color del texto a negro
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
                ),
                textStyle = TextStyle(color = Color.Black)

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
fun MenuScreen(onConsultasClick: () -> Unit, onLogoutClick: () -> Unit,onAcercaClick: () -> Unit, onConsultasTabularesClick : () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(76.dp))

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
            onClick = onConsultasTabularesClick // Llama a ConsultasScreen al hacer clic
        )

        MenuOption(
            iconId = R.drawable.iconacerca,
            text = "Acerca De",
            textColor = Color(0xFF007AFF),
            onClick = onAcercaClick // Navegación a AcercadeScreen

            )

        MenuOption(
            iconId = R.drawable.iconcerrarsesion,
            text = "Cerrar Sesión",
            textColor = Color.Red,
            onClick = onLogoutClick
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
        "Consulta1" -> Consulta1Screen(onBack = { selectedScreen = null })
        "Consulta2" -> Consulta2Screen(onBack = { selectedScreen = null })
        "Consulta3" -> Consulta3Screen(onBack = { selectedScreen = null })
        "Consulta4" -> Consulta4Screen(onBack = { selectedScreen = null })

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
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

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconconsulta1,
                            text = "Top 5 proyectos más caros 2024-2025 por margen de ganancia",
                            textColor = Color(11,11,11 ),
                            onClick = { selectedScreen = "Consulta1" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconconsulta2,
                            text = "Proyectos con un margen de ganancia mayor a 10,000 que se encuentran en estado finalizado",
                            textColor = Color(11,11,11 ),
                            onClick = { selectedScreen = "Consulta2" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconconsulta3,
                            text = "Empleados que han trabajado mas de 100 horas",
                            textColor = Color(11,11,11 ),
                            onClick = { selectedScreen = "Consulta3" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconconsulta4,
                            text = "Empleados con salario superior a 2500 y más de 20 tareas completadas en proyectos",
                            textColor = Color(11,11,11 ),
                            onClick = { selectedScreen = "Consulta4" }
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
        border = BorderStroke(2.dp, Color(43, 192, 147)),
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
                    fontWeight = FontWeight.Normal,
                    color = textColor,
                    fontFamily = CustomFont,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
@Composable
fun ConsultasTabularScreen(onBack: () -> Unit, onClick: () -> Unit = {} ) {
    var selectedScreen by remember { mutableStateOf<String?>(null) }

    when (selectedScreen) {
        "Consulta1" -> Consulta1Screen(onBack = { selectedScreen = null })
        "Consulta2" -> Consulta2Screen(onBack = { selectedScreen = null })
        "Consulta3" -> Consulta3Screen(onBack = { selectedScreen = null })
        "Consulta4" -> Consulta4Screen(onBack = { selectedScreen = null })

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
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
                    text = "Consultas tabulares",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF007AFF),
                    fontFamily = CustomFont,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconconsulta1,
                            text = "Top 5 proyectos más caros 2024-2025 por margen de ganancia",
                            textColor = Color(11,11,11 ),
                            onClick = { selectedScreen = "Consulta1" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconconsulta2,
                            text = "Proyectos con un margen de ganancia mayor a 10,000 que se encuentran en estado finalizado",
                            textColor = Color(11,11,11 ),
                            onClick = { selectedScreen = "Consulta2" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconconsulta3,
                            text = "Empleados que han trabajado mas de 100 horas",
                            textColor = Color(11,11,11 ),
                            onClick = { selectedScreen = "Consulta3" }
                        )
                    }
                    item {
                        ConsultaOption(
                            iconId = R.drawable.iconconsulta4,
                            text = "Empleados con salario superior a 2500 y más de 20 tareas completadas en proyectos",
                            textColor = Color(11,11,11 ),
                            onClick = { selectedScreen = "Consulta4" }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AcercadeScreen(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Botón de retroceso
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color(0xFF007AFF),
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.Start)
                .offset(y = 30.dp)
                .clickable { onBackClick() }
        )

        // Título de la pantalla
        Text(
            text = "Acerca de",
            fontFamily = CustomFont,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007AFF),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp)
                .offset(y = 0.dp)
        )

        Spacer(modifier = Modifier.height(70.dp))

        // Icono de la aplicación
        Icon(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = "Logo",
            tint = Color.Black,
            modifier = Modifier
                .width(286.dp)
                .height(118.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            text = "Versión 0.0.1",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Descripción de la aplicación
        Text(
            text = "TechBridge es una aplicación desarrollada por SoftDevelopers que proporciona información esencial para la toma de decisiones empresariales. Esta es la primera versión de la aplicación, diseñada para ayudar a la empresa a optimizar sus procesos mediante el acceso rápido y sencillo a datos clave.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Objetivo de la aplicación
        Text(
            text = "Nuestro objetivo es ofrecer una plataforma intuitiva que permita a los usuarios tomar decisiones informadas basadas en datos precisos y actualizados.",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(220.dp))

        // Nombre de la empresa
        Text(
            text = "SoftDevelopers",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF3A86FF),
            fontFamily = CustomFont,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TechBridge_composeTheme {
        //MainScreen()

        Consulta4Screen {

        }

    }
}