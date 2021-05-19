package hr.santolin.jetpackflowerpower.models.repos

import androidx.annotation.WorkerThread
import hr.santolin.jetpackflowerpower.models.daos.ClimbedNoteDao
import hr.santolin.jetpackflowerpower.models.entities.ClimbedNote
import kotlinx.coroutines.flow.Flow

class ClimbedNoteRepository(private val climbedNoteDao: ClimbedNoteDao) {

    @WorkerThread
    suspend fun insertNoteData(note: ClimbedNote) {
        climbedNoteDao.insertNote(note)
    }

    @WorkerThread
    suspend fun updateNote(note: ClimbedNote) {
        climbedNoteDao.updateNote(note)
    }

    @WorkerThread
    suspend fun deleteNote(note: ClimbedNote) {
        climbedNoteDao.deleteNote(note)
    }

     fun getAllNotes(): Flow<List<ClimbedNote>> {
        return climbedNoteDao.getAllNotes()
    }


    val allFavouriteRoutes: Flow<List<ClimbedNote>> = climbedNoteDao.getFavouriteRoutes()
}