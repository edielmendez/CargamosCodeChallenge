package mx.com.ediel.mv.cargamoscodechallenge.data.remote

import kotlinx.coroutines.flow.Flow
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.response.NetworkResult
import mx.com.ediel.mv.cargamoscodechallenge.ui.models.Movie

interface AppRepository {
    fun getMovies(page: Int): Flow<NetworkResult<List<Movie>>>
    fun getMovie(id: Int): Flow<NetworkResult<Movie?>>

    suspend fun saveMovie(movie: Movie)

    suspend fun deleteMovie(id: Int)

    fun getLocalMovies(): Flow<List<Movie>>
}