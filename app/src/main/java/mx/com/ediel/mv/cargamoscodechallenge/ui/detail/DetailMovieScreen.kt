package mx.com.ediel.mv.cargamoscodechallenge.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.com.ediel.mv.cargamoscodechallenge.R
import mx.com.ediel.mv.cargamoscodechallenge.ui.common.ErrorScreen
import mx.com.ediel.mv.cargamoscodechallenge.ui.common.Loader
import mx.com.ediel.mv.cargamoscodechallenge.ui.detail.components.MovieDetailsCard
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.HomeScreen
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.HomeViewModel
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.ScreenUIState
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.components.MoviesGrid
import mx.com.ediel.mv.cargamoscodechallenge.ui.route.NavigationRoutes
import mx.com.ediel.mv.cargamoscodechallenge.ui.utils.toCustomerFormat

@Composable
fun DetailsMovieScreen(
    navController: NavController,
    movieId: Int,
    viewModel: DetailMovieViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val favorite by viewModel.isFavorite.collectAsState()
    val screenState = viewModel.screenState.collectAsState().value
    
    LaunchedEffect(key1 = "getMovie" ){
        viewModel.getMovie(movieId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Ir hacia atras")
                    }
                },
                title = {
                    Text("Detalles de la película")
                },
                actions = {
                    val painter  = when(favorite){
                        true -> painterResource(id = R.drawable.heart)
                        false -> painterResource(id = R.drawable.heart_outline)
                    }
                    IconButton(onClick = {
                        viewModel.updateMovie()
                    }) {
                        Icon(
                            painter,
                            contentDescription = "Favorito"
                        )
                    }
                }
            )
        },

        scaffoldState = scaffoldState
    ) {
        when (screenState) {
            is ScreenUIState.Loading-> {
                Loader()
            }
            is ScreenUIState.Success -> {
                MovieDetailsCard(modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 16.dp, vertical = 16.dp), movie = screenState.data)
            }
            is ScreenUIState.Error -> {
                ErrorScreen(
                    message = screenState.error,
                    onRetryAction = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun DetailsMovieScreenPreview() {
    DetailsMovieScreen(navController = rememberNavController(), movieId = 0)
}