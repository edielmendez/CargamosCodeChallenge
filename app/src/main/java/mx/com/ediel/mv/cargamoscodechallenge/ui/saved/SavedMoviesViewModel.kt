package mx.com.ediel.mv.cargamoscodechallenge.ui.saved

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import mx.com.ediel.mv.cargamoscodechallenge.ui.models.Movie
import javax.inject.Inject

@HiltViewModel
class SavedMoviesViewModel @Inject constructor(

): ViewModel() {
    private val _showSearchBar = MutableStateFlow(false)
    val showSearchBar = _showSearchBar.asStateFlow()

    private val _searchText = MutableStateFlow<String>("")
    val searchText = _searchText.asStateFlow()


    private val _matchedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val matchedMovies = _matchedMovies.asStateFlow()

    private var allMovies: ArrayList<Movie> = arrayListOf()


    init {
        //allMovies.addAll(FakeData.movies)
        //_matchedMovies.value = FakeData.movies
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