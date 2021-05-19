package hr.santolin.jetpackflowerpower.models.daos

import androidx.room.*
import hr.santolin.jetpackflowerpower.models.entities.ClimbedNote
import kotlinx.coroutines.flow.Flow

@Dao
interface ClimbedNoteDao {
    @Query("SELECT * FROM climbed_note")
    fun getAllNotes(): Flow<List<ClimbedNote>>

    @Query("SELECT * FROM climbed_note WHERE route_name LIKE :routeName")
    fun getNoteByRouteName(routeName : String): List<ClimbedNote>

    @Query("SELECT * FROM climbed_note WHERE is_favourite_route = 1")
    fun getFavouriteRoutes(): Flow<List<ClimbedNote>>

    @Insert
    suspend fun insertNotes(notes: List<ClimbedNote>)

    @Insert
    suspend fun insertNote(note: ClimbedNote)

    @Update
    suspend fun updateNote(note: ClimbedNote)

    @Delete
    suspend fun deleteNote(note: ClimbedNote)
}
