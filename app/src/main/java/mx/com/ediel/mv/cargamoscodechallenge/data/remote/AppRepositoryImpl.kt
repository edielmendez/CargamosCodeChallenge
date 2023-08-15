package mx.com.ediel.mv.cargamoscodechallenge.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import mx.com.ediel.mv.cargamoscodechallenge.data.local.dao.MovieDAO
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.response.NetworkResult
import mx.com.ediel.mv.cargamoscodechallenge.di.DefaultDispatcher
import mx.com.ediel.mv.cargamoscodechallenge.ui.models.Movie
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val service: AppService,
    private val movieDAO: MovieDAO
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

    override fun getMovie(id: Int): Flow<NetworkResult<Movie?>> {
        return  flow {
            val localMovie = movieDAO.getMovie(id)
            if (localMovie != null) {
                movieDAO.getMovie(id)?.let {
                    emit(NetworkResult.Success(it.toMovie().copy(isSaved = true)))
                }
            } else {
                try {
                    val response = service.getMovie(id)
                    if (response.isSuccessful) {
                        emit(NetworkResult.Success(response.body()?.toMovie()))
                        /*emit(
                            NetworkResult.Success(
                                Movie(
                                    id = it.id ?: 0,
                                    title = it.title ?: "No Title",
                                    poster = "https://image.tmdb.org/t/p/w500/${it.posterPath}",
                                    resume = it.overview ?: "",
                                    average = "${it.voteAverage} %",
                                    averageValue = (it.voteAverage ?: 0).toDouble() / 10,
                                    releaseDate = it.releaseDate ?: ""
                                )
                            )
                        )*/

                    } else {
                        emit(NetworkResult.Error(response.errorBody().toString()))
                    }
                } catch (exception: Exception) {
                    emit(NetworkResult.Error(error = exception.message ?: ""))
                }
            }
        }
    }

    override suspend fun saveMovie(movie: Movie) {
        movieDAO.saveMovie(movie.toMovieEntity())
    }

    override suspend fun deleteMovie(id: Int) {
        movieDAO.deleteMovie(id)
    }

    override fun getLocalMovies() = movieDAO.getMovies().map { movieEntityList -> movieEntityList.map { it.toMovie() } }

}