package com.example.a7hw1.domain.repository

import com.example.a7hw1.domain.model.Note
import com.example.a7hw1.domain.utils.ResultStatus
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun createNote(note: Note): Flow<ResultStatus<Unit>>
    fun getAllNotes(): Flow<ResultStatus<List<Note>>>
    fun editNote(note: Note): Flow<ResultStatus<Unit>>
    fun deleteNote(note: Note): Flow<ResultStatus<Unit>>
}