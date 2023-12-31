package mx.com.ediel.mv.cargamoscodechallenge.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.com.ediel.mv.cargamoscodechallenge.ui.common.ErrorScreen
import mx.com.ediel.mv.cargamoscodechallenge.ui.common.Loader
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.components.MoviesGrid
import mx.com.ediel.mv.cargamoscodechallenge.ui.route.NavigationRoutes

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()

    //val movies = viewModel.moviesList.collectAsState().value
    val screenState = viewModel.screenState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Cargamos Code Challenge")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NavigationRoutes.SavedMoviesScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(painter = painterResource(mx.com.ediel.mv.cargamoscodechallenge.R.drawable.icons8_guardar_cerrar_48), contentDescription = "Saved Movies")
            }
        },
        scaffoldState = scaffoldState
    ) {

        when (screenState) {
            is ScreenUIState.Loading -> {
            }
            is ScreenUIState.Success -> {
                MoviesGrid(
                    movies = screenState.data,
                    modifier = Modifier
                        .padding(it)
                        .padding(start = 16.dp, top = 2.dp, end = 16.dp, bottom = 2.dp),
                    onMovieItemClick = { movieId ->
                        navController.navigate(NavigationRoutes.DetailScreen.route + "/$movieId")
                    },
                    onBottomReached = {
                        viewModel.getMovies()
                    }
                )
            }
            is ScreenUIState.Error -> {
                ErrorScreen(
                    message = screenState.error,
                    onRetryAction = {
                        viewModel.getMovies()
                    }
                )
            }
        }


    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}