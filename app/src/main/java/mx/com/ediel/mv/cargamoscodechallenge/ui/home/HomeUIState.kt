package mx.com.ediel.mv.cargamoscodechallenge.ui.home

import mx.com.ediel.mv.cargamoscodechallenge.ui.fake.Movie

sealed class HomeUIState<out T>{
    object Loading: HomeUIState<Nothing>()
    object Success: HomeUIState<Nothing>()
    class Error(val error: String): HomeUIState<Nothing>()
}