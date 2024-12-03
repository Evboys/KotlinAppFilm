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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage


@Composable
fun DetailFilmScreen(id:Int,navController: NavController){
    val viewModel: MainViewModel = viewModel()
    val movie by viewModel.detailMovie.collectAsState()

    viewModel.detailMovies(id)
    val imageUrl = "https://image.tmdb.org/t/p/original" + (movie?.backdrop_path ?: String)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = movie?.title,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))


        Column(modifier = Modifier.padding(16.dp)) {
            movie?.let {
                Text(
                    text = it.title,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
            }

            movie?.let {
                Text(
                    text = it.tagline,
                    style = TextStyle(fontSize = 16.sp, fontStyle = FontStyle.Italic),
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


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
                    movie?.let {
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
            movie?.credits?.cast?.take(5)?.forEach { cast ->
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
    }
}