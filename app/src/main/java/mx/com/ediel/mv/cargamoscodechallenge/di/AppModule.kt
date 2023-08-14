package mx.com.ediel.mv.cargamoscodechallenge.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.AppAuthInterceptor
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.AppRepository
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.AppRepositoryImpl
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.AppService
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.RemoteServiceConstants.AUTH_TOKEN
import mx.com.ediel.mv.cargamoscodechallenge.data.remote.RemoteServiceConstants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    /*@Provides
    @Singleton
    fun provideAuthToken() = AUTH_TOKEN*/

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideAppAuthInterceptor() = AppAuthInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun provideAppService(retrofit: Retrofit): AppService =  retrofit.create(AppService::class.java)

    @Provides
    @Singleton
    fun provideAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository = appRepositoryImpl

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.IO
}

annotation class DefaultDispatcher