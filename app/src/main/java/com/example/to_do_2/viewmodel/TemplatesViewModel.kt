package com.example.to_do_2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.to_do_2.data.templates.TemplateDatabase
import com.example.to_do_2.model.Templates
import com.example.to_do_2.repository.TemplateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TemplatesViewModel(application: Application) : AndroidViewModel(application) {

    val readAllTemplates : LiveData<List<Templates>>
    private val repository : TemplateRepository

    init {
        val tmpDao = TemplateDatabase.getDatabase(application).templateDao()
        repository = TemplateRepository(tmpDao)
        readAllTemplates = repository.readAllTemplates
    }

    fun addTemplate(tmp: Templates) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTemplate(tmp)
        }
    }
}