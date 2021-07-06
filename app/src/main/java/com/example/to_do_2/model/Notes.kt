package com.example.to_do_2.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class Notes (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val day: String,
    val note: String
): Parcelable