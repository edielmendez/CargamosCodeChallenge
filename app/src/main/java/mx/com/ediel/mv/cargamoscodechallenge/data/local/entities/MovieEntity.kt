package mx.com.ediel.mv.cargamoscodechallenge.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.com.ediel.mv.cargamoscodechallenge.ui.models.Movie


@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "poster") val poster: String?,
    @ColumnInfo(name = "resume") val resume: String?,
    @ColumnInfo(name = "average") val average: Double?,
    @ColumnInfo(name = "releaseDate") val releaseDate: String?,
){
    fun toMovie() = Movie(
        id = id ?: 0,
        title = title ?: "No Title",
        poster = poster ?: "",
        resume = resume ?: "",
        average = average ?: 0.0,
        averageValue = (average ?: 0).toDouble() / 10,
        averageLabel = "$average %",
        releaseDate = releaseDate ?: ""
    )
}
/*
data class Movie(
    val id: Int,
    val title: String,
    val poster: String,
    val resume: String,
    val average: String,
    val averageValue: Double,
    val releaseDate: String,
    val isSaved: Boolean = false
)
* */