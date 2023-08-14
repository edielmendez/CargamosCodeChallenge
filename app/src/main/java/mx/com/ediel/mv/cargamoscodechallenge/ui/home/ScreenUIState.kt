package mx.com.ediel.mv.cargamoscodechallenge.ui.home


sealed class ScreenUIState<out T>{
    object Loading: ScreenUIState<Nothing>()
    class Success<T>(val data: T): ScreenUIState<T>()
    class Error(val error: String): ScreenUIState<Nothing>()
}