package com.example.applicationfilmkotlin


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessibility
import androidx.compose.material.icons.filled.Movie

import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.applicationfilmkotlin.ui.theme.ApplicationFilmKotlinTheme
import kotlinx.serialization.Serializable

@Serializable
data class Films(val titre: String)

@Serializable
data class Series(val name: String)

@Serializable
data class Acteurs(val name: String)

@Serializable
data class DetailsFilm(val id: Int)

@Serializable
data class DetailsActeur(val id:Int)
@Serializable
data class DetailsSerie(val id:Int)

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            var text by remember { mutableStateOf("") }
            var isSearchActive by remember { mutableStateOf(false) }

            ApplicationFilmKotlinTheme {

                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Catalogue de Film")
                        },
                        actions = {
                            if (!isSearchActive) {
                                IconButton(onClick = {
                                    isSearchActive = !isSearchActive
                                }) {
                                    Icon(Icons.Filled.Search, contentDescription = "Recherche")
                                }
                            } else {
                                if (currentDestination?.hasRoute<Films>() == true) {
                                    TextField(
                                        value = text,
                                        onValueChange = { text = it },
                                        placeholder = { Text("Rechercher un Film") })
                                    Button(
                                        onClick = {
                                            if (text.isNotEmpty()) {
                                                navController.navigate(Films(text))
                                            }
                                            isSearchActive = false
                                            text=""
                                        }
                                    ) {
                                        Icon(
                                            Icons.Filled.Search,
                                            contentDescription = "Recherche"
                                        )
                                    }
                                } else {
                                    if (currentDestination?.hasRoute<Series>() == true){
                                        TextField(
                                            value = text,
                                            onValueChange = { text = it },
                                            placeholder = { Text("Rechercher une Serie") })
                                        Button(
                                            onClick = {
                                                if (text.isNotEmpty()) {
                                                    navController.navigate(Series(text))
                                                }
                                                isSearchActive = false
                                                text=""
                                            }
                                        ) {
                                            Icon(
                                                Icons.Filled.Search,
                                                contentDescription = "Recherche"
                                            )
                                        }
                                    }else{
                                        if(currentDestination?.hasRoute<Acteurs>() == true){
                                            TextField(
                                                value = text,
                                                onValueChange = { text = it },
                                                placeholder = { Text("Rechercher un Acteur") })
                                            Button(
                                                onClick = {
                                                    if (text.isNotEmpty()) {
                                                        navController.navigate(Acteurs(text))
                                                    }
                                                    isSearchActive = false
                                                    text=""
                                                }
                                            ) {
                                                Icon(
                                                    Icons.Filled.Search,
                                                    contentDescription = "Recherche"
                                                )
                                            }
                                        }
                                        else{
                                            if (currentDestination?.hasRoute<DetailsFilm>() == true) {
                                                TextField(
                                                    value = text,
                                                    onValueChange = { text = it },
                                                    placeholder = { Text("Rechercher un Film") })
                                                Button(
                                                    onClick = {
                                                        if (text.isNotEmpty()) {
                                                            navController.navigate(Films(text))
                                                        }
                                                        isSearchActive = false
                                                        text=""
                                                    }
                                                ) {
                                                    Icon(
                                                        Icons.Filled.Search,
                                                        contentDescription = "Recherche"
                                                    )
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        },
                    )
                }, bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Movie,
                                    contentDescription = "Affichage de film"
                                )
                            }, label = { Text("Films") },
                            selected = currentDestination?.hasRoute<Films>() == true,
                            onClick = { navController.navigate(Films("")) })
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Tv,
                                    contentDescription = "Affichage de series"
                                )
                            }, label = { Text("Series") },
                            selected = currentDestination?.hasRoute<Series>() == true,
                            onClick = { navController.navigate(Series("")) })
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Accessibility,
                                    contentDescription = "Affichage des acteurs"
                                )
                            }, label = { Text("Acteurs") },
                            selected = currentDestination?.hasRoute<Acteurs>() == true,
                            onClick = { navController.navigate(Acteurs("")) })
                    }
                })
                { innerPadding ->
                    NavHost(
                        navController = navController, startDestination = Films(""),
                        Modifier.padding(innerPadding)
                    ) {
                        composable<Films> { backStackEntry ->
                            val films: Films = backStackEntry.toRoute()
                            FilmsScreen(films.titre,navController)
                        }
                        composable<Series> { backStackEntry ->
                            val series: Series = backStackEntry.toRoute()
                            SeriesScreen(series.name,navController)
                        }
                        composable<Acteurs> { backStackEntry ->
                            val acteurs: Acteurs = backStackEntry.toRoute()
                            ActeursScreen(acteurs.name,navController)
                        }
                        composable<DetailsFilm> { backStackEntry ->
                            val details: DetailsFilm = backStackEntry.toRoute()
                            DetailFilmScreen(details.id,navController)
                        }
                        composable<DetailsActeur> { backStackEntry ->
                            val details: DetailsActeur = backStackEntry.toRoute()
                            DetailActeurScreen(details.id)
                        }
                        composable<DetailsSerie> { backStackEntry ->
                            val details: DetailsSerie = backStackEntry.toRoute()
                            DetailSerieScreen(details.id,navController) }
                    }
                }
            }
        }
    }
}