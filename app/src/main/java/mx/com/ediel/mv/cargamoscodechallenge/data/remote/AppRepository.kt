package mx.com.ediel.mv.cargamoscodechallenge.data.remote

import kotlinx.coroutines.flow.Flow
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.response.NetworkResult
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.Movie
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.HomeUIState

interface AppRepository {
    fun getMovies(page: Int): Flow<NetworkResult<List<Movie>>>
}