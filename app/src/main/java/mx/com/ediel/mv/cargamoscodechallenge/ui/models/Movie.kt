package mx.com.ediel.mv.cargamoscodechallenge.ui.models

import mx.com.ediel.mv.cargamoscodechallenge.data.local.entities.MovieEntity

data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val resume: String,
    val average: Double,
    val averageValue: Double,
    val averageLabel: String,
    val releaseDate: String,
    val isSaved: Boolean = false
){
    fun toMovieEntity() = MovieEntity(
        id = id,
        title = title,
        poster = poster,
        resume = resume,
        average = average,
        releaseDate = releaseDate
    )
}
