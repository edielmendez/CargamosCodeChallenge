package mx.com.ediel.mv.cargamoscodechallenge.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.Movie
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.HomeScreen
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.HomeViewModel
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.components.MoviesGrid
import mx.com.ediel.mv.cargamoscodechallenge.ui.utils.toCustomerFormat

@Composable
fun DetailsMovieScreen(
    navController: NavController,
    movieId: Int,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    var favorite = remember {
        mutableStateOf(false)
    }
    val movie = Movie(
        id = 569094,
        title = "Spider-Man: Across the Spider-Verse",
        poster = "https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg",
        resume = "After reuniting with Gwen Stacy, Brooklyn’s full-time, friendly neighborhood Spider-Man is catapulted across the Multiverse, where he encounters the Spider Society, a team of Spider-People charged with protecting the Multiverse’s very existence. But when the heroes clash on how to handle a new threat, Miles finds himself pitted against the other Spiders and must set out on his own to save those he loves most.",
        average = "8.5%",
        releaseDate = "2023-06-06"
    )
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
                    val painter  = when(favorite.value){
                        true -> painterResource(id = R.drawable.heart)
                        false -> painterResource(id = R.drawable.heart_outline)
                    }
                    IconButton(onClick = {
                        favorite.value = !favorite.value
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


        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp, vertical = 8.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.test_image),
                contentDescription = movie.title,
                contentScale = ContentScale.Fit
            )
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = movie.releaseDate.toCustomerFormat(),
                color = Color.DarkGray,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
            )
            Text(
                modifier = Modifier.padding(all = 4.dp),
                text = movie.title,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                text = movie.average,
                color = Color.DarkGray,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
            )
            LinearProgressIndicator(progress = 0.85f)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.padding(all = 4.dp),
                text = movie.resume,
                color = Color.Gray,
                textAlign = TextAlign.Justify,
                fontSize = 14.sp
            )
        }

    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun DetailsMovieScreenPreview() {
    DetailsMovieScreen(navController = rememberNavController(), movieId = 0)
}