package com.example.sonora.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "musica_table")
data class Musica(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val duracao: String
)