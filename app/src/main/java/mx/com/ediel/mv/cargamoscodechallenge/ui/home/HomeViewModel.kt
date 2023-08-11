package mx.com.ediel.mv.cargamoscodechallenge.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.FakeData
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.Movie
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel() {
    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList = _moviesList.asStateFlow()

    init {
        _moviesList.value = FakeData.movies
    }
}