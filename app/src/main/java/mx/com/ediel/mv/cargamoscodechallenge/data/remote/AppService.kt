package mx.com.ediel.mv.cargamoscodechallenge.data.remote

import mx.com.ediel.mv.cargamoscodechallenge.data.remote.RemoteServiceConstants.AUTH_TOKEN
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.response.MovieDTO
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.response.PaginatedMoviesResponse
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.response.SingleMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface AppService {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortby: String = "popularity.desc",
        @Query("api_key") ApiKey: String = "$AUTH_TOKEN"
    ): Response<PaginatedMoviesResponse>

    @GET("movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") id: Int,
        @Query("api_key") ApiKey: String = "$AUTH_TOKEN"
    ): Response<MovieDTO>
}