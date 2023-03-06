package com.example.a7hw1.data.local

import androidx.room.*
import com.example.a7hw1.data.model.NoteEntity

@Dao
interface NoteDao {

    //CRUD

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NoteEntity>

    @Update
    fun editNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)
}