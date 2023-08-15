package mx.com.ediel.mv.cargamoscodechallenge.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.com.ediel.mv.cargamoscodechallenge.data.local.dao.MovieDAO
import mx.com.ediel.mv.cargamoscodechallenge.data.local.db.LocalDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return LocalDatabase.getInstance(context)
    }

    @Provides
    fun provideMovieDAO(appDatabase: LocalDatabase): MovieDAO {
        return appDatabase.movieDAO()
    }
}