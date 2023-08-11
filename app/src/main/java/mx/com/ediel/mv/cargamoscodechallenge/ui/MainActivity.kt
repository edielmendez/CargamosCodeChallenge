package mx.com.ediel.mv.cargamoscodechallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import mx.com.ediel.mv.cargamoscodechallenge.ui.detail.DetailsMovieScreen
import mx.com.ediel.mv.cargamoscodechallenge.ui.home.HomeScreen
import mx.com.ediel.mv.cargamoscodechallenge.ui.route.NavigationRoutes
import mx.com.ediel.mv.cargamoscodechallenge.ui.theme.CargamosCodeChallengeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CargamosCodeChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavigationRoutes.HomeScreen.route
                    ){
                        composable(route = NavigationRoutes.HomeScreen.route){
                            HomeScreen(
                                navController = navController
                            )
                        }
                        composable(
                            route = NavigationRoutes.DetailScreen.route + "/{movieId}",
                            arguments = listOf(
                                navArgument(
                                    name = "movieId"
                                ) {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            DetailsMovieScreen(
                                navController = navController,
                                movieId = it.arguments?.getInt("movieId") ?: 0
                            )
                        }
                    }
                }
            }
        }
    }
}
/*
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CargamosCodeChallengeTheme {
        Greeting("Android")
    }
}*/