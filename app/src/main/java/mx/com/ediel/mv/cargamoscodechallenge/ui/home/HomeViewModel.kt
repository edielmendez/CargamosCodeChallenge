package mx.com.ediel.mv.cargamoscodechallenge.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.AppRepository
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.response.NetworkResult
import mx.com.ediel.mv.cargamoscodechallenge.ui.models.Movie
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
    /*private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList = _moviesList.asStateFlow()*/

    private val _screenState = MutableStateFlow<ScreenUIState<List<Movie>>>(ScreenUIState.Loading)
    val screenState = _screenState.asStateFlow()

    private val moviesList: MutableList<Movie> = mutableListOf()
    //private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    //val moviesList = _moviesList.asStateFlow()

    private var page = 1

    private var job: Job? = null

    init {
        page = 0
        getMovies()
        moviesList.clear()
    }

    fun getMovies(){
        job?.cancel()
        page++
        job = repository.getMovies(page)
            .flowOn(Dispatchers.IO)
            .onEach { result ->
            when (result) {
                is NetworkResult.Success -> {
                    moviesList.addAll(result.data)
                    _screenState.update {
                        ScreenUIState.Success(moviesList)
                    }
                }
                is NetworkResult.Error -> {
                    _screenState.value = ScreenUIState.Error(result.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getMoviesFirstLoad(){

    }
}