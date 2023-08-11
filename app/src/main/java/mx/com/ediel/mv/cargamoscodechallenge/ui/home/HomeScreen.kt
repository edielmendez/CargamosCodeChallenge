package mx.com.ediel.mv.cargamoscodechallenge.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.components.MovieCard
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.components.MoviesGrid

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()

    val movies = viewModel.moviesList.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Cargamos Code Challenge")
                }
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MoviesGrid(
                movies = movies,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 32.dp)
            )
        }
    }
}