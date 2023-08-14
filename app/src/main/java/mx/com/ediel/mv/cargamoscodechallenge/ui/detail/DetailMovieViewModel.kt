package mx.com.ediel.mv.cargamoscodechallenge.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.AppRepository
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.response.NetworkResult
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.Movie
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.ScreenUIState
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel()  {
    private val _screenState = MutableStateFlow<ScreenUIState<Movie>>(ScreenUIState.Loading)
    val screenState = _screenState.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    private var job: Job? = null

    fun getMovie(id: Int){
        job?.cancel()
        job = repository.getMovie(id)
            .flowOn(Dispatchers.IO)
            .onEach { result ->
            when (result) {
                is NetworkResult.Success -> {
                    result.data?.let {
                        _screenState.value = ScreenUIState.Success(it)
                        _isFavorite.value = it.isSaved
                    }
                }
                is NetworkResult.Error -> {
                    _screenState.value = ScreenUIState.Error(result.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}