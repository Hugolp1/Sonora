package com.example.sonora.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MusicaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMusica(musica: Musica)

    @Update
    suspend fun atualizarMusica(musica: Musica)

    @Delete
    suspend fun deletarMusica(musica: Musica)

    @Query("SELECT * FROM musica_table")
    suspend fun listarMusicas() : List<Musica>

    @Query("SELECT * FROM musica_table ORDER BY nome ASC")
    suspend fun listarMusicasEmOrdem() : List<Musica>

}