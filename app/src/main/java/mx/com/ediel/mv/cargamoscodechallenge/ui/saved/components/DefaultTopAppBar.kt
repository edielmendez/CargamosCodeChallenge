package mx.com.ediel.mv.cargamoscodechallenge.ui.saved.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable

@Composable
fun DefaultTopAppBar(
    onNavigateBack: () -> Unit = {},
    onSearchIconClick: () -> Unit = {}
){
    TopAppBar(
        title = {
            Text("Peliculas guardadas")
        },
        navigationIcon = {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Ir hacia atras")
            }
        },
        actions = {
            IconButton(onClick = {
                //navController.navigate(NavigationRoutes.SearchMoviesScreen.route)
                onSearchIconClick()
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar..."
                )
            }
        }
    )
}