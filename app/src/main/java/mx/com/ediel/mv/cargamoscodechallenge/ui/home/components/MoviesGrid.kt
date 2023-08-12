package mx.com.ediel.mv.cargamoscodechallenge.ui.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.Movie
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.HomeViewModel
import mx.com.ediel.mv.cargamoscodechallenge.ui.route.NavigationRoutes

@Composable
fun MoviesGrid(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onMovieItemClick: (Int) -> Unit
){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(count = 3)){
            items(movies){ movie ->
                MovieCard(movie = movie, onItemClick = {onMovieItemClick(movie.id)})
            }
        }
    }
}