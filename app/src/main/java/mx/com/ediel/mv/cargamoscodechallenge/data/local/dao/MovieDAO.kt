package mx.com.ediel.mv.cargamoscodechallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mx.com.ediel.mv.cargamoscodechallenge.data.local.entities.MovieEntity

@Dao
interface MovieDAO {
    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovie(id: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovie(movie: MovieEntity)

    @Query("DELETE FROM movies WHERE id = :id")
    suspend fun deleteMovie(id: Int)
}