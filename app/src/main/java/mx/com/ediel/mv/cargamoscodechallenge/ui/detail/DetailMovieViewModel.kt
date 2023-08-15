package mx.com.ediel.mv.cargamoscodechallenge.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.AppRepository
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.response.NetworkResult
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.ScreenUIState
import mx.com.ediel.mv.cargamoscodechallenge.ui.models.Movie
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
    private val _screenState = MutableStateFlow<ScreenUIState<Movie>>(ScreenUIState.Loading)
    val screenState = _screenState.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    private var job: Job? = null

    private var currentMovie: Movie? = null

    fun getMovie(id: Int) {
        job?.cancel()
        job = repository.getMovie(id)
            .flowOn(Dispatchers.IO)
            .onEach { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        result.data?.let {
                            _screenState.value = ScreenUIState.Success(it)
                            _isFavorite.value = it.isSaved
                            currentMovie = it
                        }
                    }
                    is NetworkResult.Error -> {
                        _screenState.value = ScreenUIState.Error(result.error)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun updateMovie() {
        if(isFavorite.value){
            deleteMovie()
        }else{
            saveMovie()
        }
    }

    private fun saveMovie(){
        viewModelScope.launch {
            currentMovie?.let {
                _isFavorite.value = true
                repository.saveMovie(it)
            }
        }
    }

    private fun deleteMovie() {
        viewModelScope.launch {
            currentMovie?.let {
                _isFavorite.value = false
                repository.deleteMovie(it.id)
            }
        }
    }
}