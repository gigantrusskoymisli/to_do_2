package com.example.to_do_2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "template_table")
data class Templates (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val tmpNote : String
): Parcelable