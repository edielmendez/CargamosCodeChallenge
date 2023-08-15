package mx.com.ediel.mv.cargamoscodechallenge.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mx.com.ediel.mv.cargamoscodechallenge.data.local.dao.MovieDAO
import mx.com.ediel.mv.cargamoscodechallenge.data.local.entities.MovieEntity


@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO

    companion object {
        private const val DB_NAME = "CCC_APP_DB_ROOM.db"

        // For Singleton instantiation
        @Volatile private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): LocalDatabase {
            return Room.databaseBuilder(context, LocalDatabase::class.java, DB_NAME)
                .build()
        }
    }
}