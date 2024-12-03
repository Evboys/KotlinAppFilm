package com.example.applicationfilmkotlin


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage


@Composable
fun PlaylistScreen(){
    /*val viewModel: MainViewModel = viewModel()
    val musics by viewModel.playlist.collectAsState()
    viewModel.playList()*/
        AsyncImage(model = "file:///android_asset/images/2.jpg", contentDescription = "")


}
