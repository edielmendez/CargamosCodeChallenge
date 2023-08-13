package mx.com.ediel.mv.cargamoscodechallenge.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.FakeData
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.Movie
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel() {
    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList = _moviesList.asStateFlow()

    private val _screenState = MutableStateFlow<HomeUIState<List<Movie>>>(HomeUIState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000)
            //_moviesList.value = FakeData.movies
            _screenState.value = HomeUIState.Success(FakeData.movies)
        }
    }
}