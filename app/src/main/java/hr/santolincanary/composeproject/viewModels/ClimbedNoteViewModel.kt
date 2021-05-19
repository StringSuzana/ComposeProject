package hr.santolin.jetpackflowerpower.viewmodel

import androidx.lifecycle.*
import hr.santolin.jetpackflowerpower.models.entities.ClimbedNote
import hr.santolin.jetpackflowerpower.models.repos.ClimbedNoteRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ClimbedNoteViewModel(private val repository: ClimbedNoteRepository) : ViewModel() {

    val allNotes: LiveData<List<ClimbedNote>> = repository.getAllNotes().asLiveData()

    fun insert(note: ClimbedNote) = viewModelScope.launch {
        repository.insertNoteData(note)
    }

    fun update(note: ClimbedNote) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun delete(note: ClimbedNote) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun onFavouriteChecked(noteId: Long?) {
       var note: ClimbedNote? = allNotes.value?.firstOrNull { x -> x.id == noteId }
        if (note != null){
            note.isFavouriteRoute = !note.isFavouriteRoute!!
            update(note)
        }
    }
    fun populateDatabase() {
        val note_1 = ClimbedNote(
            id = null,
            mountain = "Biokovo",
            routeName = "Sive jeze",
            routeGrade = "6b",
            dateClimbedStr = "19.5.2021",
            isFavouriteRoute = false,
        )
        val note_2 = ClimbedNote(
            id = null,
            mountain = "Paklenica",
            routeName = "Velebitaški",
            routeGrade = "6a",
            dateClimbedStr = "10.5.2020",
            isFavouriteRoute = true,
        )
        val note_3 = ClimbedNote(
            id = null,
            mountain = "Paklenica",
            routeName = "Mosoraš",
            routeGrade = "6a+",
            dateClimbedStr = "1.5.2020",
            isFavouriteRoute = true,
        )

        insert(note_1)
        insert(note_2)
        insert(note_3)
    }

}

/**
 * To create the ViewModel we implement a ViewModelProvider.Factory that gets as a parameter the dependencies
 * needed to create ClimbedNoteViewModel: the ClimbedNoteRepository.
 * By using viewModels and ViewModelProvider.Factory then the framework will take care of the lifecycle of the ViewModel.
 * It will survive configuration changes and even if the Activity is recreated,
 * you'll always get the right instance of the ClimbedNoteViewModel class.
 */
class ClimbedNoteViewModelFactory(private val repository: ClimbedNoteRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimbedNoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClimbedNoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}