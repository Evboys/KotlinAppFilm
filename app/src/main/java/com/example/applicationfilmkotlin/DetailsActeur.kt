package com.example.applicationfilmkotlin


import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DetailActeurScreen(id:Int){
    val viewModel: MainViewModel = viewModel()
    val actor by viewModel.detailActor.collectAsState()

    viewModel.detailActors(id)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            model = "https://image.tmdb.org/t/p/original${actor?.profile_path}",
            contentDescription = actor?.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.Transparent)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))


        Column(modifier = Modifier.padding(16.dp)) {
            actor?.let {
                Text(
                    text = it.name,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
            }

            Text(
                text = "Born:" + (actor?.birthday ?: String),
                style = TextStyle(fontSize = 16.sp, fontStyle = FontStyle.Italic),
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))


            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),

                ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Biography",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = actor?.biography.takeIf { it?.isNotEmpty() == true } ?: "No biography available",
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.DarkGray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (actor?.also_known_as?.isNotEmpty() == true) {
            Text(
                text = "Also Known As",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )


            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                actor?.also_known_as?.forEach { alias ->
                    Text(
                        text = alias,
                        style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Popularity: ",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
                color = Color.Black
            )
            Text(
                text = actor?.popularity.toString(),
                style = TextStyle(fontSize = 18.sp),
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (actor?.imdb_id?.isNotEmpty() == true) {
            val imdbUrl = "https://www.imdb.com/name/${actor!!.imdb_id}"
            Text(
                text = "IMDb: $imdbUrl",
                style = TextStyle(fontSize = 16.sp, fontStyle = FontStyle.Italic),
                color = Color.Blue,
                modifier = Modifier.padding(horizontal = 16.dp),

            )
        }
    }
}