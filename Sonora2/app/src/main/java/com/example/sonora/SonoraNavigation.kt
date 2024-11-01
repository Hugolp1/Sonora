package com.example.sonora

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sonora.Telas.TelaMenu

@Composable
fun SonoraNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "menu") {
        composable("menu") {
            TelaMenu(navController)
        }
        composable("detalhes/{nomeLivro}") {
            val nomeLivro = it.arguments?.getString("nomeLivro")
            //TelaDetalhes(navController, nomeLivro)
        }
    }
}

