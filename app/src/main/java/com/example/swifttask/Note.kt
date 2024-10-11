package com.example.swifttask

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    val title: String,
    val details: String,
    val time: String,
    val date: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

