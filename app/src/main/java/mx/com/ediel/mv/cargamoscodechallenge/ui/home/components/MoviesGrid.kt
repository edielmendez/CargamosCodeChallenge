package mx.com.ediel.mv.cargamoscodechallenge.ui.home.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
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
    onMovieItemClick: (Int) -> Unit,
    onBottomReached: () -> Unit = {}
){
    val gridState = rememberLazyGridState()
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(count = 3), state = gridState){
            items(movies){ movie ->
                MovieCard(movie = movie, onItemClick = {onMovieItemClick(movie.id)})
            }
        }
    }
    gridState.OnBottomReached {
        onBottomReached()
    }
}



@Composable
fun LazyGridState.OnBottomReached(
    loadMore : () -> Unit
){
    // state object which tells us if we should load more
    val shouldLoadMore = remember {
        derivedStateOf {

            // get last visible item
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:
                // list is empty
                // return false here if loadMore should not be invoked if the list is empty
                return@derivedStateOf true

            // Check if last visible item is the last item in the list
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    // Convert the state into a cold flow and collect
    LaunchedEffect(shouldLoadMore){
        snapshotFlow { shouldLoadMore.value }
            .collect {
                // if should load more, then invoke loadMore
                if (it) loadMore()
            }
    }
}