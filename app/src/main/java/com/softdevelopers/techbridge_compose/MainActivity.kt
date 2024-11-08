@file:OptIn(ExperimentalMaterial3Api::class)
package com.softdevelopers.techbridge_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TechBridge_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppContent( // Llama a AppContent para manejar el flujo de bienvenida y login
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

//Esto es una solución para crear una pantalla de bienvenida temporal que,
// después de un breve retraso, cambia automáticamente a una pantalla de inicio de sesión sin
// requerir interacción del usuario.
@Composable
fun AppContent(modifier: Modifier = Modifier) {
    var showWelcomeScreen by remember { mutableStateOf(true) }
    var isLoggedIn by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000)
        showWelcomeScreen = false
    }

    if (showWelcomeScreen) {
        MainScreen()
    } else if (isLoggedIn) {
        MenuScreen() // Muestra la pantalla del menú después del inicio de sesión exitoso
    } else {
        LoginScreen(onLoginSuccess = { isLoggedIn = true }) // Redirige a MenuScreen después del login
    }
}

//Pantalla de bienvenida de la aplicacion
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
    // Definir lista de usuarios válidos
    val validUsers = listOf(
        "carlos" to "12345",
        "user1" to "password1"
    )

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
            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo1),
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
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF007AFF),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de inicio de sesión
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

            // Nombre de la empresa
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
fun MenuScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Icono de retroceso en la parte superior izquierda
        Icon(
            painter = painterResource(id = R.drawable.iconback), // Cambia 'iconback' por el ícono correcto de regreso
            contentDescription = "Back",
            tint = Color(0xFF007AFF),
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.Start)
                .offset(y = 30.dp)
                .clickable { /* Acción para retroceder */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título del menú
        Text(
            text = "Menu",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007AFF),
            fontFamily = CustomFont
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Opciones del menú
        MenuOption(
            iconId = R.drawable.icondashboard,
            text = "Consultas",
            textColor = Color(0xFF007AFF)
        )

        MenuOption(
            iconId = R.drawable.iconconfig,
            text = "Configuraciones",
            textColor = Color(0xFF007AFF)
        )

        MenuOption(
            iconId = R.drawable.iconacerca,
            text = "Acerca De",
            textColor = Color(0xFF007AFF)
        )

        MenuOption(
            iconId = R.drawable.iconcerrarsesion,
            text = "Cerrar Sesión",
            textColor = Color.Red
        )
    }
    Spacer(modifier = Modifier.height(1.dp))
    // Nombre de la empresa
    Text(
        text = "SoftDevelopers",
        fontSize = 18.sp,
        color = Color.Blue,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 720.dp),
        fontFamily = CustomFont
    )
}

//Pantalla del menu
@Composable
fun MenuOption(iconId: Int, text: String, textColor: Color) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color(0xFF007AFF)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Acción al hacer clic en la opción */ },

    ) {
        // Caja interna con fondo blanco
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
fun ConsultasScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Icono de retroceso en la parte superior izquierda
        Icon(
            painter = painterResource(id = R.drawable.iconback), // Cambia 'iconback' por el ícono correcto de regreso
            contentDescription = "Back",
            tint = Color(0xFF007AFF),
            modifier = Modifier
                .size(36.dp)
                .align(Alignment.Start)
                .offset(y = 30.dp)
                .clickable { /* Acción para retroceder */ }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título del menú
        Text(
            text = "Consultas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007AFF),
            fontFamily = CustomFont
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Opciones del menú
        MenuOption(
            iconId = R.drawable.icondashboard,
            text = "Consulta 1",
            textColor = Color(0xFF007AFF)
        )

        MenuOption(
            iconId = R.drawable.iconconfig,
            text = "Consulta 2",
            textColor = Color(0xFF007AFF)
        )

        MenuOption(
            iconId = R.drawable.iconacerca,
            text = "Consulta 3",
            textColor = Color(0xFF007AFF)
        )

        MenuOption(
            iconId = R.drawable.iconcerrarsesion,
            text = "Consulta 4",
            textColor = Color(0xFF007AFF)
        )
        MenuOption(
            iconId = R.drawable.iconcerrarsesion,
            text = "Consulta 5",
            textColor = Color(0xFF007AFF)
        )
        MenuOption(
            iconId = R.drawable.iconcerrarsesion,
            text = "Consulta 6",
            textColor = Color(0xFF007AFF)
        )
        MenuOption(
            iconId = R.drawable.iconcerrarsesion,
            text = "Consulta 7",
            textColor = Color(0xFF007AFF)
        )
        MenuOption(
            iconId = R.drawable.iconcerrarsesion,
            text = "Consulta 8",
            textColor = Color(0xFF007AFF)
        )
    }
    Spacer(modifier = Modifier.height(1.dp))
//    // Nombre de la empresa
//    Text(
//        text = "SoftDevelopers",
//        fontSize = 18.sp,
//        color = Color.Blue,
//        textAlign = TextAlign.Center,
//        modifier = Modifier
//            .fillMaxWidth()
//            .offset(y = 720.dp),
//        fontFamily = CustomFont
//    )
}

//Pantalla del menu
@Composable
fun ConsultasOption(iconId: Int, text: String, textColor: Color) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color(0xFF007AFF)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { /* Acción al hacer clic en la opción */ },

        ) {
        // Caja interna con fondo blanco
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TechBridge_composeTheme {
        //MenuScreen()
        ConsultasScreen()
    }
}