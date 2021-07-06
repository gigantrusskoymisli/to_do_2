package com.example.to_do_2.data.notes

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.to_do_2.model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<Notes>>

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllData()

    @Insert
    suspend fun addNote(note: Notes)

    @Update
    suspend fun updateNote(note: Notes)

    @Delete
    suspend fun deleteNote(note: Notes)
}