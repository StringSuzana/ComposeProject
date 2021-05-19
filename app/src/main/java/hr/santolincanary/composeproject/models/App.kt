package hr.santolincanary.composeproject.models

import android.app.Application
import hr.santolin.jetpackflowerpower.models.databases.ClimbingDatabase
import hr.santolin.jetpackflowerpower.models.repos.ClimbedNoteRepository

class App : Application() {
    private val database by lazy { ClimbingDatabase.getInstance(this) }
    val repository by lazy { ClimbedNoteRepository(database.climbedNoteDao()) }
}
