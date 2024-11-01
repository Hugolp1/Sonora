package com.example.sonora.Telas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sonora.Data.Musica
import com.example.sonora.Data.MusicaDatabase
import com.example.sonora.ui.theme.SonoraTheme
import kotlinx.coroutines.launch


@Composable
fun TelaMenu(navController: NavController) {
    SonoraTheme {
        Surface (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            //shadowElevation = 10.dp,
            border = BorderStroke(0.5.dp, Color.LightGray),
            //color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        ) {


            var musicas = listOf(
                Musica(0, "AAAAAAAA", "02:12"),
                Musica(1, "BBBBB", "03:12"),
                Musica(2, "CCCCCC", "01:16")
            )

            Column {
                InputNovaMusica()
                Spacer(modifier = Modifier.height(30.dp))
                ListaDeMusicas(musicas = musicas, navController)
            }

        }
    }
}

@Composable
fun InputNovaMusica(){

    var nome by remember { mutableStateOf("") }
    var duracao by remember { mutableStateOf("") }

    val corroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val db = MusicaDatabase.getDatabase(context)

    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        Text("Novo Musica: ", fontSize = 30.sp)

        Row (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            OutlinedTextField(
                value = nome,
                onValueChange = {nome = it},
                label = { Text("Nome")},
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = duracao,
                onValueChange = {duracao = it},
                label = { Text("duracao")},
                modifier = Modifier.weight(1f)
            )
        }

        Button(onClick = {
            corroutineScope.launch {
                val novaMusica = Musica(0, nome, duracao)
                //db.musicaDao().addMusica(novaMusica)
            }
        }) {
            Text(text = "Adicionar Musica")
        }
    }
}



@Composable
fun CardMusica(musica: Musica, escolherMusica: (String) -> Unit = {}){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable{
                escolherMusica(musica.nome)
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column (
            modifier = Modifier.padding(6.dp)
        ) {
            Text(text = "ID: ${musica.id}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Nome: ${musica.nome}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Duração: ${musica.duracao}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun ListaDeMusicas(musicas: List<Musica>, navController: NavController){
    LazyColumn {
        items(musicas){ musica ->
            CardMusica(musica = musica){
                    li -> navController.navigate(route = "detalhes/$li")
            }
        }

    }
}

