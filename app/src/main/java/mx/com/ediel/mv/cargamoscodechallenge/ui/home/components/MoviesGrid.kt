package mx.com.ediel.mv.cargamoscodechallenge.ui.home.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.Movie
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.HomeViewModel

@Composable
fun MoviesGrid(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onMovieItemClick: (Int) -> Unit
){
    LazyVerticalGrid(columns = GridCells.Fixed(count = 3), modifier = modifier){
        items(movies){ movie ->
            MovieCard(movie = movie, onItemClick = {onMovieItemClick(movie.id)})
        }
    }
}