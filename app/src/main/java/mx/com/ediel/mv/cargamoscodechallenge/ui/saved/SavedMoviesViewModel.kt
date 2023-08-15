package mx.com.ediel.mv.cargamoscodechallenge.ui.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.AppRepository
import mx.com.ediel.mv.cargamoscodechallenge.ui.models.Movie
import javax.inject.Inject

@HiltViewModel
class SavedMoviesViewModel @Inject constructor(
    private val repository: AppRepository
): ViewModel() {
    private val _showSearchBar = MutableStateFlow(false)
    val showSearchBar = _showSearchBar.asStateFlow()

    private val _searchText = MutableStateFlow<String>("")
    val searchText = _searchText.asStateFlow()


    private val _matchedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val matchedMovies = _matchedMovies.asStateFlow()

    private var allMovies: List<Movie> = emptyList()

    private var job: Job? = null


    init {
        //allMovies.addAll(FakeData.movies)
        //_matchedMovies.value = FakeData.movies
        getMovies()
    }

    private fun getMovies(){
        job?.cancel()
        job = repository.getLocalMovies()
            .onEach {
            allMovies = it
                _matchedMovies.value = allMovies
            }.launchIn(viewModelScope)
    }

    fun onSearchTextChanged(changedSearchText: String) {
        _searchText.value = changedSearchText
        if (changedSearchText.isEmpty()) {
            _matchedMovies.value = allMovies
            return
        }
        val usersFromSearch = allMovies.filter { movie ->
            movie.title.contains(changedSearchText, true) || movie.resume.contains(changedSearchText, true)

        }
        _matchedMovies.value = usersFromSearch
    }

    fun onClearClick() {
        _searchText.value = ""
        _matchedMovies.value = allMovies
    }

    fun showSearchBar(show: Boolean){
        _showSearchBar.value = show
    }
}