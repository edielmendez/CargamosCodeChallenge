package mx.com.ediel.mv.cargamoscodechallenge.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.response.NetworkResult
import mx.com.ediel.mv.cargamoscodechallenge.di.DefaultDispatcher
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.Movie
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.HomeUIState
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val service: AppService
): AppRepository {
    override fun getMovies(page: Int): Flow<NetworkResult<List<Movie>>> = flow{
        try {
            val response = service.getMovies(page)
            if(response.isSuccessful){
                //emit(HomeUIState.Success(response.body()?.movies?.map { it.toMovie() } ?: emptyList()))
                emit(NetworkResult.Success(response.body()?.movies?.map { it.toMovie() } ?: emptyList()))
            }else{
                //emit(HomeUIState.Error(response.errorBody().toString()))
                emit(NetworkResult.Error(response.errorBody().toString()))
            }
        }catch (exception: Exception){
            emit(NetworkResult.Error(error = exception.message ?: ""))
        }
    }

}