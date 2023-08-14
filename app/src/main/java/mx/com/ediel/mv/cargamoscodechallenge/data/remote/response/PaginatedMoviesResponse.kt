package mx.com.ediel.mv.cargamoscodechallenge.data.remote.response

import com.google.gson.annotations.SerializedName
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.Movie

data class PaginatedMoviesResponse(
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var movies      : List<MovieDTO> = emptyList(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
)

data class MovieDTO (

    @SerializedName("adult"             ) var adult            : Boolean?       = null,
    @SerializedName("backdrop_path"     ) var backdropPath     : String?        = null,
    @SerializedName("genre_ids"         ) var genreIds         : ArrayList<Int> = arrayListOf(),
    @SerializedName("id"                ) var id               : Int?           = null,
    @SerializedName("original_language" ) var originalLanguage : String?        = null,
    @SerializedName("original_title"    ) var originalTitle    : String?        = null,
    @SerializedName("overview"          ) var overview         : String?        = null,
    @SerializedName("popularity"        ) var popularity       : Double?        = null,
    @SerializedName("poster_path"       ) var posterPath       : String?        = null,
    @SerializedName("release_date"      ) var releaseDate      : String?        = null,
    @SerializedName("title"             ) var title            : String?        = null,
    @SerializedName("video"             ) var video            : Boolean?       = null,
    @SerializedName("vote_average"      ) var voteAverage      : Double?        = null,
    @SerializedName("vote_count"        ) var voteCount        : Int?           = null

){
    fun toMovie() = Movie(
        id = id ?: 0,
        title = title ?: "No Title",
        poster = "https://image.tmdb.org/t/p/w500/$posterPath",
        resume = overview ?: "",
        average = "$voteAverage %",
        averageValue = (voteAverage ?: 0).toDouble() / 10,
        releaseDate = "2023-06-06"
    )
}