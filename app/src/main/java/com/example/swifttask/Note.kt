package com.example.swifttask

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    var title: String,
    var details: String,
    var time: String,
    var date: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

