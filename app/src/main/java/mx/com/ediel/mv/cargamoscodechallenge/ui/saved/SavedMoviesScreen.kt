package mx.com.ediel.mv.cargamoscodechallenge.ui.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.HomeViewModel
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.components.MoviesGrid
import mx.com.ediel.mv.cargamoscodechallenge.ui.route.NavigationRoutes
import mx.com.ediel.mv.cargamoscodechallenge.ui.saved.components.DefaultTopAppBar
import mx.com.ediel.mv.cargamoscodechallenge.ui.saved.components.NoSearchResultsFounds
import mx.com.ediel.mv.cargamoscodechallenge.ui.saved.components.SearchTopAppBar

@Composable
fun SavedMoviesScreen(
    navController: NavController,
    viewModel: SavedMoviesViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    //val movies = viewModel.moviesList.collectAsState().value
    val movies by viewModel.matchedMovies.collectAsState()
    val showSearchBar by viewModel.showSearchBar.collectAsState()
    val searchText by viewModel.searchText.collectAsState()


    Scaffold(
        topBar = {
            when(showSearchBar){
                true -> SearchTopAppBar(
                    searchText = searchText,
                    placeholderText = "Buscar...",
                    onSearchTextChanged = { viewModel.onSearchTextChanged(it) },
                    onClearClick = { viewModel.onClearClick(); viewModel.showSearchBar(false) },
                    onNavigateBack = { navController.popBackStack() },
                )
                false -> DefaultTopAppBar(
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onSearchIconClick = {
                        viewModel.showSearchBar(true)
                    }
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        if(movies.isEmpty() && searchText.isNotEmpty()){
            NoSearchResultsFounds(
                searchText = searchText
            )
        }else{
            MoviesGrid(
                movies = movies,
                modifier = Modifier.padding(it).padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 32.dp),
                onMovieItemClick = {movieId ->
                    navController.navigate(NavigationRoutes.DetailScreen.route + "/$movieId")
                }
            )
        }
    }
}