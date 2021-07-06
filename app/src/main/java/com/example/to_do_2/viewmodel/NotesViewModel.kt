package com.example.to_do_2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.to_do_2.data.notes.NotesDatabase
import com.example.to_do_2.repository.NotesRepository
import com.example.to_do_2.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Notes>>
    private val repository: NotesRepository

    init {
        val notesDao = NotesDatabase.getDatabase(application).notesDao()
        repository = NotesRepository(notesDao)
        readAllData = repository.readAllData
    }

    fun addNote(note: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(note)
        }
    }

    fun deleteNote(note: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
    }
}