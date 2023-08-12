package mx.com.ediel.mv.cargamoscodechallenge.ui.route

sealed class NavigationRoutes(val route: String){
    object HomeScreen: NavigationRoutes("home_screen")
    object DetailScreen: NavigationRoutes("detail_screen")
    object SavedMoviesScreen: NavigationRoutes("saved_movies_screen")
    object SearchMoviesScreen: NavigationRoutes("search_movies_screen")
}
