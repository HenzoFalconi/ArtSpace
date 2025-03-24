package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.artspace.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EvolutionApp()
        }
    }
}

@Composable
fun EvolutionApp() {
    var currentScreen by remember { mutableStateOf(1) }
    val totalScreens = 4

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color.Yellow),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Evoluções🔥🐉",
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }

            when (currentScreen) {
                1 -> EvolutionScreen(
                    imageRes = R.drawable.image_removebg_preview__6_,
                    description = "Toque para o Charmander evoluir",
                    evolutionText = "1º evolução registrada em 1996",
                    onNext = { currentScreen = if (currentScreen == totalScreens) 1 else currentScreen + 1 }
                )
                2 -> EvolutionScreen(
                    imageRes = R.drawable.image_removebg_preview__10_,
                    description = "Toque para o Charmeleon evoluir",
                    evolutionText = "2º evolução registrada em 1997",
                    onNext = { currentScreen = if (currentScreen == totalScreens) 1 else currentScreen + 1 },
                    onBack = { currentScreen = if (currentScreen == 1) totalScreens else currentScreen - 1 }
                )
                3 -> EvolutionScreen(
                    imageRes = R.drawable.charizard,
                    description = "Toque para o Charizard mega-evoluir",
                    evolutionText = "3º evolução registrada em 1998",
                    onNext = { currentScreen = if (currentScreen == totalScreens) 1 else currentScreen + 1 },
                    onBack = { currentScreen = if (currentScreen == 1) totalScreens else currentScreen - 1 }
                )
                4 -> EvolutionScreen(
                    imageRes = R.drawable.mega,
                    description = "Clique para voltar ao início",
                    evolutionText = "Mega evolução ativada!",
                    onNext = { currentScreen = if (currentScreen == totalScreens) 1 else currentScreen + 1 },
                    onBack = { currentScreen = if (currentScreen == 1) totalScreens else currentScreen - 1 }
                )
            }
        }
    }
}

@Composable
fun EvolutionScreen(
    imageRes: Int,
    description: String,
    evolutionText: String,
    onNext: () -> Unit,
    onBack: (() -> Unit)? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Imagem da evolução",
            modifier = Modifier.size(500.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = buildAnnotatedString {
                append(description + "\n")
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append(evolutionText)
                }
            },
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            onBack?.let {
                Button(onClick = it) {
                    Text("Voltar")
                }
            }

            Button(onClick = onNext) {
                Text("Próxima")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EvolutionApp()
}
