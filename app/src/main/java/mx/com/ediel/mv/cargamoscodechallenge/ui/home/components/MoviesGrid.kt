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
import kotlinx.coroutines.flow.distinctUntilChanged
import mx.com.ediel.mv.cargamoscodechallenge.ui.models.Movie

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
    gridState.OnBottomReached(
        loadMore = {
            onBottomReached()
        }
    )
}



@Composable
fun LazyGridState.OnBottomReached(
    loadMore : () -> Unit,
    buffer: Int = 2
){
    // state object which tells us if we should load more
    /*val shouldLoadMore = remember {
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
    }*/
    val shouldLoadMore = remember {
        derivedStateOf {
            val layoutInfo = this.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            lastVisibleItemIndex > (totalItems - buffer)
        }
    }

    // Convert the state into a cold flow and collect
    LaunchedEffect(shouldLoadMore){
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .collect {
                if(it)
                    loadMore()
            }
    }
}