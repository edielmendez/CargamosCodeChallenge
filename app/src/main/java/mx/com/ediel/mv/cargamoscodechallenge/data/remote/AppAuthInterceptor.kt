package mx.com.ediel.mv.cargamoscodechallenge.data.remote

import mx.com.ediel.mv.cargamoscodechallenge.data.remote.RemoteServiceConstants.AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AppAuthInterceptor @Inject constructor(
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("api_key", AUTH_TOKEN)
            .build()
        return chain.proceed(request)
    }
}