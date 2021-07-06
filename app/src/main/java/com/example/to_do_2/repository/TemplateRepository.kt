package com.example.to_do_2.repository

import androidx.lifecycle.LiveData
import com.example.to_do_2.data.templates.TemplateDao
import com.example.to_do_2.model.Templates

class TemplateRepository(private val tmpDao : TemplateDao) {

    val readAllTemplates: LiveData<List<Templates>> = tmpDao.readAllTemplates()

    suspend fun addTemplate(tmp: Templates){
        tmpDao.addTemplate(tmp)
    }
}