package com.example.to_do_2.data.templates

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.to_do_2.model.Templates

@Database(entities = [Templates::class], version = 1, exportSchema = false)
abstract class TemplateDatabase : RoomDatabase() {

    abstract fun templateDao(): TemplateDao

    companion object{
        @Volatile
        private var INSTANCE: TemplateDatabase? = null

        fun getDatabase(context: Context): TemplateDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TemplateDatabase::class.java,
                    "template_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}