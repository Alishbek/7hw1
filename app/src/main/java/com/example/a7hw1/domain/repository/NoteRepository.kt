package com.example.a7hw1.domain.repository

import com.example.a7hw1.domain.model.Note

interface NoteRepository {
    fun createNote(note: Note)
    fun getAllNotes(): List<Note>
    fun editNote(note: Note)
    fun deleteNote(note: Note)
}