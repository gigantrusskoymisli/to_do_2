package com.example.to_do_2.repository

import androidx.lifecycle.LiveData
import com.example.to_do_2.data.notes.NotesDao
import com.example.to_do_2.model.Notes

class NotesRepository(private val notesDao: NotesDao) {
    
    val readAllData: LiveData<List<Notes>> = notesDao.readAllData()

    suspend fun addNote(note: Notes){
        notesDao.addNote(note)
    }

    suspend fun updateNote(note: Notes) {
        notesDao.updateNote(note)
    }

    suspend fun deleteNote(note: Notes) {
        notesDao.deleteNote(note)
    }

    suspend fun deleteAllData() {
        notesDao.deleteAllData()
    }
}