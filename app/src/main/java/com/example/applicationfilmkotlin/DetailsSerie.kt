package com.example.applicationfilmkotlin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun DetailSerieScreen(id:Int,navController: NavController){
    val viewModel: MainViewModel = viewModel()
    val serie by viewModel.detailSerie.collectAsState()

    viewModel.detailSeries(id)
    val imageUrl = "https://image.tmdb.org/t/p/original" + (serie?.backdrop_path ?: String)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Image de fond
        AsyncImage(
            model = imageUrl,
            contentDescription = serie?.name,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(16.dp)) {
            serie?.let {
                Text(
                    text = it.name,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
            }

            serie?.let {
                Text(
                    text = it.tagline,
                    style = TextStyle(fontSize = 16.sp, fontStyle = FontStyle.Italic),
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Carte pour la description
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Synopsis",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    serie?.let {
                        Text(
                            text = it.overview,
                            style = TextStyle(fontSize = 16.sp),
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        serie?.let {
            Text(
                text = "Status: ${it.status}",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                text = "Premier épisode: ${it.first_air_date}",
                style = TextStyle(fontSize = 16.sp),
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Liste des créateurs
        Text(
            text = "Créateurs",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            serie?.created_by?.take(5)?.forEach { creator ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w185${creator.profile_path}",
                        contentDescription = creator.name,
                        modifier = Modifier
                            .shadow(8.dp)
                            .clickable {
                                navController.navigate(DetailsActeur(creator.id))
                            }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = creator.name,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                        maxLines = 1
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Main Cast",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            serie?.credits?.cast?.forEach { cast ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w185${cast.profile_path}",
                        contentDescription = cast.name,
                        modifier = Modifier
                            .shadow(8.dp)
                            .clickable {
                                navController.navigate(DetailsActeur(cast.id))
                            }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = cast.name,
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                        maxLines = 1
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Liste des saisons
        Text(
            text = "Saisons",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            serie?.seasons?.forEach { season ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),

                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Saison ${season.season_number}",
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = season.overview,
                            style = TextStyle(fontSize = 16.sp),
                            color = Color.DarkGray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}