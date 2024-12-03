package com.example.applicationfilmkotlin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationtest.playlistjson
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

public class MainViewModel : ViewModel() {
    val movies = MutableStateFlow<List<Movie>>(listOf())
    val series = MutableStateFlow<List<Serie>>(listOf())
    val actors = MutableStateFlow<List<Actor>>(listOf())
    val detailMovie = MutableStateFlow<DetailMovie?>(null)
    val detailActor = MutableStateFlow<DetailActor?>(null)
    val detailSerie = MutableStateFlow<DetailSerie?>(null)

    val playlist = MutableStateFlow<List<Playlist>>(listOf())

    val apikey = "be1ca8af0da3936dcdb2aeaad464d374"
    val language= "fr-FR"
    val service = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(Api::class.java)

    fun searchMovies(motcle: String) {
        viewModelScope.launch {
            try {
                val response = service.searchMovies(apikey, motcle)
                movies.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun lastMovies() {
        viewModelScope.launch {
            movies.value = service.lastMovies(apikey).results
        }
    }

    fun detailMovies(id:Int){
        viewModelScope.launch{
            detailMovie.value = service.detailMovie(id,apikey,"credits",language)
        }
    }

    fun searchSeries(motcle: String) {
        viewModelScope.launch {
            try {
                val response = service.searchSeries(apikey, motcle)
                series.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun lastSeries() {
        viewModelScope.launch {
            series.value = service.lastSeries(apikey).results
        }
    }
    fun detailSeries(id:Int){
        viewModelScope.launch{
            detailSerie.value = service.detailSerie(id,apikey,"credits",language)
        }
    }

    fun searchActors(motcle: String) {
        viewModelScope.launch {
            try {
                val response = service.searchActors(apikey, motcle)
                actors.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun lastActors() {
        viewModelScope.launch {
            actors.value = service.lastActors(apikey).results
        }
    }
    fun detailActors(id:Int) {
        viewModelScope.launch {
            detailActor.value = service.detailActor(id,apikey,language)
        }
    }
    fun fetchPlaylist(): Playlist{
        val moshi = Moshi.Builder().build()
        return moshi.adapter(Playlist::class.java).fromJson(playlistjson)!!
    }
    fun playList(){
        viewModelScope.launch {
            playlist.value = fetchPlaylist()
        }
    }
}