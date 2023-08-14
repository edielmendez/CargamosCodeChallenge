package mx.com.ediel.mv.cargamoscodechallenge.data.remote.response

sealed class NetworkResult<out T>{
    class Success<T>(val data: T): NetworkResult<T>()
    class Error(val error: String): NetworkResult<Nothing>()
}
