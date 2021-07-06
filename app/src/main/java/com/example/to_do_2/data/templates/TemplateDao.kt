package com.example.to_do_2.data.templates

import androidx.room.Dao
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.example.to_do_2.model.Templates

@Dao
interface TemplateDao {

    @Query("SELECT * FROM template_table ORDER BY id ASC")
    fun readAllTemplates() : LiveData<List<Templates>>

    @Insert
    suspend fun addTemplate(tmp: Templates)
}