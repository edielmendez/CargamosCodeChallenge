package mx.com.ediel.mv.cargamoscodechallenge.ui.route

sealed class NavigationRoutes(val route: String){
    object HomeScreen: NavigationRoutes("home_screen")
    object DetailScreen: NavigationRoutes("detail_screen")
}
