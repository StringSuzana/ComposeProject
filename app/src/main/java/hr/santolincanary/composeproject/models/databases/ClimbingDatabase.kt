package hr.santolin.jetpackflowerpower.models.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hr.santolin.jetpackflowerpower.models.daos.ClimbedNoteDao
import hr.santolin.jetpackflowerpower.models.entities.ClimbedNote
import hr.santolin.jetpackflowerpower.utils.DateConverter

@Database(entities = [ClimbedNote::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class ClimbingDatabase : RoomDatabase() {
    abstract fun climbedNoteDao(): ClimbedNoteDao

    companion object {
        @Volatile
        private var INSTANCE: ClimbingDatabase? = null
        fun getInstance(context: Context): ClimbingDatabase =
            INSTANCE ?: synchronized(ClimbingDatabase::class.java) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ClimbingDatabase::class.java,
            "climbing.db"
        ).build()
    }

}